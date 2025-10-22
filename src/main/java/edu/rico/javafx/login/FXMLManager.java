package edu.rico.javafx.login;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FXMLManager
{
    private static Stage stage;

    public static void loadScene(String filename)
    {
        loadScene(filename, stage);
    }

    public static void loadScene(String filename, Stage stage)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(FXMLManager.class.getResource(filename));
        try
        {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FXMLManager.stage = stage;
    }
}
