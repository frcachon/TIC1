package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Reserva;
import com.example.demo3.managers.ReservaMgr;
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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

@Component
public class ReservarController implements Initializable {

    @Autowired
    private ReservaMgr reservaMgr;

    Actividad actividad;
    Cliente cliente;

    public void setData(Actividad actividad, Cliente cliente) {
        this.actividad = actividad;
        this.cliente = cliente;
    }

    @FXML
    private AnchorPane reserva_pane;

    @FXML
    private Label nombreLabel;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField cupoField;

    @FXML
    private ChoiceBox<LocalTime> horasChoice;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        reserva_pane.getChildren().setAll(pane);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void buscarHora(ActionEvent event) {
        LocalDate fecha = dateField.getValue();
        Integer cupo = 0;
        if (!cupoField.getText().equals("")) {
            try {
                cupo = parseInt(cupoField.getText());
            }
            catch (Exception e) {
                showAlert("Cantidad inválida", "Por favor ingrese una cantidad numérica y mayor a cero.");
                return;
            }

            if (cupo <= 0) {
                showAlert("Cantidad inválida", "Por favor ingrese una cantidad numérica y mayor a cero.");
                return;
            }

        }
        if (cupoField.getText().equals("") || dateField.getValue() == null) {
            showAlert("Ingrese todos los datos", "Por favor ingrese una fecha y la cantidad de asistentes.");
            return;
        }
        if (dateField.getValue().isBefore(LocalDate.now())) {
            showAlert("Fecha inválida", "Por favor ingrese una fecha futura.");
        }


    }

    @FXML
    void confirmarReserva(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreLabel.setText(actividad.getTitulo());
    }

}