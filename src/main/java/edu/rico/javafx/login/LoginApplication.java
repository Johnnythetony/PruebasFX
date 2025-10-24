package edu.rico.javafx.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LoginApplication extends Application {

    private static Thread prueba_musica;

    @Override
    public void start(Stage stage) {
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Poker Stars");
        stage.getIcons().add(new Image(String.valueOf(LoginApplication.class.getResource("/images/icon.png").toExternalForm())));
        FXMLManager.setStage(stage);
        FXMLManager.loadScene("login-view.fxml");
        playMusic(Thread.currentThread());
        
    }

    public static void main(String[] args)
    {
        launch();
    }

    private static void playMusic(Thread main_thread)
    {
        prueba_musica = new Thread(() -> {
            Media musica_menu = new Media(FXMLManager.class.getResource("/audio/menu-music.wav").toExternalForm());
            MediaPlayer audio_player = new MediaPlayer(musica_menu);
            audio_player.setCycleCount(MediaPlayer.INDEFINITE);
            audio_player.setVolume(0.3);
            audio_player.play();
            while (main_thread.isAlive()) {}
        });
        prueba_musica.start();
    }

    public static void stopMusic()
    {
        prueba_musica.interrupt();
    }
}