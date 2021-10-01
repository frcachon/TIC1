package com.example.demo3.ui.controllers;

import com.example.demo3.entities.Interes;
import com.example.demo3.persistence.InteresRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class InteresesClienteController implements Initializable {

    @Autowired
    private InteresRepository interesRepository;

    @FXML
    private AnchorPane pane_intereses;

    @FXML
    private Button agregarButton;

    @FXML
    private TableView<Interes> tablaIntereses;

    @FXML
    private TableColumn<Interes, String> interesesColumn;

    @FXML
    private Button finalizarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    void agregarPreferencia(ActionEvent event) {

    }

    @FXML
    void eliminarInteres(ActionEvent event) {

    }

    @FXML
    void endRegister(ActionEvent event) {

    }

    ObservableList<Interes> lista;

    @Override
    public void initialize(URL location, ResourceBundle rb) {

        List<Interes> q = (List<Interes>) interesRepository.findAll();
        lista = FXCollections.observableArrayList();

        lista.addAll(q);

        tablaIntereses.setItems(lista);
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("interes"));
    }

}
