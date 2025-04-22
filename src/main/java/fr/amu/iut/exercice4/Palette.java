package fr.amu.iut.exercice4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Label label;
    private Pane panneau;
    private HBox bas;

    @Override
    public void start(Stage primaryStage) {
        // Création des composants
        label = new Label(" ");
        label.setStyle("-fx-font-size: 16px; -fx-padding: 10px;");
        BorderPane.setAlignment(label, Pos.CENTER);

        panneau = new Pane();
        panneau.setStyle("-fx-background-color: white;");
        panneau.setPrefSize(400, 150);

        vert = new Button("Vert");
        rouge = new Button("Rouge");
        bleu = new Button("Bleu");

        bas = new HBox(10, vert, rouge, bleu);
        bas.setAlignment(Pos.CENTER);
        bas.setPadding(new Insets(10));

        // Gestion des événements
        vert.setOnAction(e -> {
            nbVert++;
            panneau.setStyle("-fx-background-color: #20B2AA;"); // turquoise/vert
            label.setText("Vert choisi " + nbVert + " fois");
        });

        rouge.setOnAction(e -> {
            nbRouge++;
            panneau.setStyle("-fx-background-color: #FF6666;"); // rouge clair
            label.setText("Rouge choisi " + nbRouge + " fois");
        });

        bleu.setOnAction(e -> {
            nbBleu++;
            panneau.setStyle("-fx-background-color: #87CEEB;"); // bleu ciel
            label.setText("Bleu choisi " + nbBleu + " fois");
        });

        // Assemblage du BorderPane
        root = new BorderPane();
        root.setTop(label);
        root.setCenter(panneau);
        root.setBottom(bas);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Palette de couleurs");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
