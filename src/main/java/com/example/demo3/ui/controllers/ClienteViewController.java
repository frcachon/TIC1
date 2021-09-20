package com.example.demo3.ui.controllers;

import com.example.demo3.entities.Cliente;
import com.example.demo3.persistence.ClienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
    public class ClienteViewController implements Initializable {

    @Autowired
    private ClienteRepository clienteRepository;

    @FXML
    private TableView<Cliente> table;

    @FXML
    private TableColumn<Cliente, Long> cedulaColumn;

    @FXML
    private TableColumn<Cliente, String> nameColumn;

    @FXML
    private TableColumn<Cliente, String> emailColumn;

    ObservableList<Cliente> lista;

    @Override
    public void initialize(URL location, ResourceBundle rb) {

        List<Cliente> q = (List<Cliente>) clienteRepository.findAll();
        lista = FXCollections.observableArrayList();

        lista.addAll(q);


        table.setItems(lista);
        cedulaColumn.setCellValueFactory(new PropertyValueFactory<>("document")); // hay que poner los nombres
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email")); // de los atributos de la clase
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // en el orden de las cols de la DB

    }
}
