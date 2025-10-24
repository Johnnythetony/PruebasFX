package edu.rico.javafx.login;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LoginApplication extends Application {

    private static MediaPlayer prueba_musica;

    @Override
    public void start(Stage stage) throws InterruptedException {
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Poker Stars");
        stage.getIcons().add(new Image(String.valueOf(LoginApplication.class.getResource("/images/icon.png").toExternalForm())));
        FXMLManager.setStage(stage);
        FXMLManager.loadScene("login-view.fxml");
        playMusic();
    }

    public static void main(String[] args)
    {
        launch();
    }

    private static void playMusic()
    {
            Media musica_menu = new Media(FXMLManager.class.getResource("/audio/menu-music.wav").toExternalForm());
            prueba_musica = new MediaPlayer(musica_menu);
            prueba_musica.setCycleCount(MediaPlayer.INDEFINITE);
            prueba_musica.setVolume(0.2);
            prueba_musica.play();
    }

    public static void stopMusic()
    {
        prueba_musica.setVolume(0);
    }
}