package com.example.demo3.ui.controllers;
import com.example.demo3.Demo3Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginController {

    @FXML
    private AnchorPane login_pane;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField user_field;

    @FXML
    private Button iniciar_sesion;

    @FXML
    private Button atras_button;

    @FXML
    void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Main.fxml"));
        login_pane.getChildren().setAll(pane);
    }

    @FXML
    void loginAction(ActionEvent event) {

    }

}
