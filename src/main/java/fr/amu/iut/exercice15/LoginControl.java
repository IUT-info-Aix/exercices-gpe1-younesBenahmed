package fr.amu.iut.exercice5;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginControl implements Initializable {

    @FXML private TextField    userId;
    @FXML private PasswordField pwd;
    @FXML private Button       okBtn;
    @FXML private Button       cancelBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createBindings();
    }

    private void createBindings() {
        // 1) le champ mot de passe n’est éditable que si userId.length >= 6
        pwd.editableProperty().bind(
                userId.textProperty().length().greaterThanOrEqualTo(6)
        );

        // 2) cancelBtn désactivé si les deux champs sont vides
        cancelBtn.disableProperty().bind(
                userId.textProperty().isEmpty()
                        .and(pwd.textProperty().isEmpty())
        );

        // 3) okBtn désactivé tant que pwd n’a pas ≥8 caractères, 1 majuscule et 1 chiffre
        BooleanBinding lengthOK =
                pwd.textProperty().length().greaterThanOrEqualTo(8);

        BooleanBinding hasUppercase = Bindings.createBooleanBinding(
                () -> pwd.getText().matches(".*[A-Z].*"),
                pwd.textProperty()
        );
        BooleanBinding hasDigit = Bindings.createBooleanBinding(
                () -> pwd.getText().matches(".*\\d.*"),
                pwd.textProperty()
        );

        BooleanBinding passwordValid = lengthOK
                .and(hasUppercase)
                .and(hasDigit);

        okBtn.disableProperty().bind(passwordValid.not());
    }

    @FXML
    private void okClicked() {
        System.out.println("User: " + userId.getText());
        System.out.println("Password: " + "*".repeat(pwd.getText().length()));
    }

    @FXML
    private void cancelClicked() {
        userId.clear();
        pwd.clear();
    }
}
