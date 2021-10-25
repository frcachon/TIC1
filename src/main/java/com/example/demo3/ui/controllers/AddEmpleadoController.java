package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Operador;
import com.example.demo3.exceptions.NombreDeUsuarioYaExiste;
import com.example.demo3.managers.EmpleadoMgr;
import com.example.demo3.managers.OperadorMgr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class AddEmpleadoController implements Initializable {

    @Autowired
    private EmpleadoMgr empleadoMgr;

    @Autowired
    private OperadorMgr operadorMgr;

    @FXML
    private AnchorPane emp_pane;

    @FXML
    private TextField username_field;

    @FXML
    private ChoiceBox<String> operador_choiceBox;

    @FXML
    private PasswordField password_field;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        emp_pane.getChildren().setAll(pane);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void registrarEmpleado(ActionEvent event) {
        if (username_field.getText() == null || username_field.getText().equals("") || //chequeamos que nada sea nulo
                password_field.getText() == null || password_field.getText().equals("")) {

            showAlert(
                    "Faltan datos",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            String username = username_field.getText();
            String password = password_field.getText();
            String empresa = operador_choiceBox.getValue();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeAdmin.fxml"));
                emp_pane.getChildren().setAll(pane);
                empleadoMgr.addEmpleado(username, password, empresa);

            } catch (NombreDeUsuarioYaExiste clienteYaExiste) {
                showAlert(
                        "Ya se registró un cliente con ese nombre de usuario",
                        "Por favor ingrese un nombre de usuario diferente.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Operador> q = operadorMgr.getAll();

        String[] opes = new String[q.size()];
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) != null) {
                opes[i] = q.get(i).getEmpresa();
            }
        }
        operador_choiceBox.getItems().addAll(opes);
    }

}