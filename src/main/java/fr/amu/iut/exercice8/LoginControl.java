package fr.amu.iut.exercice8;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControl {

    @FXML private TextField    user;
    @FXML private PasswordField pwd;

    /** Appelé par onAction="#okClicked" */
    @FXML
    private void okClicked() {
        String username = user.getText();
        String password = pwd.getText();
        System.out.println("Utilisateur : " + username);
        System.out.println("Mot de passe : " + "*".repeat(password.length()));
    }

    /** Appelé par onAction="#cancelClicked" */
    @FXML
    private void cancelClicked() {
        user.clear();
        pwd.clear();
    }
}
