package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Operador;
import com.example.demo3.entities.Reserva;
import com.example.demo3.managers.ActividadMgr;
import com.example.demo3.managers.ClienteMgr;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ValidarReservaController implements Initializable {

    @Autowired
    ReservaMgr reservaMgr;

    @Autowired
    ClienteMgr clienteMgr;

    @Autowired
    ActividadMgr actividadMgr;

    Operador operador;
    public void setOperador(Operador operador) {
        this.operador = operador;
    }

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
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeOperador.fxml"));
        val_pane.getChildren().setAll(pane);
    }

    @FXML
    void rechazarReserva(ActionEvent event) {
        Reserva reserva = tablaReservas.getSelectionModel().getSelectedItem();
        reservaMgr.setValidada(reserva, false);

    }

    @FXML
    void validarReserva(ActionEvent event) {
        Reserva reserva = tablaReservas.getSelectionModel().getSelectedItem();
        reservaMgr.setValidada(reserva, true);

    }

    ObservableList<Reserva> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Reserva> q = new ArrayList<>();
        List<Integer> actividadesDelOperador = actividadMgr.getIdActividadesFromOperador(operador.getId());

        for (int i = 0; i < actividadesDelOperador.size(); i++) {
            List<Reserva> resDeAct = reservaMgr.getNoValidadas(actividadesDelOperador.get(i));
            for (int j = 0; j < resDeAct.size(); j++) {
                q.add(resDeAct.get(j));
            }
        }

        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaReservas.setItems(lista);
        userColumn.setCellValueFactory(cellData -> {
            String username = clienteMgr.getUsernameFromId(cellData.getValue().getIdcliente());
            return new ReadOnlyStringWrapper(username);
        });
        actividadColumn.setCellValueFactory(cellData -> {
            String titulo = actividadMgr.getTituloFromId(cellData.getValue().getIdactividad());
            return new ReadOnlyStringWrapper(titulo);
        });
        cupoColumn.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
    }
}
