package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Interes;
import com.example.demo3.entities.Tags;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.exceptions.InteresYaExiste;
import com.example.demo3.exceptions.TagYaExiste;
import com.example.demo3.managers.ActividadMgr;
import com.example.demo3.managers.InteresMgr;
import com.example.demo3.managers.TagsMgr;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @Autowired
    private TagsMgr tagsMgr;

    @Autowired
    private HomeOperadorController homeOperadorController;

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

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void agregarPreferencia(ActionEvent event) {

        if(tagsChoiceBox.getValue() == null || tagsChoiceBox.getValue().equals("")){
            showAlert("Faltan datos", "Por favor ingrese toda la informacion requerida");
        }
        else{
            try {
                String tempTags = tagsChoiceBox.getValue();
                Integer actividad = this.actividad.getId();

                Interes interes = interesMgr.getInteresFromNombre(tempTags);
                Integer idtag = interes.getIdinteres();

                tagsMgr.addTag(actividad,idtag);

                List<Tags> todos_tags = tagsMgr.getAll();
                List<Integer> tags_actividad = new ArrayList<>();

                if(todos_tags.size()>0){
                    for(Tags t: todos_tags){
                        Integer act = t.getId().getActividad();
                        if(act == actividad){
                            assert false;
                            tags_actividad.add(t.getId().getIdtags());
                        }
                    }
                }
                List<Interes> intereses = new ArrayList<>();
                if(tags_actividad.size() > 0){
                    for(Integer i: tags_actividad){
                        intereses.add(interesMgr.getInteresFromId(i));
                    }
                }
                ObservableList<Interes> lista;
                lista = FXCollections.observableArrayList();
                lista.addAll(intereses);
                tablaTags.setItems(lista);
                interesesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));

            } catch (TagYaExiste tagYaExiste) {
                showAlert("Ya se registro esta preferencia","Por favor regitre una preferencia valida");
            }


        }

    }

    @FXML
    void crearInteres(ActionEvent event) throws InteresYaExiste {

        if(newField.getText() == null || newField.getText().equals("")){
            showAlert("Tag invalido","Por favor registre un interes valido");
        }

        else{
            try {
                String nuevo = newField.getText();
                interesMgr.addInteres(nuevo);

                List<Interes> q = (List<Interes>) interesMgr.getAll();
                List<String> inters = new ArrayList<>();
                Interes interes = interesMgr.getInteresFromNombre(nuevo);
                inters.add(interes.getNombre());
                tagsChoiceBox.getItems().add(interes.getNombre());

//                for (int i = 0; i < q.size(); i++) {
//                    if (q.get(i) != null) {
//                        inters.add(q.get(i).toString());
//                    }
//                }
//                tagsChoiceBox.getItems().addAll(inters);

            }

            catch (InteresYaExiste e){
                showAlert("Interes ya registrado","Por favor ingrese un interes nuevo");
            }
        }

        //String nuevo = newField.getText();
        // crear objeto
        //inters.add(objeto)
//        List<Interes> q = (List<Interes>) interesMgr.getAll();
//        List<String> inters = new ArrayList<>();
//
//        for (int i = 0; i < q.size(); i++) {
//            if (q.get(i) != null) {
//                inters.add(q.get(i).toString());
//            }
//        }
//        tagsChoiceBox.getItems().addAll(inters);
    }

    @FXML
    void eliminarInteres(ActionEvent event) {

        if(tagsChoiceBox.getValue() == null || tagsChoiceBox.getValue().equals("")){
            showAlert("Faltan datos", "Por favor ingrese toda la informacion requerida");
        }

        else{

            try{
                String tempTags = tagsChoiceBox.getValue();
                Integer actividad = this.actividad.getId();

                Interes interes = interesMgr.getInteresFromNombre(tempTags);
                Integer idtags = interes.getIdinteres();

                tagsMgr.deleteTags(actividad,idtags);

                List<Tags> todos_tags = tagsMgr.getAll();
                List<Integer> tags_actividad = new ArrayList<>();
                if(todos_tags.size() > 0){
                    for(Tags t: todos_tags){
                        Integer act = t.getId().getActividad();
                        if(act == actividad){
                            assert false;
                            tags_actividad.add(t.getId().getIdtags());
                        }
                    }
                }

                List<Interes> intereses = new ArrayList<>();
                if(tags_actividad.size()>0){
                    for(Integer i : tags_actividad){
                        intereses.add(interesMgr.getInteresFromId(i));
                    }
                }
                ObservableList<Interes> lista;
                lista = FXCollections.observableArrayList();
                lista.addAll(intereses);
                tablaTags.setItems(lista);
                interesesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));

            } catch (InformacionInvalida informacionInvalida) {
                informacionInvalida.printStackTrace();
            }
        }

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
        List<Tags> todos_tags = tagsMgr.getAll();
        List<Integer> tags_actividad = new ArrayList<>();
        if(todos_tags.size() > 0){
            for(Tags t: todos_tags){
                Integer act = t.getId().getActividad();
                if(act == actividad.getId()){
                    assert false;
                    tags_actividad.add(t.getId().getIdtags());
                }
            }
        }

        List<Interes> intereses = new ArrayList<>();
        if(tags_actividad.size()>0){
            for(Integer i : tags_actividad){
                intereses.add(interesMgr.getInteresFromId(i));
            }
        }
        ObservableList<Interes> lista;
        lista = FXCollections.observableArrayList();
        lista.addAll(intereses);
        tablaTags.setItems(lista);
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));





    }

}