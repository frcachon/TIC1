package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Cliente;
import com.example.demo3.persistence.ClienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Component
    public class ClienteViewController implements Initializable {

    @Autowired
    private ClienteRepository clienteRepository;

    @FXML
    private AnchorPane clienteViewPane;

    @FXML
    private TableView<Cliente> table;

    @FXML
    private TableColumn<Cliente, String> usernameColumn;

    @FXML
    private TableColumn<Cliente, String> mailColumn;

    @FXML
    private TableColumn<Cliente, Long> documentoColumn;

    @FXML
    private TableColumn<Cliente, String> tipoColumn;

    @FXML
    private TableColumn<Cliente, LocalDate> nacimientoColumn;

    @FXML
    private TableColumn<Cliente, Boolean> vacunaColumn;

    @FXML
    private TableColumn<Cliente, String> paisColumn;

    @FXML
    private Button atrasButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("Register.fxml"));
        clienteViewPane.getChildren().setAll(pane);
    }

    ObservableList<Cliente> lista;

    @Override
    public void initialize(URL location, ResourceBundle rb) {

        List<Cliente> q = (List<Cliente>) clienteRepository.findAll();
        lista = FXCollections.observableArrayList();

        lista.addAll(q);


        table.setItems(lista);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username")); // hay que poner los nombres
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail")); // de los atributos de la clase
        documentoColumn.setCellValueFactory(new PropertyValueFactory<>("documento")); // en el orden de las cols de la DB
        tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo_documento"));
        nacimientoColumn.setCellValueFactory(new PropertyValueFactory<>("fecha_nacimiento"));
        vacunaColumn.setCellValueFactory(new PropertyValueFactory<>("vacuna_covid"));
        paisColumn.setCellValueFactory(new PropertyValueFactory<>("pais"));
    }
}
