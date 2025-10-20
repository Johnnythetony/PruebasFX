package edu.rico.javafx.login;

import edu.rico.javafx.login.Controllers.FXMLManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Poker Stars");
        stage.getIcons().add(new Image(String.valueOf(LoginApplication.class.getResource("/images/icon.png").toExternalForm())));
        FXMLManager.setStage(stage);
        FXMLManager.loadScene("login-view.fxml");


        FXMLManager.getStage().setMinHeight(600);
        FXMLManager.getStage().setMinWidth(800);
    }

    public static void main(String[] args) {
        launch();
    }
}