package com.example.demo3.ui.controllers;

import com.example.demo3.managers.OperadorMgr;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.OperadorYaExiste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class OperadorController implements Initializable {

    @Autowired
    private OperadorMgr operadorMgr;

    @FXML
    private TextField name_field;

    @FXML
    private TextField phone_field;

    @FXML
    private Button siguiente_button;

    @FXML
    private TextField password_field;

    @FXML
    private ChoiceBox<String> depto_choice;

    @FXML
    private TextField email_field;

    @FXML
    private TextField direccion_field;

    @FXML
    void descripOffersWindow(ActionEvent event) {
    }


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
        direccion_field.setText(null);
        phone_field.setText(null);
    }

    @FXML
    void addOperador(ActionEvent event) {
        if (name_field.getText() == null || name_field.getText().equals("") ||     //chequeamos que nada sea nulo
                phone_field.getText() == null || direccion_field.getText().equals("") ||
                phone_field.getText() == null || direccion_field.getText().equals("")) {

            showAlert(
                    "Faltan datos!",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            try {
                Long identificador = Long.valueOf(phone_field.getText());   //obtiene los valores de los campos
                String mail = direccion_field.getText();
                String name = name_field.getText();

                try {

                    operadorMgr.addOperador(identificador, name, mail);

                    showAlert("Operador agregado", "Se agrego con exitosamente al operador");

                    close(event);
                } catch (InformacionInvalida informacionInvalida) {
                    showAlert(
                            "Informacion invalida !",
                            "Los datos ingresados no son correctos.");
                } catch (OperadorYaExiste operadorYaExiste) {
                    showAlert(
                            "Los datos ingresados pertenecen a un operador ya registrado!",
                            " ");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos!",
                        "El ID no tiene el formato esperado (numerico).");

            }
        }

    }


    private String[] deptos = {"Artigas", "Canelones", "Cerro Largo", "Colonia", "Durazno", "Flores", "Florida", "Lavalleja", "Maldonado", "Montevideo", "Paysandú", "Río Negro", "Rivera", "Rocha", "Salto", "San José", "Soriano", "Tacuarembó", "Treinta y Tres",};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        depto_choice.getItems().addAll(deptos);
    }
}