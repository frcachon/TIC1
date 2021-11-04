package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;

import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Operador;
import com.example.demo3.managers.ActividadMgr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;

@Component
public class AddActividadController {

        private byte[] image_bytes;
        // private File file = null;

        @Autowired
        private ActividadMgr actividadMgr;

        @Autowired
        private EditarInteresesActividadController editarInteresesActividadController;

        Operador operador;

        void setOperador(Operador operador) {
                this.operador = operador;
        }

        @FXML
        private AnchorPane act_pane;


        @FXML
        private TextField titulo_field;

        @FXML
        private TextField descripcion_field;

        @FXML
        private TextField cupo_field;

        @FXML
        private TextField horarioApertura_field;

        @FXML
        private TextField horarioCierre_field;

        @FXML
        private CheckBox reservas_check;

        @FXML
        private CheckBox vacuna_check;

        @FXML
        private ImageView imagenVw  = new ImageView();

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
        void AddActividad (ActionEvent event) {
                if ((titulo_field.getText() == null) || titulo_field.getText().equals("") || //chequeamos que nada sea nulo
                        (descripcion_field.getText() == null) || descripcion_field.getText().equals("") ||
                        (cupo_field.getText() == null) || cupo_field.getText().equals("") ||
                        (horarioApertura_field.getText() == null) || horarioApertura_field.getText().equals("") ||
                        (horarioCierre_field.getText() == null) || horarioCierre_field.getText().equals(""))  {

                        showAlert(
                                "Faltan datos",
                                "Por favor, ingrese toda la informacion requerida");

                } else {

                        try {
                                Integer idoperador = operador.getId();
                                String titulo = titulo_field.getText();
                                String descripcion = descripcion_field.getText();
                                LocalTime apertura = LocalTime.parse(horarioApertura_field.getText());
                                LocalTime cierre = LocalTime.parse(horarioCierre_field.getText());
                                Integer cupo = Integer.valueOf(cupo_field.getText());
                                Boolean utiliza_reservas = reservas_check.isSelected();
                                Boolean requiere_vacuna = vacuna_check.isSelected();
                                byte[] imagen_actividad = image_bytes;

                                try {
                                        actividadMgr.registrarActividad(titulo, imagen_actividad, idoperador, descripcion, apertura, cierre,false,   cupo, utiliza_reservas, requiere_vacuna);
                                        FXMLLoader fxmlLoader = new FXMLLoader();
                                        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                                        Actividad actividad = actividadMgr.getActividadFromTituloAndIdoperador(titulo,idoperador); //Nose si este buscador es el necesario
                                        editarInteresesActividadController.setActividad(actividad);
                                        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("EditarInteresesActividad.fxml"));
                                        act_pane.getChildren().setAll(pane);
                                        //actividadMgr.registrarActividad(titulo, imagen_actividad, idoperador, descripcion, apertura, cierre,false,   cupo, utiliza_reservas);

                                        //showAlert("Actividad agregada", "Se registro con exitosamente la actividad");
                                        //close(event);

                                } catch (IOException e) {
                                        e.printStackTrace();
                                }

                        } catch (NumberFormatException e) {
                                showAlert(
                                        "Datos incorrectos",
                                        "El documento no tiene el formato esperado (numerico).");
                        }
                }

        }

        @FXML
        void seleccionarImagen (ActionEvent event) throws IOException {

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Buscar Imagen");

                // Agregar filtros para facilitar la busqueda de imagenes por su formato
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("PNG", "*.png")
                );

                /*
                // Obtener la imagen seleccionada
                File imgFile = fileChooser.showOpenDialog(null);

                // Mostar la imagen
                if (imgFile != null) {
                        Image image = new Image("file:" + ((File) imgFile).getAbsolutePath());
                        imagenVw.setImage(image);
                        BufferedImage bi = ImageIO.read((ImageInputStream) image);

                        //Convertir la bufferedimage a bytes
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(bi, "png", baos);
                        image_bytes = baos.toByteArray();

                }
                */

                //Seleccionar la imagen
                Stage stage = (Stage)act_pane.getScene().getWindow();
                //this.file = fileChooser.showOpenDialog(stage);

                File file = fileChooser.showOpenDialog(stage);

                if (file != null) {
                        Path path = Paths.get(file.getAbsolutePath());
                        this.image_bytes = Files.readAllBytes(path);

                      //Mostrar la imagen seleccionada
                        Image image = new Image("file:" + ((File) file).getAbsolutePath());
                        imagenVw.setImage(image);
                }
        }

}