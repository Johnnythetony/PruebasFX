module edu.rico.javafx.login {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.rico.javafx.login to javafx.fxml;
    exports edu.rico.javafx.login;
}