package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Reserva;
import com.example.demo3.managers.ActividadMgr;
import com.example.demo3.managers.ReservaMgr;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ItinerarioClienteController implements Initializable {

    Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Autowired
    private ReservaMgr reservaMgr;

    @Autowired
    private ActividadMgr actividadMgr;

    @FXML
    private AnchorPane backpane;

    @FXML
    private TableView<Reserva> tabla_reservas;

    @FXML
    private TableColumn<Reserva, String> actividadcolumn;

    @FXML
    private TableColumn<Reserva, LocalDate> diacolumn;

    @FXML
    private TableColumn<Reserva, LocalTime> horacolumn;

    @FXML
    private TableColumn<Reserva, Integer> cantidadcolumn;

    @FXML
    private TableColumn<Reserva, String> estadocolumn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeCliente.fxml"));
        backpane.getChildren().setAll(pane);
    }

    ObservableList<Reserva> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Reserva> q = reservaMgr.getReservasFromCliente(cliente.getId());
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tabla_reservas.setItems(lista);

        actividadcolumn.setCellValueFactory(cellData -> {
            String tit = actividadMgr.getTituloFromId(cellData.getValue().getIdactividad());
            return new ReadOnlyStringWrapper(tit);
        });
        diacolumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horacolumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        cantidadcolumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        estadocolumn.setCellValueFactory(cellData -> {
            Boolean estado = cellData.getValue().getValidada();
            String estadoAsString;
            if (estado != null) {
                if (estado) {
                    estadoAsString = "Confirmada";
                } else {
                    estadoAsString = "Rechazada";
                }
            }
            else {
                estadoAsString = "Pendiente";
            }
            return new ReadOnlyStringWrapper(estadoAsString);
        });

    }

}