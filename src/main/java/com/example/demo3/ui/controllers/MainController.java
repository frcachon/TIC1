package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.ui.controllers.ClienteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class MainController {

    @FXML
    private AnchorPane pane_reg;

    @FXML
    void registerWindow(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("Register.fxml"));
        pane_reg.getChildren().setAll(pane);

        //Parent root = fxmlLoader.load(ClienteController.class.getResourceAsStream("Register.fxml"));
        //Stage stage = new Stage();
        //stage.setScene(new Scene(root));
        //stage.setTitle("Registrarse");
        //stage.show();
    }

    @FXML
    private ImageView caratula;

    //void display() {
    //Image image = new Image(getClass().getResourceAsStream("logo.jpg"));
    //caratula.setImage(image);
    //}


    @FXML
    void loginWindow(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("Login.fxml"));
        pane_reg.getChildren().setAll(pane);

        //Parent root = fxmlLoader.load(ClienteController.class.getResourceAsStream("Login.fxml"));
        //Stage stage = new Stage();
        //stage.setScene(new Scene(root));
        //stage.setTitle("Iniciar Sesión");
        //stage.show();

    }


}
