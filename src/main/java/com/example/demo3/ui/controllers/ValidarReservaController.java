package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

@Component
public class ValidarReservaController implements Initializable {

    @FXML
    private AnchorPane val_pane;

    @FXML
    private TableView<Reserva> tablaReservas;

    @FXML
    private TableColumn<Reserva, String> userColumn;

    @FXML
    private TableColumn<Reserva, String> actividadColumn;

    @FXML
    private TableColumn<Reserva, Integer> cupoColumn;

    @FXML
    private TableColumn<Reserva, LocalDate> fechaColumn;

    @FXML
    private TableColumn<Reserva, LocalTime> horaColumn;

    @FXML
    private Button bloquearButton;

    @FXML
    private Button desbloquearButton;

    @FXML
    private Button atrasButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeOperador.fxml"));
        val_pane.getChildren().setAll(pane);
    }

    @FXML
    void rechazarReserva(ActionEvent event) {

    }

    @FXML
    void validarReserva(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
