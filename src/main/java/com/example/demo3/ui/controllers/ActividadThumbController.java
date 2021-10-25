package com.example.demo3.ui.controllers;
import com.example.demo3.entities.Actividad;
import com.example.demo3.managers.OperadorMgr;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActividadThumbController {

    AnchorPane home_pane;
    void setPane(AnchorPane pane) {
        this.home_pane = pane;
    }

    @Autowired
    private OperadorMgr operadorMgr;

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
        puntuacionLabel.setText(actividad.getPromediopuntuaciones() + " estrellas");
        operadorLabel.setText(operadorMgr.getOperadorFromId(actividad.getIdoperador()).getEmpresa());
        descripcionLabel.setWrapText(true);
        descripcionLabel.setText(actividad.getDescripcion());
    }

}