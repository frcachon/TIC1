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
public class OperadorChoiceController {

    @FXML
    private AnchorPane pane_operador;

    @FXML
    private Button empleado;

    @FXML
    private Button empresa;

    @FXML
    private Button atrasButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Register.fxml"));
        pane_operador.getChildren().setAll(pane);
    }

    @FXML
    void registrarEmpleado(ActionEvent event) {

    }

    @FXML
    void registrarEmpresa(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(RegisterController.class.getResourceAsStream("AddOperador.fxml"));
        pane_operador.getChildren().setAll(pane);
    }

}

