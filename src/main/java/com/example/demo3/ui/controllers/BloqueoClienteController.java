package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.managers.ClienteMgr;
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
import java.util.List;
import java.util.ResourceBundle;

@Component
public class BloqueoClienteController implements Initializable {

    @Autowired
    private ClienteMgr clienteMgr;

    @FXML
    private AnchorPane bloq_pane;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> emailColumn;

    @FXML
    private TableColumn<Cliente, Long> docColumn;

    @FXML
    private TableColumn<Cliente, String> estadoColumn;

    @FXML
    private TextField field;

    @FXML
    void busqueda(KeyEvent event) {
        List<Cliente> q = clienteMgr.getClientesFromMailContaining(field.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(q);

        tablaClientes.setItems(lista);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        docColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
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
    void bloquearCliente(ActionEvent event) {
        Cliente cli = tablaClientes.getSelectionModel().getSelectedItem();
        clienteMgr.bloquearCliente(cli);

        List<Cliente> q = clienteMgr.getClientesFromMailContaining(field.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(q);
        tablaClientes.setItems(lista);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        docColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
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
    void desbloquearCliente(ActionEvent event) {
        Cliente cli = tablaClientes.getSelectionModel().getSelectedItem();
        clienteMgr.desbloquearCliente(cli);

        List<Cliente> q = clienteMgr.getClientesFromMailContaining(field.getText());
        lista = FXCollections.observableArrayList();
        lista.removeAll();
        lista.addAll(q);
        tablaClientes.setItems(lista);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        docColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
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

    ObservableList<Cliente> lista;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Cliente> q = clienteMgr.getAll();
        lista = FXCollections.observableArrayList();
        lista.addAll(q);
        tablaClientes.setItems(lista);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        docColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
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
