package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

@Component
public class MainController {

    @FXML
    private AnchorPane pane_reg;

    @FXML
    private ImageView caratula;

    @FXML
    void registerWindow(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("AddClient.fxml"));
        pane_reg.getChildren().setAll(pane);
    }

    @FXML
    void loginWindow(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Login.fxml"));
        pane_reg.getChildren().setAll(pane);
    }

}