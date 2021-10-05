package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.persistence.ActividadRepository;
import com.example.demo3.persistence.OperadorRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HomeClienteController implements Initializable {

    @Autowired
    private ActividadRepository actvidadRepository;

    @Autowired
    private ActividadViewController actVC;

    @Autowired
    private OperadorRepository operadorRepository;
    // para traer el nombre del operador a partir de su id

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
    private Button buscar_button;

    @FXML
    private TableView<Actividad> tabla_actividades;

    @FXML
    private TableColumn<Actividad, String> nombre_actividad;

    @FXML
    private TableColumn<Actividad, String> nombre_operador;


    @FXML
    void ampliar_actividad(MouseEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        Actividad act = tabla_actividades.getSelectionModel().getSelectedItem();
        actVC.setActividad(act);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        home_pane.getChildren().setAll(pane);
    }


    @FXML
    void buscar(ActionEvent event) {
    }

    @FXML
    void editar_perfil(ActionEvent event) {

    }

    @FXML
    void editar_preferencia(ActionEvent event) {

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

    ObservableList<Actividad> lista;

   @Override
    public void initialize(URL location, ResourceBundle rb) {
        List<Actividad> q = (List<Actividad>) actvidadRepository.findAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tabla_actividades.setItems(lista);
        nombre_actividad.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        nombre_operador.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

/*    @Override
    public void start(Stage primaryStage) throws Exception {
        TableView<Actividad> table = new TableView<>();
        table.setRowFactory(tv -> {
            TableRow<String> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    String rowData = row.getItem();
                    System.out.println("Double click on: " + rowData.toString());
                }
            });
            return row;
        });
        table.getColumns().add(nombre_actividad("Item", Item::nameProperty));
        table.getColumns().add(nombre_operador("Value", Item::valueProperty));
    }*/

}
