package com.example.demo3.ui.controllers;
import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Operador;
import com.example.demo3.persistence.OperadorRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.input.MouseEvent;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ActividadThumbController {

    @Autowired
    private ActividadViewController actividadViewController;

    Actividad actividad;

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

    public void setData(Actividad actividad) {
        this.actividad = actividad;
        //hay que traer la imagen de la BD
        //imagen.setImage();
        nombreLabel.setText(actividad.getTitulo());
        puntuacionLabel.setText(actividad.getPromediopuntuaciones() + " estrellas.");
        operadorLabel.setText(operadorRepository.findOperadorById(this.actividad.getIdoperador()).getEmpresa());
        descripcionLabel.setWrapText(true);
        descripcionLabel.setText(actividad.getDescripcion());
    }

    @FXML
    void ampliarActividad(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        actividadViewController.setActividad(actividad);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        home_pane.getChildren().setAll(pane);
    }

}