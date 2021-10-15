package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Operador;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.OperadorYaExiste;
import com.example.demo3.managers.ClienteMgr;
import com.example.demo3.managers.OperadorMgr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class EditarPerfilClienteController {

    Cliente cliente;
    void setCliente(Cliente cliente){this.cliente = cliente;}

    @Autowired
    private ClienteMgr clienteMgr;

    @Autowired
    private HomeClienteController homeClienteController;

    @FXML
    private AnchorPane editar_pane;

    @FXML
    private PasswordField contrasena_field;

    @FXML
    private PasswordField confirmacion_field;

    @FXML
    private CheckBox vacunado_field;

    @FXML
    private Button editar_button;

    /*@FXML
    private ImageView perfil_view;*/

    @FXML
    private Button foto_button;

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cunado algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();

    }

    @FXML
    void updateCliente(ActionEvent event) {
        String contrasena = contrasena_field.getText();
        String confirmar = confirmacion_field.getText();
        Boolean vacuna = vacunado_field.isSelected();

        if(!contrasena.equals("") && !confirmar.equals("")){

            if(!contrasena.equals(cliente.getContrasena())) {
                if (contrasena.equals(confirmar)) {
                    try {
                        clienteMgr.setCliente(cliente);
                        clienteMgr.updateCliente(contrasena, vacuna);
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                        homeClienteController.setCliente(cliente);
                        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
                        editar_pane.getChildren().setAll(pane);

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
                else {
                    showAlert("Datos incorrectos", "Las contraseñas no son iguales");
                }

            }
            else{
                showAlert("Datos incorrectos", "La contraseña es igual a la actual");
            }
        }

        else {
            try {
                clienteMgr.setCliente(cliente);
                clienteMgr.updateCliente(contrasena, vacuna);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                homeClienteController.setCliente(cliente);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
                editar_pane.getChildren().setAll(pane);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }
}
