module edu.rico.javafx.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires mysql.connector.j;
    requires java.naming;
    requires jakarta.persistence;
    requires io.github.cdimascio.dotenv.java;
    requires java.desktop;
    requires javafx.media;


    opens edu.rico.javafx.login to javafx.fxml, org.hibernate.orm.core;
    opens edu.rico.javafx.login.Controllers to javafx.fxml, org.hibernate.orm.core;
    opens edu.rico.javafx.login.DAO to javafx.fxml, org.hibernate.orm.core;
    opens edu.rico.javafx.login.EntityModels to javafx.fxml, org.hibernate.orm.core;
    exports edu.rico.javafx.login;
    exports edu.rico.javafx.login.Controllers;
    exports edu.rico.javafx.login.DAO;
    exports edu.rico.javafx.login.EntityModels;
}