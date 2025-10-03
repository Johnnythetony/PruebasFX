package edu.rico.javafx.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField nombreT;

    @FXML
    private Label nombreL;

    @FXML
    private TextField passwordT;

    @FXML
    private Label passwordL;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button _loginButton;

    private String nombre = "admin";
    private String password = "1234";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void manageLogin(ActionEvent actionEvent) {
        if (nombreT.getText().equals("admin") && passwordT.getText().equals("1234")) {
            //TODO CARGAR EL FXML welcome-view.fxml
        }else {
            nombreT.setText("");
            passwordT.setText("");
        }
    }
}