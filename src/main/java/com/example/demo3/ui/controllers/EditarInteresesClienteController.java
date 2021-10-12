package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Gustos;
import com.example.demo3.entities.Interes;
import com.example.demo3.exceptions.GustoYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import com.example.demo3.managers.GustosMgr;
import com.example.demo3.persistence.GustosRepository;
import com.example.demo3.persistence.InteresRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class EditarInteresesClienteController implements Initializable {

    @Autowired
    private GustosRepository gustosRepository;

    @Autowired
    private GustosMgr gustosMgr;

    @Autowired
    private InteresRepository interesRepository;

    @Autowired
    private HomeClienteController homeClienteController;

    Cliente cliente;
    void setCliente(Cliente cliente){this.cliente = cliente;}

    @FXML
    private AnchorPane pane_intereses;

    @FXML
    private ChoiceBox<String> interesChoiceBox;

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
    void agregarPreferencia(ActionEvent event) throws GustoYaExiste {
        String tempInteres = interesChoiceBox.getValue();
        String usuario = this.cliente.getMail();

        Interes interes = interesRepository.findByNombre(tempInteres);
        Integer idgusto = interes.getIdinteres();

        gustosMgr.addGusto(usuario, idgusto);

        List<Gustos> todos_gustos = gustosRepository.findAll();
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
                intereses.add(interesRepository.findByIdinteres(i));
            }
        }
        ObservableList<Interes> lista;
        lista = FXCollections.observableArrayList();
        lista.addAll(intereses);
        tablaIntereses.setItems(lista);
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    @FXML
    void eliminarInteres(ActionEvent event) throws InformacionInvalida {
        String tempInteres = interesChoiceBox.getValue();
        String usuario = this.cliente.getMail();

        Interes interes = interesRepository.findByNombre(tempInteres);
        Integer idgusto = interes.getIdinteres();

        gustosMgr.deleteGusto(usuario, idgusto);

        List<Gustos> todos_gustos = gustosRepository.findAll();
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
                intereses.add(interesRepository.findByIdinteres(i));
            }
        }
        ObservableList<Interes> lista;
        lista = FXCollections.observableArrayList();
        lista.addAll(intereses);
        tablaIntereses.setItems(lista);
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    @FXML
    void updateIntereses(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        homeClienteController.setCliente(cliente);
        AnchorPane pane = fxmlLoader.load(ClienteController.class.getResourceAsStream("HomeCliente.fxml"));
        pane_intereses.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Interes> q = (List<Interes>) interesRepository.findAll();
        String[] inters = new String[q.size()];
        for (int i = 0; i < q.size(); i++) {
            if (q.get(i) != null) {
                inters[i] = q.get(i).toString();
            }
        }
        interesChoiceBox.getItems().addAll(inters);

        List<Gustos> todos_gustos = gustosRepository.findAll();
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
                intereses.add(interesRepository.findByIdinteres(i));
            }
        }
        ObservableList<Interes> lista;
        lista = FXCollections.observableArrayList();
        lista.addAll(intereses);
        tablaIntereses.setItems(lista);
        interesesColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }
}
