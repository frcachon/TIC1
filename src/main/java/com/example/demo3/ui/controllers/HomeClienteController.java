package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.persistence.ActividadRepository;
import com.example.demo3.persistence.OperadorRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HomeClienteController implements Initializable {

    @Autowired
    private ActividadRepository actvidadRepository;

    @Autowired
    private ActividadViewController actividadViewController;

    @Autowired
    private ActividadThumbController actividadThumbController;

    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private EditarInteresesClienteController editarInteresesClienteController;

    Cliente cliente;
    void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private AnchorPane home_pane;

    @FXML
    private Button atrasButton;

    @FXML
    private Button itinerario_button;

    @FXML
    private Button editar_preferencia_button;

    @FXML
    private Button realizar_comentario_button;

    @FXML
    private Button editar_perfil_button;

    @FXML
    private Label username_label;

    @FXML
    private TextField search_field;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane actividadesGrid;
    private List<Actividad> actividades;

    @FXML
    private void mouseClick(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();

        //Bounds b = source.getBoundsInLocal();
        //Bounds c = source.getBoundsInParent();

        //Node temp =

        //boolean a = source.getBoundsInLocal().contains(x,y);
        //int colIndex = GridPane.getColumnIndex(source);
        //int rowIndex = GridPane.getRowIndex(source);

/*        for( Node node: actividadesGrid.getChildren()) {
            if( node instanceof HBox) {
                if(source.getBoundsInParent().contains(event.getX(), event.getY())) {
                    rowIndex =  GridPane.getRowIndex(node);
                    colIndex = GridPane.getColumnIndex(node);
                }
            }
        }*/

        int rowIndex = 0;
        for(Node node: actividadesGrid.getChildren()) {
            if(node instanceof HBox) {
                if(node.getBoundsInParent().contains(event.getX(), event.getY())) {
                    rowIndex =  GridPane.getRowIndex(node) - 1;
                    //System.out.println(rowIndex);
                }
            }
        }
        Actividad act = actividadThumbController.actividades.get(rowIndex);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        actividadViewController.setActividad(act);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void busquedaDinamica(KeyEvent event) {
        actividadesGrid.getChildren().clear();
        List<Actividad> q = (List<Actividad>) actvidadRepository.findAllByTituloContaining(search_field.getText());
        int row = 1;

        try {
            for (Actividad actividad : q) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ActividadThumb.fxml"));
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                HBox box = fxmlLoader.load();
                actividadThumbController = fxmlLoader.getController();
                actividadThumbController.setData(actividad);
                actividadThumbController.setPane(home_pane);
                actividadesGrid.addRow(row, box);
                row++;
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }



        //ObservableList<Actividad> lista;
        //lista = FXCollections.observableArrayList();
        //lista.removeAll();
        //lista.addAll(q);

        //tabla_actividades.setItems(lista);
        //nombre_actividad.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        //nombre_operador.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        //nombre_operador.setCellValueFactory(cellData -> {
        //    Integer idoperador = cellData.getValue().getIdoperador();
        //    String nombre_operador = operadorRepository.findOperadorById(idoperador).getEmpresa();
        //    return new ReadOnlyStringWrapper(nombre_operador);
        //});
    }

    @FXML
    void editar_perfil(ActionEvent event) {

    }

    @FXML
    void editar_preferencia(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        editarInteresesClienteController.setCliente(cliente);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("EditarInteresesCliente.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void realizar_comentario(ActionEvent event) {

    }

    @FXML
    void ver_itinerario(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Login.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        username_label.setText(cliente.getMail());
        actividadesGrid.getChildren().clear();
        actividades = (List<Actividad>) actvidadRepository.findAll();
        int row = 1;

        try {
            for (Actividad actividad : actividades) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ActividadThumb.fxml"));
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                HBox box = fxmlLoader.load();
                actividadThumbController = fxmlLoader.getController();
                actividadThumbController.setData(actividad);
                actividadThumbController.setPane(home_pane);
                actividadesGrid.addRow(row, box);
                row++;
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

/*    EventHandler<ActionEvent> buttonEventHandler(){
        return event -> {
            Node node = (Node) event.getTarget();
            int row = GridPane.getRowIndex(node);
            int column = GridPane.getColumnIndex(node);
        };
    }*/

}