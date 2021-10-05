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
public class AddEmpleadoController {

    @FXML
    private AnchorPane emp_pane;

    @FXML
    private Button atras_button;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        emp_pane.getChildren().setAll(pane);
    }

}
