package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Comentario;
import com.example.demo3.managers.CalificacionMgr;
import com.example.demo3.managers.ClienteMgr;
import com.example.demo3.managers.ComentarioMgr;
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
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ComentariosController implements Initializable {

    @Autowired
    private CalificacionMgr calificacionMgr;

    @Autowired
    private ComentarioMgr comentarioMgr;

    @Autowired
    private ClienteMgr clienteMgr;

    Actividad actividad;
    Cliente cliente;
    List<Comentario> comentarios;

    public void setData(Actividad actividad, Cliente cliente) {
        this.actividad = actividad;
        this.comentarios = comentarioMgr.getCommentsFromActivity(actividad.getId());
        this.cliente = cliente;
    }

    @FXML
    private AnchorPane paneComentarios;

    @FXML
    private ChoiceBox<Integer> puntosChoiceBox;

    @FXML
    private TextArea comentField;

    @FXML
    private Label usernamelabel;

    @FXML
    private Label datelabel;

    @FXML
    private Label commentlabel;

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle("Demasiados caracteres");
        alert.setHeaderText(null);
        alert.setContentText("El límite de caracteres para un comentario es de 200.");
        alert.showAndWait();
    }

    @FXML
    void calificar(ActionEvent event) {
        if (puntosChoiceBox.getValue() != null) {
            calificacionMgr.upsortCalificacion(cliente.getId(), actividad.getId(), puntosChoiceBox.getValue());
        }

    }

    @FXML
    void comentar(ActionEvent event) {
        String comentario = comentField.getText();
        if (comentario.length() >= 200) {
            showAlert(
            );
        }
        else {
            comentarioMgr.addComentario(cliente.getId(), actividad.getId(), comentField.getText());
            comentarios = comentarioMgr.getCommentsFromActivity(actividad.getId());
        }

    }

    @FXML
    void siguientecomentario(ActionEvent event) {
        if (comentarios.size() > index + 1) {
            index++;
            Comentario com = comentarios.get(index);
            usernamelabel.setText(clienteMgr.getUsernameFromId(com.getIdcliente()));
            datelabel.setText(com.getFecha().toString());
            commentlabel.setText(com.getComentario());
            commentlabel.setWrapText(true);
        }
        else {
            index = 0;
            Comentario com = comentarios.get(index);
            usernamelabel.setText(clienteMgr.getUsernameFromId(com.getIdcliente()));
            datelabel.setText(com.getFecha().toString());
            commentlabel.setText(com.getComentario());
            commentlabel.setWrapText(true);
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
    int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        puntosChoiceBox.getItems().addAll(puntos);
        comentField.setWrapText(true);
        if (comentarios.size() > 0) {
            Comentario com = comentarios.get(0);
            usernamelabel.setText(clienteMgr.getUsernameFromId(com.getIdcliente()));
            datelabel.setText(com.getFecha().toString());
            commentlabel.setText(com.getComentario());
            commentlabel.setWrapText(true);
        }
    }

}