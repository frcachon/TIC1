package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

@Component
public class ReservarController implements Initializable {

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
    private TableView<Reserva> tablaHoras;

    @FXML
    private TableColumn<Reserva, LocalTime> columnaHoras;

    @FXML
    private TextField cupoField;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        reserva_pane.getChildren().setAll(pane);
    }

    @FXML
    void buscarHora(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreLabel.setText(actividad.getTitulo());
    }

}