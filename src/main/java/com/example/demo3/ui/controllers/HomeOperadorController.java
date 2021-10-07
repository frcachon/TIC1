package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Operador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class HomeOperadorController implements Initializable {

    Operador operador;
    void setOperador(Operador operador) {
        this.operador = operador;
    }

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
    private TableView<?> tabla_actividades;

    @FXML
    private TableColumn<?, ?> actvidadColumn;

    @FXML
    private TableColumn<?, ?> descripcionColumn;

    @FXML
    private Label username_label;

    @FXML
    void ampliar_actividad(MouseEvent event) {

    }

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void editarPerfil(ActionEvent event) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username_label.setText(operador.getEmpresa());
    }

}
