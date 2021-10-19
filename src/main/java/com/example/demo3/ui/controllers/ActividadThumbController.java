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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javafx.scene.input.MouseEvent;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ActividadThumbController {

    @Autowired
    private ActividadViewController actividadViewController;

    AnchorPane home_pane;
    void setPane(AnchorPane pane) {
        this.home_pane = pane;
    }

    @Autowired
    private OperadorRepository operadorRepository;

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
        if (actividad.getImagenactividad() != null) {
            InputStream is = new ByteArrayInputStream(actividad.getImagenactividad());
            imagen.setImage(new Image(is));
        }
        nombreLabel.setText(actividad.getTitulo());
        puntuacionLabel.setText(actividad.getPromediopuntuaciones() + " estrellas.");
        operadorLabel.setText(operadorRepository.findOperadorById(actividad.getIdoperador()).getEmpresa());
        descripcionLabel.setWrapText(true);
        descripcionLabel.setText(actividad.getDescripcion());
    }

}