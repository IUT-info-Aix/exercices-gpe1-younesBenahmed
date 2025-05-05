package fr.amu.iut.exercice8;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginMain extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // 1) On charge bien le resource via Class.getResource (path absolu depuis la racine du classpath) :
        URL fxmlUrl = getClass().getResource("/exercice8/LoginView.fxml");
        if (fxmlUrl == null) {
            throw new RuntimeException("Impossible de trouver LoginView.fxml !");
        }

        // 2) On crée le loader à partir de cette URL
        Parent root = FXMLLoader.load(fxmlUrl);

        // 3) On affiche
        stage.setScene(new Scene(root));
        stage.setTitle("Connexion FXML");
        stage.show();
    }

}
