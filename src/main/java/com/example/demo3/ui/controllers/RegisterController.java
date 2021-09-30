package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class RegisterController {

    @FXML
    private AnchorPane pane_reg;

    @FXML
    void agregarClientAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("AddClient.fxml"));
        pane_reg.getChildren().setAll(pane);

        //Parent root = fxmlLoader.load(ClienteController.class.getResourceAsStream("AddClient.fxml"));
        //Stage stage = new Stage();
        //stage.setScene(new Scene(root));
        //stage.setTitle("Registrar un Cliente");
        //stage.show();

    }

    @FXML
    void agregarOperadorAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("AddOperador.fxml"));
        pane_reg.getChildren().setAll(pane);

        //Parent root = fxmlLoader.load(ClienteController.class.getResourceAsStream("AddOperador.fxml"));
        //Stage stage = new Stage();
        //stage.setScene(new Scene(root));
        //stage.setTitle("Registrar un Operador");
        //stage.show();
    }

    @FXML
    void verClientsAction(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("ClienteView.fxml"));
        pane_reg.getChildren().setAll(pane);

        //Parent root = fxmlLoader.load(ClienteController.class.getResourceAsStream("ClienteView.fxml"));
        //Stage stage = new Stage();
        //stage.setScene(new Scene(root));
        //stage.setTitle("Ver clientes");
        //stage.show();
    }
}