package edu.rico.javafx.login.Controllers;

import edu.rico.javafx.login.FXMLManager;
import edu.rico.javafx.login.LoginApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable
{
    @FXML
    private FlowPane containerPane;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }

    public void loadSettingsView(ActionEvent actionEvent)
    {
        loadView("account-view.fxml");
    }

    public void loadJugadoresView(ActionEvent actionEvent)
    {
        loadView("jugadores-view.fxml");
    }

    private void loadView(String view)
    {
        try
        {
            FXMLManager.getStage().resizableProperty().setValue(true);
            containerPane.getChildren().clear();
            Parent contenido = FXMLLoader.load(LoginApplication.class.getResource(view));
            containerPane.getChildren().add(contenido);
            FXMLManager.getStage().sizeToScene();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
