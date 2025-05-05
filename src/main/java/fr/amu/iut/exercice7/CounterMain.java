package fr.amu.iut.exercice7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class CounterMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Charger le BorderPane défini dans votre FXML
            URL location = getClass().getResource("/exercice7/CounterView.fxml");
            System.out.println("FXML localisé à : " + location);
            BorderPane root = new FXMLLoader(location).load();

            // 2. Créer la scène avec ce root et l’afficher
            Scene scene = new Scene(root, 300, 200);
            primaryStage.setTitle("Counter Main Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
