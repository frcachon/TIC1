package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Operador;
import com.example.demo3.entities.Tags;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.managers.ActividadMgr;
import com.example.demo3.managers.TagsMgr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EditActividadController implements Initializable {

    private byte[] image_bytes;

    @Autowired
    private ActividadMgr actividadMgr;

    @Autowired
    private EditarInteresesActividadController editarInteresesActividadController;

    @Autowired
    private TagsMgr tagsMgr;

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
    private CheckBox vacuna_check;

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
    void seleccionarImagen(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        Stage stage = (Stage) act_pane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Path path = Paths.get(file.getAbsolutePath());
            this.image_bytes = Files.readAllBytes(path);

            //Mostrar la imagen seleccionada
            Image image = new Image("file:" + ((File) file).getAbsolutePath());
            imagenVw.setImage(image);
        }
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
            Boolean requiere_vacuna = vacuna_check.isSelected();
            byte[] imagen_actividad = image_bytes;

            try {
                //actividadMgr.setActividad(actividad);
                actividadMgr.updateActividad(actividad.getId(), titulo,descripcion,apertura,cierre,cupo,utiliza_reservas, imagen_actividad, requiere_vacuna);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                editarInteresesActividadController.setActividad(actividad);
                AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("EditarInteresesActividad.fxml"));
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

    @FXML
    void eliminarActividad(ActionEvent event) throws IOException, InformacionInvalida {
        List<Tags> todos_tags = tagsMgr.getAll();
        //List<Integer> tags_actividad = new ArrayList<>();
        if(todos_tags.size() > 0){
            for(Tags t: todos_tags){
                Integer act = t.getId().getActividad();
                if(act == actividad.getId()){
                    assert false;
                    tagsMgr.deleteTags(act, t.getId().getIdtags());
                    //tags_actividad.add(t.getId().getIdtags());
                }
            }
        }

        actividadMgr.eliminarActividad(actividad);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeOperador.fxml"));
        act_pane.getChildren().setAll(pane);
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
        vacuna_check.setSelected(actividad.getRequiere_vacuna());
        if (actividad.getImagenactividad() != null) {
            InputStream is = new ByteArrayInputStream(actividad.getImagenactividad());
            imagenVw.setImage(new Image(is));
        }
    }

}