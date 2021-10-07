package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Operador;
import com.example.demo3.managers.OperadorMgr;
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
public class BloqueoOperadorController implements Initializable {

    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private OperadorMgr operadorMgr;

    @FXML
    private AnchorPane bloq_pane;

    @FXML
    private TableView<Operador> tablaOperadores;

    @FXML
    private TableColumn<Operador, String> nameColumn;

    @FXML
    private TableColumn<Operador, String> emailColumn;

    @FXML
    private TableColumn<Operador, String> estadoColumn;

    @FXML
    private Button bloquearButton;

    @FXML
    private Button desbloquearButton;

    @FXML
    private Button atrasButton;

    @FXML
    void bloquearOperador(ActionEvent event) {
        Operador ope = tablaOperadores.getSelectionModel().getSelectedItem();
        operadorMgr.bloquearOperador(ope);

        List<Operador> q = (List<Operador>) operadorRepository.findAll();
        lista.removeAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaOperadores.setItems(lista);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailcontacto"));
        estadoColumn.setCellValueFactory(cellData -> {
            boolean estado = cellData.getValue().getBloqueado();
            String estadoAsString;
            if(estado) {
                estadoAsString = "Bloqueado";
            }
            else {
                estadoAsString = "Habilitado";
            }
            return new ReadOnlyStringWrapper(estadoAsString);
        });

    }

    @FXML
    void desbloquearOperador(ActionEvent event) {
        Operador ope = tablaOperadores.getSelectionModel().getSelectedItem();
        operadorMgr.desbloquearOperador(ope);

        List<Operador> q = (List<Operador>) operadorRepository.findAll();
        lista.removeAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaOperadores.setItems(lista);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailcontacto"));
        estadoColumn.setCellValueFactory(cellData -> {
            boolean estado = cellData.getValue().getBloqueado();
            String estadoAsString;
            if(estado) {
                estadoAsString = "Bloqueado";
            }
            else {
                estadoAsString = "Habilitado";
            }
            return new ReadOnlyStringWrapper(estadoAsString);
        });
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeAdmin.fxml"));
        bloq_pane.getChildren().setAll(pane);
    }

    ObservableList<Operador> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Operador> q = (List<Operador>) operadorRepository.findAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaOperadores.setItems(lista);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailcontacto"));
        estadoColumn.setCellValueFactory(cellData -> {
            boolean estado = cellData.getValue().getBloqueado();
            String estadoAsString;
            if(estado) {
                estadoAsString = "Bloqueado";
            }
            else {
                estadoAsString = "Habilitado";
            }
            return new ReadOnlyStringWrapper(estadoAsString);
        });

    }

}
