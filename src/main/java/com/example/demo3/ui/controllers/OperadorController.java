package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.managers.OperadorMgr;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.OperadorYaExiste;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class OperadorController implements Initializable {

    @Autowired
    private OperadorMgr operadorMgr;

    @FXML
    private AnchorPane pane_empresa;

    @FXML
    private TextField operador_name_field;

    @FXML
    private TextField phone_field;

    @FXML
    private ChoiceBox<String> depto_choice;

    @FXML
    private TextField email_field;

    @FXML
    private TextField direccion_field;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        pane_empresa.getChildren().setAll(pane);
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

    /*private void clean() {
        employee_name_field.setText(null);
        direccion_field.setText(null);
        phone_field.setText(null);
    }*/

    @FXML
    void addOperador(ActionEvent event) {
        if (operador_name_field.getText() == null || operador_name_field.getText().equals("")||
                depto_choice.getValue() == null || depto_choice.getValue().equals("") ||
                phone_field.getText() == null || phone_field.getText().equals("") ||
                email_field.getText() == null || email_field.getText().equals("") ||
                direccion_field.getText() == null || direccion_field.getText().equals(""))  {

            showAlert(
                    "Faltan datos!",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            try {
                String nombreEmpresa = operador_name_field.getText();
                String departamento = depto_choice.getValue();
                Long telefono = Long.valueOf(phone_field.getText());
                String emailContacto = email_field.getText();
                String direccion = direccion_field.getText();

                try {

                    operadorMgr.addOperador(nombreEmpresa,departamento,telefono,
                            emailContacto,direccion);

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                    AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeAdmin.fxml"));
                    pane_empresa.getChildren().setAll(pane);

                } catch (InformacionInvalida informacionInvalida) {
                    showAlert(
                            "Informacion invalida !",
                            "Los datos ingresados no son correctos.");
                } catch (OperadorYaExiste operadorYaExiste) {
                    showAlert(
                            "Los datos ingresados pertenecen a un operador ya registrado!",
                            " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos!",
                        "El teléfono no tiene el formato esperado (numerico).");

            }
        }

    }


    private final String[] deptos = {"Artigas", "Canelones", "Cerro Largo", "Colonia", "Durazno", "Flores", "Florida", "Lavalleja", "Maldonado", "Montevideo", "Paysandú", "Río Negro", "Rivera", "Rocha", "Salto", "San José", "Soriano", "Tacuarembó", "Treinta y Tres",};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        depto_choice.getItems().addAll(deptos);
    }
}