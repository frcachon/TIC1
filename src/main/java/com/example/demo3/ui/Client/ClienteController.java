package com.example.demo3.ui.Client;

import com.example.demo3.ClienteMgr;
import com.example.demo3.exceptions.ClienteYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteController {

    @Autowired
    private ClienteMgr clienteMgr;

    @FXML
    private Button btn;

    @FXML
    private TextField name_field;

    @FXML
    private Button register_button;

    @FXML
    private TextField mail_field;

    @FXML
    private TextField document_field;

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cunado algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private void clean() {
        name_field.setText(null);
        mail_field.setText(null);
        document_field.setText(null);
    }

    @FXML
    void addClient(ActionEvent event) {
        if (name_field.getText() == null || name_field.getText().equals("") ||     //chequeamos que nada sea nulo
                mail_field.getText() == null || mail_field.getText().equals("") ||
                document_field.getText() == null || document_field.getText().equals("")) {

            showAlert(
                    "Faltan datos!",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            try {
                Long name = Long.valueOf(name_field.getText());   //obtiene los valores de los campos
                String mail = mail_field.getText();
                String document = document_field.getText();

                try {

                    clienteMgr.addClient(name, mail, document);

                    showAlert("Cliente agregado", "Se agrego con exitosamente al cliente");

                    close(event);
                } catch (InformacionInvalida informacionInvalida) {
                    showAlert(
                            "Informacion invalida !",
                            "Los datos ingresados no son correctos.");
                } catch (ClienteYaExiste clienteYaExiste) {
                    showAlert(
                            "Los datos ingresados pertenecen a un cliente ya registrado!",
                            " ");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos!",
                        "El documento no tiene el formato esperado (numerico).");

            }
        }

    }


}



