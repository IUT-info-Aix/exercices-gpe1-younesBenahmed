package fr.amu.iut.exercice7;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CounterController implements Initializable {

    // Liaison avec le Label du FXML
    @FXML
    private Label counterLabel;

    // Compteur interne
    private int counter = 0;

    /**
     * Appelé automatiquement après le chargement du FXML.
     * On initialise ici le texte du label à "0".
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        counterLabel.setText(Integer.toString(counter));
    }

    /**
     * Incrémenté par onAction="#increment" dans le FXML.
     */
    @FXML
    private void increment() {
        counter++;
        counterLabel.setText(Integer.toString(counter));
    }

    /**
     * Invoqué par onAction="#decrement" dans le FXML.
     */
    @FXML
    private void decrement() {
        counter--;
        counterLabel.setText(Integer.toString(counter));
    }
}
