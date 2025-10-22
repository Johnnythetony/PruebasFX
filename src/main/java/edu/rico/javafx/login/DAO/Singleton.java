package edu.rico.javafx.login.DAO;

import edu.rico.javafx.login.EntityModels.JugadorModel;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.sql.Date;
import java.util.List;

public class Singleton
{
    private static Singleton instance;
    private static Usuario usuario;
    private static List<Jugador> jugadores;
    private static Configuration cfg;
    private final SessionFactory sf;

    private Singleton()
    {
        Dotenv dotenv = Dotenv.configure()
                .directory(this.getClass().getClassLoader().getResource(".env").toString())
                .filename(".env")
                .load();
        createConfig();
        cfg.configure("hibernate.cfg.xml");
        cfg.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
        cfg.setProperty("hibernate.connection.driver_class", dotenv.get("DB_DRIVER_MYSQL"));
        cfg.setProperty("hibernate.connection.username", dotenv.get("DB_USER_MYSQL"));
        sf = cfg.buildSessionFactory();
    }

    private static void createConfig()
    {
        cfg = new Configuration();
        File cfg_file = new File(String.valueOf(Singleton.class.getResource("hibernate.cfg.xml")));
        if(cfg_file.exists())
        {
            cfg.configure("hibernate.cfg.xml");
        }
        else
        {
            cfg.addAnnotatedClass(Usuario.class);
            cfg.addAnnotatedClass(Jugador.class);
            cfg.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
            cfg.setProperty("hibernate.show_sql", "true");
            cfg.setProperty("hibernate.format_sql", "true");
            cfg.setProperty("hibernate.hbm2ddl.auto", "update");
        }
    }

    public static Singleton getInstance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }

    public static String createUser(String username, String name, String surname, String email, String birthday, String password)
    {
        if (username.isEmpty() || name.isEmpty() || surname.isEmpty() || email.isEmpty() || birthday.isEmpty() || password.isEmpty())
        {
            return "Faltan campos por rellenar!";
        }
        try
        {
            Date birth_date = Date.valueOf(birthday);
            Singleton.setUsuario(new Usuario(username, name, surname, email, birth_date, password));
        } catch (IllegalArgumentException e)
        {
            return "El formato de fecha debe ser yyyy-mm-dd";
        }

        Session s = Singleton.getInstance().getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.persist(Singleton.getUsuario());
        t.commit();
        s.close();

        return "Usuario creado con exito!";
    }

    public static String createPlayer(JugadorModel jugador)
    {
        try
        {
            Singleton.getJugadores().add(new Jugador(jugador.getNombre(), jugador.getApellido(), jugador.getApodo(), Date.valueOf(jugador.getFecha_nacimiento()), jugador.getEstilo()));
        } catch (IllegalArgumentException e)
        {
            return "El formato de fecha es incorrecto";
        }

        Session s = Singleton.getInstance().getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.persist(Singleton.getJugadores().getLast());
        t.commit();
        s.close();

        return "Usuario creado con exito!";
    }

    public static String updateUser(String username, String name, String surname, String email, String birthday, String password)
    {
        if (!username.isEmpty())
        {
            usuario.setUsername(username);
        }
        if (!name.isEmpty())
        {
            usuario.setName(name);
        }
        if (!surname.isEmpty())
        {
            usuario.setSurname(surname);
        }
        if (!email.isEmpty())
        {
            usuario.setEmail(email);
        }
        if (!birthday.isEmpty())
        {
            try
            {
                usuario.setBirth_date(Date.valueOf(birthday));
            } catch (Exception e)
            {
                return "Formato de fecha incorrecto";
            }
        }
        if (!password.isEmpty())
        {
            usuario.setPassword(password);
        }

        Session s = Singleton.getInstance().getSessionFactory().openSession();
        Transaction t = s.beginTransaction();
        s.merge(usuario);
        t.commit();
        s.close();

        return "Cambios realizados con exito!";
    }

    public static String updatePlayer(JugadorModel jugador)
    {
        for (int i = 0; i < Singleton.getJugadores().size(); i++)
        {
            Jugador j = Singleton.getJugadores().get(i);

            if(j.getId() == jugador.getId())
            {
                try
                {
                    Date birth_date = Date.valueOf(jugador.getFecha_nacimiento());
                    Singleton.getJugadores().get(i).setNombre(jugador.getNombre());
                    Singleton.getJugadores().get(i).setApellido(jugador.getApellido());
                    Singleton.getJugadores().get(i).setApodo(jugador.getApodo());
                    Singleton.getJugadores().get(i).setFechaNac(birth_date);
                    Singleton.getJugadores().get(i).setEstilo(jugador.getEstilo());

                    Session s = Singleton.getInstance().getSessionFactory().openSession();
                    Transaction t = s.beginTransaction();
                    s.merge(Singleton.getJugadores().get(i));
                    t.commit();
                    s.close();

                    return "Cambios realizados con exito";
                } catch (IllegalArgumentException e)
                {
                    return "Formato de fecha del JugadorModel incorrecto";
                }
            }
        }
        return "Ha ocurrido un error al intentar actualizar los datos";
    }

    public static void updateDAO(ObservableList<JugadorModel> jugadores)
    {

    }

    public SessionFactory getSessionFactory()
    {
        return sf;
    }

    public static Usuario getUsuario()
    {
        return usuario;
    }

    public static void setUsuario(Usuario usuario)
    {

        Singleton.usuario = usuario;
    }

    public static List<Jugador> getJugadores()
    {
        if(jugadores == null)
        {
            Session s = Singleton.getInstance().getSessionFactory().openSession();
            jugadores = s.createQuery("FROM Jugador", Jugador.class).list();
            s.close();
        }

        return jugadores;
    }
}
