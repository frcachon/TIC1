package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Interes;
import com.example.demo3.managers.ActividadMgr;
import com.example.demo3.managers.InteresMgr;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EditarInteresesActividadController implements Initializable {

    @Autowired
    private InteresMgr interesMgr;

    Actividad actividad;
    void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @FXML
    private AnchorPane pane_intereses;

    @FXML
    private ChoiceBox<String> tagsChoiceBox;

    @FXML
    private Button agregarButton;

    @FXML
    private TableView<Interes> tablaTags;

    @FXML
    private TextField newField;

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
    void crearInteres(ActionEvent event) {
        //String nuevo = newField.getText();
        // crear objeto
        //inters.add(objeto)
        List<Interes> q = (List<Interes>) interesMgr.getAll();
        List<String> inters = new ArrayList<>();
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) != null) {
                inters.add(q.get(i).toString());
            }
        }
        tagsChoiceBox.getItems().addAll(inters);

    }

    @FXML
    void eliminarInteres(ActionEvent event) {

    }

    @FXML
    void finalizar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeOperador.fxml"));
        pane_intereses.getChildren().setAll(pane);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Interes> q = (List<Interes>) interesMgr.getAll();
        List<String> inters = new ArrayList<>();
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) != null) {
                inters.add(q.get(i).toString());
            }
        }
        tagsChoiceBox.getItems().addAll(inters);




    }

}