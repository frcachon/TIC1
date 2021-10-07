package com.example.demo3.ui.controllers;
import com.example.demo3.Demo3Application;
import com.example.demo3.exceptions.AdminYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.exceptions.OperadorYaExiste;
import com.example.demo3.managers.AdminMgr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AddAdminController {

    @Autowired
    private AdminMgr adminMgr;

    @FXML
    private AnchorPane reg_admin_pane;

    @FXML
    private TextField username_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button atras_button;

    @FXML
    private Button registrar_button;

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void addAdmin(ActionEvent event) {
        if (username_field.getText() == null || username_field.getText().equals("") || //chequeamos que nada sea nulo
                password_field.getText() == null || password_field.getText().equals("")) {

            showAlert(
                    "Faltan datos",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            String username = username_field.getText();
            String password = password_field.getText();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeAdmin.fxml"));
                reg_admin_pane.getChildren().setAll(pane);
                adminMgr.addAdmin(username, password);

            } catch (AdminYaExiste | OperadorYaExiste | NombreDeUsuarioYaExiste adminYaExiste) {
                showAlert(
                        "Nombre de usuario no disponible.",
                        "Por favor ingrese un nombre de usuario diferente.");
            } catch (InformacionInvalida informacionInvalida) {
                showAlert(
                        "Datos faltantes.",
                        "Por favor ingrese toda la informacion solicitada.");
            }
            catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);

        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        reg_admin_pane.getChildren().setAll(pane);
    }

}
