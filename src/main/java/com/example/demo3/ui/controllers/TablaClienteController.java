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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

@Component
    public class TablaClienteController implements Initializable {

        @Autowired
        private ClienteRepository clienteRepository;

        @FXML
        private TableView<Cliente> table;

        @FXML
        private TableColumn<Cliente,Long> cedulaColumn;

        @FXML
        private TableColumn<Cliente,String> nameColumn;

        @FXML
        private TableColumn<Cliente,String> emailColumn;

        ObservableList<Cliente> lista = FXCollections.observableArrayList();

        @Override
        public void initialize(URL location, ResourceBundle rb) {

            List<Cliente> q = (List<Cliente>) clienteRepository.findAll();

            try {
                for (int i = 0; i < q.size(); i++) {
                    lista.add(new Cliente(q.get(i).getDocument(), q.get(i).getName(), q.get(i).getEmail()));
                }
            }
            catch (Exception e) {

            }

            cedulaColumn.setCellValueFactory(new PropertyValueFactory<>("document"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

            table.setItems(lista);
        }

    }
