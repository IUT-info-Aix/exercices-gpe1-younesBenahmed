package fr.amu.iut.exercice10;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class UIController {

    @FXML private TextArea mainText;
    @FXML private Label    statusBar;

    @FXML
    private void onActionA() {
        mainText.appendText("Vous avez cliqué sur Bouton A\n");
        statusBar.setText("Action A exécutée");
    }

    @FXML
    private void onActionB() {
        mainText.appendText("Vous avez cliqué sur Bouton B\n");
        statusBar.setText("Action B exécutée");
    }

    @FXML
    private void onQuit() {
        statusBar.setText("Fermeture…");
        System.exit(0);
    }
}
