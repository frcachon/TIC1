package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HomeAdminController {

    @FXML
    private AnchorPane home_pane;

    @FXML
    private Button atrasButton;

    @FXML
    private Button bloq_operador;

    @FXML
    private Button val_operador;

    @FXML
    private Button reg_operador;

    @FXML
    private Button agregar_empleado;

    @FXML
    private Button agregar_admin;

    @FXML
    void bloq_operador(ActionEvent event) {

    }

    @FXML
    void agregar_admin(ActionEvent event) {

    }

    @FXML
    void agregar_empleado(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("AddEmpleado.fxml"));
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
    void reg_operador(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("AddOperador.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void val_operador(ActionEvent event) {

    }

}
