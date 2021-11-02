package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Reserva;
import com.example.demo3.managers.ReservaMgr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
    private ChoiceBox<String> horasChoice;

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
        if (actividad.getRequiere_vacuna() && !cliente.getVacuna_covid()) {
            showAlert("No vacunado", "Para poder realizar reservas en esta actividad, debe estar vacunado.");
        }
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
        //if (actividad.) si requiere vacunas y yo no estoy
        List<Reserva> reservasDelDia = reservaMgr.getFromActivityInDate(actividad.getId(), fecha);
        Integer apertura = actividad.getApertura().getHour();
        Integer cierre = actividad.getCierre().getHour();


        List<String> horasDisponibles = new ArrayList<>();

        for (int i = apertura; i < cierre; i++) {
            int cupos = 0;
            if (reservasDelDia.size() > 0) {
                for (int j = 0; j < reservasDelDia.size(); j++) {
                    if (i == reservasDelDia.get(j).getHora().getHour()) {
                        cupos = cupos + reservasDelDia.get(j).getCantidad();
                    }
                }
            }
            if (cupos + cupo > actividad.getCupo()) {
                continue;
            }
            else {
                horasDisponibles.add(i+":00");
            }
        }

        ObservableList<String> observableList = FXCollections.observableList(horasDisponibles);
        horasChoice.setItems(observableList);
    }

    @FXML
    void confirmarReserva(ActionEvent event) throws IOException {
        if (horasChoice.getValue() != null && dateField.getValue() != null && parseInt(cupoField.getText()) > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime hora = LocalTime.parse(horasChoice.getValue(), formatter);
            reservaMgr.crearReserva(cliente.getId(), actividad.getId(), dateField.getValue(), hora, parseInt(cupoField.getText()));
            showAlert("Reserva confirmada", "Esperamos que lo disfrutes. Puedes ver tu reserva en la sección 'Itinerario'.");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
            AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
            reserva_pane.getChildren().setAll(pane);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nombreLabel.setText(actividad.getTitulo());
    }

}