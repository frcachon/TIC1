package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.*;
import com.example.demo3.exceptions.GustoYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.managers.GustosMgr;
import com.example.demo3.managers.InteresMgr;
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
public class InteresesClienteController implements Initializable {

    Cliente cliente;
    void setCliente(Cliente cliente){this.cliente = cliente;}

    @Autowired
    private HomeClienteController homeClienteController;

    @Autowired
    private InteresMgr interesMgr;

    @Autowired
    private GustosMgr gustosMgr;

    @FXML
    private AnchorPane pane_intereses;

    @FXML
    private TableView<Interes> tablaIntereses;

    @FXML
    private TableColumn<Interes, String> interesesColumn;

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

                Interes interes = interesMgr.getInteresFromNombre(tempInteres);
                Integer idgusto = interes.getIdinteres();

                gustosMgr.addGusto(usuario, idgusto);

                List<Gustos> todos_gustos = gustosMgr.getAll();
                List<Integer> gustos_cliente = new ArrayList<>();
                if (todos_gustos.size() > 0) {
                    for (Gustos g : todos_gustos) {
                        String usu = g.getId().getUsuario();
                        if (usu.equals(cliente.getMail())) {
                            assert false;
                            gustos_cliente.add(g.getId().getIdgustos());
                        }
                    }
                }
                List<Interes> intereses = new ArrayList<>();
                // hasta este punto tengo los id de los intereses del cliente (en gustos_cliente)
                if (gustos_cliente.size() > 0) {
                    for (Integer i : gustos_cliente) {
                        intereses.add(interesMgr.getInteresFromId(i));
                    }
                }
                ObservableList<Interes> lista;
                lista = FXCollections.observableArrayList();
                lista.addAll(intereses);
                tablaIntereses.setItems(lista);
                interesesColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

            }
            catch (GustoYaExiste e){
                showAlert("Ya se registro esta preferencia","Por favor registre una preferencia valida");
            }
        }

        // debo ejecutar la query para actualizar la tabla con el nuevo interes
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

                Interes interes = interesMgr.getInteresFromNombre(tempInteres);
                Integer idgusto = interes.getIdinteres();

                gustosMgr.deleteGusto(usuario, idgusto);

                List<Gustos> todos_gustos = gustosMgr.getAll();
                List<Integer> gustos_cliente = new ArrayList<>();
                if (todos_gustos.size() > 0) {
                    for (Gustos g : todos_gustos) {
                        String usu = g.getId().getUsuario();
                        if (usu.equals(cliente.getMail())) {
                            assert false;
                            gustos_cliente.add(g.getId().getIdgustos());
                        }
                    }
                }
                List<Interes> intereses = new ArrayList<>();
                // hasta este punto tengo los id de los intereses del cliente (en gustos_cliente)
                if (gustos_cliente.size() > 0) {
                    for (Integer i : gustos_cliente) {
                        intereses.add(interesMgr.getInteresFromId(i));
                    }
                }
                ObservableList<Interes> lista;
                lista = FXCollections.observableArrayList();
                lista.addAll(intereses);
                tablaIntereses.setItems(lista);
                interesesColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

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
            homeClienteController.setCliente(cliente);
            AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
            pane_intereses.getChildren().setAll(pane);
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        List<Interes> q = interesMgr.getAll();
        String[] inters = new String[q.size()];
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) != null) {
                inters[i] = q.get(i).toString();
            }
        }
        interesChoiceBox.getItems().addAll(inters);
    }

}