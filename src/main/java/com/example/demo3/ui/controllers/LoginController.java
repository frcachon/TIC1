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
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField user_field;

    @FXML
    private Button iniciar_sesion;

    @FXML
    void loginAction(ActionEvent event) {

    }

}
