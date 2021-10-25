package com.example.demo3.ui.controllers;
import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Admin;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Empleado;
import com.example.demo3.entities.Operador;
import com.example.demo3.managers.AdminMgr;
import com.example.demo3.managers.ClienteMgr;
import com.example.demo3.managers.EmpleadoMgr;
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
public class LoginController {

    @Autowired
    private HomeClienteController homeClienteController;

    @Autowired
    private HomeOperadorController homeOperadorController;

    @Autowired
    private AdminMgr adminMgr;

    @Autowired
    private EmpleadoMgr empleadoMgr;

    @Autowired
    private ClienteMgr clienteMgr;

    @FXML
    private AnchorPane login_pane;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField user_field;

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

            Admin admin = adminMgr.getAdminFromMailAndPassword(mail, contrasena);
            Cliente cliente = clienteMgr.getClienteFromMailAndPassword(mail, contrasena);
            Empleado empleado = empleadoMgr.getEmpleadoFromMailAndPassword(mail, contrasena);

            if (admin != null) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeAdmin.fxml"));
                login_pane.getChildren().setAll(pane);

            } else if (cliente != null) {
                if (!cliente.getBloqueado()) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                    homeClienteController.setCliente(cliente);
                    AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
                    login_pane.getChildren().setAll(pane);
                }
                else {
                    showAlert(
                            "Usuario bloqueado",
                            "Por favor, comuníquese con nosotros.");
                }

            } else if (empleado != null) {
                Operador op = empleadoMgr.getOperadorFromEmpleado(empleado.getId());
                if (!op.getBloqueado()) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                    homeOperadorController.setOperador(op);
                    AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeOperador.fxml"));
                    login_pane.getChildren().setAll(pane);
                }
                else {
                    showAlert(
                            "Operador bloqueado",
                            "Por favor, comuníquese con nosotros.");
                }

            } else {
                showAlert("Datos incorrectos", "Verifique los datos ingresados.");
            }

        }

    }

}