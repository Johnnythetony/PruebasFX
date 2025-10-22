package edu.rico.javafx.login.EntityModels;

import edu.rico.javafx.login.DAO.Singleton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ModelHandler
{
    private static ObservableList<JugadorModel> jugadores = FXCollections.observableArrayList(initialImport());

    private static List<JugadorModel> initialImport()
    {
        ArrayList<JugadorModel> jugador_model_list = new ArrayList<JugadorModel>();

        for(int i = 0; i < Singleton.getJugadores().size(); i++)
        {
            jugador_model_list.add(new JugadorModel(
                    Singleton.getJugadores().get(i).getId(),
                    Singleton.getJugadores().get(i).getNombre(),
                    Singleton.getJugadores().get(i).getApellido(),
                    Singleton.getJugadores().get(i).getApodo(),
                    String.valueOf(Singleton.getJugadores().get(i).getFechaNac()),
                    Singleton.getJugadores().get(i).getEstilo()));
        }

        return jugador_model_list;
    }

    public static ObservableList<JugadorModel> getJugadores()
    {
        return jugadores;
    }

    public static void updateDAO()
    {
        Singleton.updateDAO(jugadores);
    }
}
