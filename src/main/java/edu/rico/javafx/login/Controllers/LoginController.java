package edu.rico.javafx.login.Controllers;

import edu.rico.javafx.login.DAO.Singleton;
import edu.rico.javafx.login.DAO.Usuario;
import edu.rico.javafx.login.FXMLManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML
    private TextField nombreT;

    @FXML
    private Label nombreL;

    @FXML
    private TextField passwordT;

    @FXML
    private Label passwordL;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button _loginButton;

    @FXML
    private Button _registerButton;

    @FXML
    private Label loginerror;

    @FXML
    private ImageView menuI;

    @FXML
    private HBox parentN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuI.setImage(new Image(String.valueOf(getClass().getResource("/images/Menuimage.png").toExternalForm())));
        menuI.fitHeightProperty().bind(parentN.heightProperty());
        menuI.fitWidthProperty().bind(parentN.widthProperty());
    }

    public void manageLogin(ActionEvent actionEvent) {

        Session s = Singleton.getInstance().getSessionFactory().openSession();

        Query<Usuario> q = s.createNativeQuery("CALL retrieveUser(:pword,:uname)", Usuario.class);
        q.setParameter("pword", passwordT.getText());
        q.setParameter("uname", nombreT.getText());
        Singleton.setUsuario(q.uniqueResult());

        if(Singleton.getUsuario() != null)
        {
            //FXMLManager.loadScene("account-view.fxml");
            FXMLManager.loadScene("usermenu-view.fxml");
            s.close();
        }
        else
        {
            loginerror.setText("Usuario no encontrado");
            nombreT.setText("");
            passwordT.setText("");
            s.close();
        }
    }

    public void gotoRegister(ActionEvent actionEvent)
    {
        FXMLManager.loadScene("register-view.fxml");
    }
}