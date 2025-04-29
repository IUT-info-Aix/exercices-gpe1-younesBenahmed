package com.example.partie3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class RegistrationFormController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Window owner = submitButton.getScene().getWindow();
        if(nameField.getText().isEmpty()) {
            System.out.println("Form Error!, Please enter your name");
            return;
        }
        if(emailField.getText().isEmpty()) {
            System.out.println("Form Error!, Please enter your email id");
            return;
        }
        if(passwordField.getText().isEmpty()) {
            System.out.println("Form Error!, Please enter a password");
            return;
        }

        System.out.println("Registration Successful! \n Welcome " + nameField.getText());
    }
}
