package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Operador;
import com.example.demo3.managers.ActividadMgr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

@Component
public class EditActividadController implements Initializable {

    @Autowired
    private ActividadMgr actividadMgr;

    Actividad actividad;

    void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    @FXML
    private AnchorPane act_pane;

    @FXML
    private TextField horarioApertura_field;

    @FXML
    private TextField titulo_field;

    @FXML
    private CheckBox reservas_check;

    @FXML
    private ImageView imagenVw;

    @FXML
    private TextField horarioCierre_field;

    @FXML
    private TextArea descripcion_field;

    @FXML
    private TextField cupo_field;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeOperador.fxml"));
        act_pane.getChildren().setAll(pane);
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void seleccionarImagen(ActionEvent event) {

    }

    @FXML
    public void updateActividad(ActionEvent actionEvent) {
        try {
            String titulo = titulo_field.getText();
            String descripcion = descripcion_field.getText();
            LocalTime apertura = LocalTime.parse(horarioApertura_field.getText());
            LocalTime cierre = LocalTime.parse(horarioCierre_field.getText());
            Integer cupo = Integer.valueOf(cupo_field.getText());
            Boolean utiliza_reservas = reservas_check.isSelected();

            try {
                actividadMgr.setActividad(actividad);
                actividadMgr.updateActividad(titulo,descripcion,apertura,cierre,cupo,utiliza_reservas);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeOperador.fxml"));
                act_pane.getChildren().setAll(pane);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

            showAlert(
                    "Datos incorrectos",
                    "Algún dato no tiene el formato esperado.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titulo_field.setText(actividad.getTitulo());
        descripcion_field.setText(actividad.getDescripcion());
        descripcion_field.setWrapText(true);
        cupo_field.setText(actividad.getCupo().toString());
        horarioApertura_field.setText(actividad.getApertura().toString());
        horarioCierre_field.setText(actividad.getCierre().toString());
        reservas_check.setSelected(actividad.getUtiliza_reservas());
    }

}