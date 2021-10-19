package com.example.demo3.ui.controllers;
import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Operador;
import com.example.demo3.persistence.OperadorRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.input.MouseEvent;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ActividadThumbController implements Initializable {

    @Autowired
    private ActividadViewController actividadViewController;

    AnchorPane home_pane;
    void setPane(AnchorPane pane) {
        this.home_pane = pane;
    }

    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private HomeClienteController homeClienteController;

    @FXML
    private ImageView imagen;

    @FXML
    private Label nombreLabel;

    @FXML
    private Label puntuacionLabel;

    @FXML
    private Label operadorLabel;

    @FXML
    private Label descripcionLabel;

    public List<Actividad> actividades = new ArrayList<>();

    public void setData(Actividad actividad) {
        actividades.add(actividad);
        //hay que traer la imagen de la BD
        //imagen.setImage();
        nombreLabel.setText(actividad.getTitulo());
        puntuacionLabel.setText(actividad.getPromediopuntuaciones() + " estrellas.");
        operadorLabel.setText(operadorRepository.findOperadorById(actividad.getIdoperador()).getEmpresa());
        descripcionLabel.setWrapText(true);
        descripcionLabel.setText(actividad.getDescripcion());
    }

    @FXML
    void ampliarActividad(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        //actividadViewController.setActividad(actividad);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        home_pane.getChildren().setAll(pane);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}