package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Operador;
import com.example.demo3.persistence.ActividadRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HomeOperadorController implements Initializable {

    Operador operador;
    void setOperador(Operador operador) {
        this.operador = operador;
    }

    @Autowired
    private EditarPerfilOperadorController editarPerfilOperadorController;

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private ActividadViewController actVC;

    @FXML
    private AnchorPane home_pane;

    @FXML
    private Button atrasButton;

    @FXML
    private Button reservas_button;

    @FXML
    private Button editar_perfil_button;

    @FXML
    private Button buscar_button;

    @FXML
    private TableView<Actividad> tabla_actividades;

    @FXML
    private TableColumn<Actividad, String> actvidadColumn;

    @FXML
    private TableColumn<Actividad, String> descripcionColumn;

    @FXML
    private Label username_label;

    @FXML
    private Button agregar_actividad_button;

    @FXML
    void agregarActividad(ActionEvent event) {

    }

    @FXML
    void ampliar_actividad(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        Actividad act = tabla_actividades.getSelectionModel().getSelectedItem();
        actVC.setActividad(act);

        //AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        //home_pane.getChildren().setAll(pane);
    }

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void editarPerfil(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        editarPerfilOperadorController.setOperador(operador);
        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("EditarPerfilOperador.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Login.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void verReservas(ActionEvent event) {

    }

    ObservableList<Actividad> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username_label.setText(operador.getEmpresa());
        List<Actividad> q = (List<Actividad>) actividadRepository.findAllByIdoperador(operador.getId());
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tabla_actividades.setItems(lista);
        actvidadColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

}
