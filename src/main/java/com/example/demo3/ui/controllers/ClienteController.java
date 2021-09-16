package com.example.demo3.ui.controllers;

import com.example.demo3.managers.ClienteMgr;
import com.example.demo3.exceptions.ClienteYaExiste;
import com.example.demo3.exceptions.InformacionInvalida;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ClienteController implements Initializable {

    @Autowired
    private ClienteMgr clienteMgr;

    @FXML
    private TextField username_field;

    @FXML
    private TextField mail_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button siguiente_button;

    @FXML
    private TextField document_field;

    @FXML
    private ChoiceBox<String> document_choicebox;

    @FXML
    private DatePicker date_picker;

    @FXML
    private CheckBox vacuna_check;

    @FXML
    private ChoiceBox<String> pais_choicebox;

    private String[] paises = {"Afganistán","Albania","Alemania","Andorra","Angola","Antigua y Barbuda","Arabia Saudita","Argelia","Argentina","Armenia","Australia","Austria","Azerbaiyán","Bahamas","Bangladés","Barbados","Baréin","Bélgica","Belice","Benín","Bielorrusia","Birmania","Bolivia","Bosnia y Herzegovina","Botsuana","Brasil","Brunéi","Bulgaria","Burkina Faso","Burundi","Bután","Cabo Verde","Camboya","Camerún","Canadá","Catar","Chad","Chile","China","Chipre","Ciudad del Vaticano","Colombia","Comoras","Corea del Norte","Corea del Sur","Costa de Marfil","Costa Rica","Croacia","Cuba","Dinamarca","Dominica","Ecuador","Egipto","El Salvador","Emiratos Árabes Unidos","Eritrea","Eslovaquia","Eslovenia","España","Estados Unidos","Estonia","Etiopía","Filipinas","Finlandia","Fiyi","Francia","Gabón","Gambia","Georgia","Ghana","Granada","Grecia","Guatemala","Guyana","Guinea","Guinea ecuatorial","Guinea-Bisáu","Haití","Honduras","Hungría","India","Indonesia","Irak","Irán","Irlanda","Islandia","Islas Marshall","Islas Salomón","Israel","Italia","Jamaica","Japón","Jordania","Kazajistán","Kenia","Kirguistán","Kiribati","Kuwait","Laos","Lesoto","Letonia","Líbano","Liberia","Libia","Liechtenstein","Lituania","Luxemburgo","Madagascar","Malasia","Malaui","Maldivas","Malí","Malta","Marruecos","Mauricio","Mauritania","México","Micronesia","Moldavia","Mónaco","Mongolia","Montenegro","Mozambique","Namibia","Nauru","Nepal","Nicaragua","Níger","Nigeria","Noruega","Nueva Zelanda","Omán","Países Bajos","Pakistán","Palaos","Palestina","Panamá","Papúa Nueva Guinea","Paraguay","Perú","Polonia","Portugal","Reino Unido","República Centroafricana","República Checa","República de Macedonia","República del Congo","República Democrática del Congo","República Dominicana","República Sudafricana","Ruanda","Rumanía","Rusia","Samoa","San Cristóbal y Nieves","San Marino","San Vicente y las Granadinas","Santa Lucía","Santo Tomé y Príncipe","Senegal","Serbia","Seychelles","Sierra Leona","Singapur","Siria","Somalia","Sri Lanka","Suazilandia","Sudán","Sudán del Sur","Suecia","Suiza","Surinam","Tailandia","Tanzania","Tayikistán","Timor Oriental","Togo","Tonga","Trinidad y Tobago","Túnez","Turkmenistán","Turquía","Tuvalu","Ucrania","Uganda","Uruguay","Uzbekistán","Vanuatu","Venezuela","Vietnam","Yemen","Yibuti","Zambia","Zimbabue"};
    private String[] documentos = {"Pasaporte", "Cédula"};

    @FXML
    void hobbiesWindow(ActionEvent event) {
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   //Como is lanzara una excepcion cunado algo falla
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    private void clean() {
        document_field.setText(null);
        mail_field.setText(null);
        document_field.setText(null);
    }

    @FXML
    void addClient(ActionEvent event) {
        if (document_field.getText() == null || document_field.getText().equals("") ||     //chequeamos que nada sea nulo
                mail_field.getText() == null || mail_field.getText().equals("") ||
                document_field.getText() == null || document_field.getText().equals("")) {

            showAlert(
                    "Faltan datos!",
                    "Por favor, ingrese toda la informacion requerida");

        } else {

            try {
                Long document = Long.valueOf(document_field.getText());   //obtiene los valores de los campos
                String mail = mail_field.getText();
                String name = document_field.getText();

                try {

                    clienteMgr.addClient(document, name, mail);

                    showAlert("Cliente agregado", "Se agrego con exitosamente al cliente");

                    close(event);
                } catch (InformacionInvalida informacionInvalida) {
                    showAlert(
                            "Informacion invalida !",
                            "Los datos ingresados no son correctos.");
                } catch (ClienteYaExiste clienteYaExiste) {
                    showAlert(
                            "Los datos ingresados pertenecen a un cliente ya registrado!",
                            " ");
                }

            } catch (NumberFormatException e) {

                showAlert(
                        "Datos incorrectos!",
                        "El documento no tiene el formato esperado (numerico).");

            }
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pais_choicebox.getItems().addAll(paises);
        document_choicebox.getItems().addAll(documentos);
    }

}

