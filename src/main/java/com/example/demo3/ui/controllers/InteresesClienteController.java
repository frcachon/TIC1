package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Interes;
import com.example.demo3.exceptions.GustoYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.managers.GustosMgr;
import com.example.demo3.persistence.InteresRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class InteresesClienteController implements Initializable {

    Cliente cliente;
    void setCliente(Cliente cliente){this.cliente = cliente;}

    @Autowired
    private InteresRepository interesRepository;

    @Autowired
    private GustosMgr gustosMgr;

    @FXML
    private AnchorPane pane_intereses;

    @FXML
    private Button agregarButton;

    @FXML
    private TableView<Interes> tablaIntereses;

    @FXML
    private TableColumn<Interes, String> interesesColumn;

    @FXML
    private Button finalizarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    private ChoiceBox<String> interesChoiceBox;

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cuando algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    @FXML
    void agregarPreferencia(ActionEvent event){

        if (interesChoiceBox.getValue() == null  || interesChoiceBox.getValue().equals("")){
            showAlert("Faltan Datos","Por favor ingrese toda la informacion requerida");
        }
        else{

            try {
                String tempInteres = interesChoiceBox.getValue();
                String usuario = this.cliente.getMail();

                Interes interes = interesRepository.findByNombre(tempInteres);
                Integer idgusto = interes.getIdinteres();

                gustosMgr.addGusto(usuario, idgusto);
            }
            catch (GustoYaExiste e){
                showAlert("Ya se registro esta preferencia","Por favor registre una preferencia valida");
            }
        }

        // 1- debo hacer que agregue el interes al cliente
        // 2- debo ejecutar la query para actualizar la tabla con el nuevo interes
    }

    @FXML
    void eliminarInteres(ActionEvent event) throws InformacionInvalida {

        if (interesChoiceBox.getValue() == null  || interesChoiceBox.getValue().equals("")){
            showAlert("Faltan Datos","Por favor ingrese toda la informacion requerida");
        }

        else{

            try {
                String tempInteres = interesChoiceBox.getValue();
                String usuario = this.cliente.getMail();

                Interes interes = interesRepository.findByNombre(tempInteres);
                Integer idgusto = interes.getIdinteres();

                gustosMgr.deleteGusto(usuario, idgusto);
            }
            catch(InformacionInvalida e){
                showAlert("Preferencia no existe", "Por favor seleccione una preferencia existente");
            }
        }
    }

    @FXML
    void endRegister(ActionEvent event) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
            AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("Main.fxml"));
            pane_intereses.getChildren().setAll(pane);
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle rb) {

        List<Interes> q = (List<Interes>) interesRepository.findAll();

        String[] inters = new String[q.size()];
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) != null) {
                inters[i] = q.get(i).toString();
            }
        }

        //ObservableList<Interes> lista = FXCollections.observableArrayList();
        //lista.addAll(q);

        interesChoiceBox.getItems().addAll(inters);

        //tablaIntereses.setItems(lista);
        //interesesColumn.setCellValueFactory(new PropertyValueFactory<>("interes"));
    }

}
