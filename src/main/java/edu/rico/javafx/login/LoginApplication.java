package edu.rico.javafx.login;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.InputStream;

public class LoginApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Poker Stars");
        stage.getIcons().add(new Image(String.valueOf(LoginApplication.class.getResource("/images/icon.png").toExternalForm())));
        FXMLManager.setStage(stage);
        FXMLManager.loadScene("login-view.fxml");

        FXMLManager.getStage().setMinHeight(600);
        FXMLManager.getStage().setMinWidth(800);

        File f = new File("");
        System.out.println(f.getAbsolutePath());
    }

    public static void main(String[] args){
        launch();
    }
}