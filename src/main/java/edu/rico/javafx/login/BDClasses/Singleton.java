package edu.rico.javafx.login.BDClasses;

import edu.rico.javafx.login.EntityModels.JugadorModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;
import java.util.List;

public class Singleton
{
    private static Singleton instance;
    private static Usuario usuario;
    private static List<Jugador> jugadores;

    private final SessionFactory sf;

    private Singleton()
    {
        StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure().build();
        sf = new MetadataSources(sr).buildMetadata().buildSessionFactory();
    }

    public static Singleton getInstance()
    {
        if(instance == null)
        {
            instance = new Singleton();
        }
        return instance;
    }

    public static boolean verifyPassword(String password)
    {
        String hpassword = HashUtil.hashPassword(password);
        return hpassword != null && hpassword.equals(usuario.getPassword());
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
