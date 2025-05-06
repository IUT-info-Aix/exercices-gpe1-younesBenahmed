// src/main/java/fr/amu/iut/exercice2/Palette.java
package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Palette extends Application {

    private Label texteDuHaut;
    private Label texteDuBas;
    private Pane  panneau;

    private CustomButton vert;
    private CustomButton rouge;
    private CustomButton bleu;

    // Pointe vers le dernier bouton cliqué
    private CustomButton sourceOfEvent;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Palette de couleurs - Exo12");

        // Label du haut
        texteDuHaut = new Label("Cliquez sur un bouton");
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        // Panneau central
        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        // Création des CustomButton
        vert  = new CustomButton("Vert",  "#00FF00");
        rouge = new CustomButton("Rouge", "#FF0000");
        bleu  = new CustomButton("Bleu",  "#0000FF");

        // Gestionnaire générique : stocke la source et incrémente son compteur
        EventHandler<ActionEvent> gestionnaireEvenement = e -> {
            sourceOfEvent = (CustomButton) e.getSource();
            sourceOfEvent.setNbClics(sourceOfEvent.getNbClics() + 1);
        };
        vert.setOnAction(gestionnaireEvenement);
        rouge.setOnAction(gestionnaireEvenement);
        bleu.setOnAction(gestionnaireEvenement);

        // HBox des boutons
        HBox boutons = new HBox(10, vert, rouge, bleu);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10));

        // Label du bas
        texteDuBas = new Label();
        texteDuBas.setPadding(new Insets(5, 0, 0, 0));

        VBox bas = new VBox(5, boutons, texteDuBas);
        bas.setAlignment(Pos.CENTER);

        // Layout principal
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setTop(texteDuHaut);
        root.setCenter(panneau);
        root.setBottom(bas);

        // Listener sur nbClics de chaque bouton
        ChangeListener<Number> nbClicsListener = (obs, oldVal, newVal) -> {
            String nom = sourceOfEvent.getText();
            // Met à jour le label du haut
            texteDuHaut.setText(nom + " choisi " + newVal.intValue() + " fois");
            // Change la couleur de fond du panneau
            panneau.setStyle("-fx-background-color: " + sourceOfEvent.getCouleur() + ";");
            // Met à jour le label du bas (texte et couleur)
            texteDuBas.setText("Le " + nom + " est une jolie couleur !");
            texteDuBas.setStyle("-fx-text-fill: " + sourceOfEvent.getCouleur() + ";");
        };

        vert.nbClicsProperty().addListener(nbClicsListener);
        rouge.nbClicsProperty().addListener(nbClicsListener);
        bleu.nbClicsProperty().addListener(nbClicsListener);

        // Affichage
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
