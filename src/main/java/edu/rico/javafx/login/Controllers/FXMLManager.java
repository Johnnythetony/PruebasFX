package edu.rico.javafx.login.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLManager
{
    private static Stage stage;

    public static void loadScene(String filename)
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
