package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ComentariosController implements Initializable {

    Actividad actividad;
    Cliente cliente;

    public void setData(Actividad actividad, Cliente cliente) {
        this.actividad = actividad;
        this.cliente = cliente;
    }

    @FXML
    private AnchorPane paneComentarios;

    @FXML
    private ChoiceBox<Integer> puntosChoiceBox;

    @FXML
    private TextArea comentField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane comentariosGrid;

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void calificar(ActionEvent event) {
        if (puntosChoiceBox.getValue() != null) {
            // me fijo si ya existe puntuacion de ese cliente. Si existe, la modifico. Si no, la creo
        }

    }

    @FXML
    void comentar(ActionEvent event) {
        String comentario = comentField.getText();
        if (comentario.length() >= 200) {
            showAlert(
                    "Demasiados caracteres",
                    "El límite de caracteres para un comentario es de 200.");
        }
        else {
            // crear entidad comentario, persistirla y refrescar tabla
        }

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        paneComentarios.getChildren().setAll(pane);
    }

    private final Integer[] puntos = {1, 2, 3, 4, 5};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        puntosChoiceBox.getItems().addAll(puntos);
        comentField.setWrapText(true);
    }

}