package com.example.demo3.ui.controllers;
import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Admin;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Operador;
import com.example.demo3.exceptions.DocumentoYaExisteParaMismoPais;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.managers.ClienteMgr;
import com.example.demo3.managers.OperadorMgr;
import com.example.demo3.persistence.AdminRepository;
import com.example.demo3.persistence.ClienteRepository;
import com.example.demo3.persistence.OperadorRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

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

    @Autowired
    private ClienteRepository clienteRepository;

    // falta repository de empleado de operador

    @Autowired
    private AdminRepository adminRepository;

    @FXML
    void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Main.fxml"));
        login_pane.getChildren().setAll(pane);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void loginAction(ActionEvent event) throws IOException {

        if (user_field.getText() == null || user_field.getText().equals("") || //chequeamos que nada sea nulo
            password_field.getText() == null || password_field.getText().equals("")) {

            showAlert(
                "Faltan datos",
                "Por favor, ingrese toda la informacion requerida");

        } else {

            String mail = user_field.getText();
            String contrasena = password_field.getText();

            Admin admin = adminRepository.findByMailAndPassword(mail, contrasena);
            Cliente cliente = clienteRepository.findByMailAndAndContrasena(mail, contrasena);
            // falta para empleado de operador

            if (admin != null) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeAdmin.fxml"));
                login_pane.getChildren().setAll(pane);
            } else if (cliente != null) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
                login_pane.getChildren().setAll(pane);
            } else {
                showAlert("Datos incorrectos", "Verifique los datos ingresados.");
            }

        }

    }

}