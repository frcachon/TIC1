package com.example.demo3.ui.controllers;

import com.example.demo3.Demo3Application;
import com.example.demo3.entities.Actividad;
import com.example.demo3.entities.Cliente;
import com.example.demo3.entities.Reserva;
import com.example.demo3.managers.ActividadMgr;
import com.example.demo3.managers.OperadorMgr;
import com.example.demo3.managers.ReservaMgr;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class ItinerarioClienteController implements Initializable {

    @Autowired
    private ActividadMgr actividadMgr;

    @Autowired
    private ReservaMgr reservaMgr;

    @Autowired
    private OperadorMgr operadorMgr;

    @Autowired
    private ActividadViewController actividadViewController;

    @Autowired
    private ActividadThumbController actividadThumbController;


    Cliente cliente;
    void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private AnchorPane home_pane;

    @FXML
    private TextField search_field;

    @FXML
    private GridPane actividadesGrid;

    private List<Actividad> actividades;
    private List<Reserva> reservas;
    private List<Actividad> actividadesTodas;

    @FXML
    private void mouseClick(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();

        int rowIndex = 0;
        for(Node node: actividadesGrid.getChildren()) {
            if(node instanceof HBox) {
                if(node.getBoundsInParent().contains(event.getX(), event.getY())) {
                    rowIndex =  GridPane.getRowIndex(node) - 1;
                    //System.out.println(rowIndex);
                }
            }
        }
        Actividad act = actividadThumbController.actividades.get(rowIndex);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        actividadViewController.setData(act, cliente);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("ActividadView.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @FXML
    void busquedaDinamica(KeyEvent event) {
        actividadesGrid.getChildren().clear();
        List<Actividad> q = actividadMgr.getActividadesFromTituloContaining(search_field.getText());
        for (int i = 0; i < q.size(); i++) {
            if (operadorMgr.getOperadorFromId(q.get(i).getIdoperador()).getBloqueado()) {
                q.remove(i);
            }
        }

        int row = 1;

        try {
            for (Actividad actividad : q) {
                if (actividad.getValidada()) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ActividadThumb.fxml"));
                    fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                    HBox box = fxmlLoader.load();
                    actividadThumbController = fxmlLoader.getController();
                    actividadThumbController.setData(actividad);
                    actividadThumbController.setPane(home_pane);
                    actividadesGrid.addRow(row, box);
                    row++;
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void goBack(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
        AnchorPane pane = fxmlLoader.load(MainController.class.getResourceAsStream("HomeCliente.fxml"));
        home_pane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        actividadesGrid.getChildren().clear();
        reservas = reservaMgr.getReservasFromCliente(cliente.getId());
        actividadesTodas = actividadMgr.getAll();
        //Aca la lista actividades que contiene las actividades de las reservas realizadas por el cliente
        for(Reserva reserva : reservas){
            for(Actividad act : actividadesTodas){

                if(reserva.getIdactividad() == act.getId())
                    actividades.add(act);
            }
        }


        int acts_size = actividades.size();
        int i = 0;
        while (i < acts_size) {
            try {
                if (operadorMgr.getOperadorFromId(actividades.get(i).getIdoperador()).getBloqueado()) {
                    actividades.remove(i);
                    continue;
                }
                i++;
            }
            catch (Exception e) {
                break;
            }
        }


        int row = 1;

        try {
            if (actividades.size() > 0) {
                for (Actividad actividad : actividades) {
                    if (actividad.getValidada()) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("ActividadThumb.fxml"));
                        fxmlLoader.setControllerFactory(Demo3Application.getContext()::getBean);
                        HBox box = fxmlLoader.load();
                        actividadThumbController = fxmlLoader.getController();
                        actividadThumbController.setData(actividad);
                        actividadThumbController.setPane(home_pane);
                        actividadesGrid.addRow(row, box);
                        row++;
                    }
                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
