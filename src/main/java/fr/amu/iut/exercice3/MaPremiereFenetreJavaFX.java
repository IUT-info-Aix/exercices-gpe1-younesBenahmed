package fr.amu.iut.exercice3;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MaPremiereFenetreJavaFX extends Application {

    // Composants de la fenêtre
    private Label helloLabel;
    private TextField nameField;
    private Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création du conteneur principal
        VBox vbox = new VBox(10); // 10 pixels d'espacement entre les éléments
        vbox.setAlignment(Pos.CENTER);

        // Création du label
        helloLabel = new Label("Bonjour à tous !");
        vbox.getChildren().add(helloLabel);

        // Création du champ texte
        nameField = new TextField("Veuillez saisir un nom");
        nameField.setMaxWidth(180.0d);
        nameField.setFont(Font.font("Courier", FontWeight.NORMAL, 12));
        nameField.setOnAction(event -> handleButtonClick(event));
        vbox.getChildren().add(nameField);

        // Création du bouton
        button = new Button("Dire bonjour");
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleButtonClick(event));
        vbox.getChildren().add(button);

        // Création de la scène
        Scene scene = new Scene(vbox);

        // Configuration de la fenêtre principale
        primaryStage.setScene(scene);
        primaryStage.setTitle("La page d'un Pro de JavaFX");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();
    }

    // Méthode pour gérer les clics et la touche Entrée
    private void handleButtonClick(Event event) {
        helloLabel.setText("Bonjour à toi, " + nameField.getText());
    }
}
