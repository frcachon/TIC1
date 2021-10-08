package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Operador;
import com.example.demo3.managers.ActividadMgr;
import com.example.demo3.persistence.ActividadRepository;
import com.example.demo3.persistence.OperadorRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ValidarActividadController implements Initializable {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private ActividadMgr actividadMgr;

    @Autowired
    private OperadorRepository operadorRepository;

    @FXML
    private AnchorPane val_pane;

    @FXML
    private TableView<Actividad> tablaActividades;

    @FXML
    private TableColumn<Actividad, String> nameColumn;

    @FXML
    private TableColumn<Actividad, String> operadorColumn;

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
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        val_pane.getChildren().setAll(pane);
    }

    ObservableList<Actividad> lista;

    @FXML
    void rechazarActividad(ActionEvent event) {
        Actividad act = tablaActividades.getSelectionModel().getSelectedItem();
        actividadMgr.rechazarActividad(act);

        List<Actividad> q = (List<Actividad>) actividadRepository.findAllByValidadaIsFalse();
        lista.removeAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaActividades.setItems(lista);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        operadorColumn.setCellValueFactory(cellData -> {
            Integer id_operador = cellData.getValue().getIdoperador();
            String nombre_operador = operadorRepository.findOperadorById(id_operador).getEmpresa();
            return new ReadOnlyStringWrapper(nombre_operador);
        });
    }

    @FXML
    void validarActividad(ActionEvent event) {
        Actividad act = tablaActividades.getSelectionModel().getSelectedItem();
        actividadMgr.validarActividad(act);

        List<Actividad> q = (List<Actividad>) actividadRepository.findAllByValidadaIsFalse();
        lista.removeAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaActividades.setItems(lista);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        operadorColumn.setCellValueFactory(cellData -> {
            Integer id_operador = cellData.getValue().getIdoperador();
            String nombre_operador = operadorRepository.findOperadorById(id_operador).getEmpresa();
            return new ReadOnlyStringWrapper(nombre_operador);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Actividad> q = (List<Actividad>) actividadRepository.findAllByValidadaIsFalse();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaActividades.setItems(lista);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        operadorColumn.setCellValueFactory(cellData -> {
            Integer id_operador = cellData.getValue().getIdoperador();
            String nombre_operador = operadorRepository.findOperadorById(id_operador).getEmpresa();
            return new ReadOnlyStringWrapper(nombre_operador);
        });
    }
}
