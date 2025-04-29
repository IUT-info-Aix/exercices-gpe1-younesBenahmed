package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class IHMPendu extends Application {

    private static final int NB_VIES_INITIALES = 7;
    private int nbVies;
    private String motSecret;
    private char[] lettresTrouvees;

    private Label labelVies;
    private Label labelMot;
    private ImageView imageView;
    private GridPane clavier;
    private Button rejouerButton;

    private Dico dico = new Dico();

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #e0f9f9;");

        imageView = new ImageView();
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        labelVies = new Label();
        labelVies.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        labelMot = new Label();
        labelMot.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        clavier = new GridPane();
        clavier.setAlignment(Pos.CENTER);
        clavier.setHgap(5);
        clavier.setVgap(5);

        rejouerButton = new Button("Rejouer");
        rejouerButton.setStyle("-fx-background-color: #d3f7f7; -fx-border-color: #ffa07a; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: #ff7f50; -fx-font-weight: bold;");
        rejouerButton.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        rejouerButton.setOnAction(e -> initialiserPartie());

        root.getChildren().addAll(imageView, labelVies, labelMot, clavier, rejouerButton);

        Scene scene = new Scene(root, 500, 550);
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.setScene(scene);
        primaryStage.show();

        initialiserPartie();
    }

    private void initialiserPartie() {
        nbVies = NB_VIES_INITIALES;
        motSecret = dico.getMot().toLowerCase();
        lettresTrouvees = new char[motSecret.length()];
        for (int i = 0; i < lettresTrouvees.length; i++) {
            lettresTrouvees[i] = '*';
        }

        mettreAJourAffichage();
        creerClavier();
    }

    private void creerClavier() {
        clavier.getChildren().clear();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] lettresParLigne = {10, 9, 7};
        int index = 0;

        for (int row = 0; row < lettresParLigne.length; row++) {
            int totalCols = lettresParLigne[row];
            int startCol = (13 - totalCols) / 2;
            for (int col = 0; col < totalCols; col++) {
                if (index >= alphabet.length()) break;
                char lettre = alphabet.charAt(index);
                Button boutonLettre = new Button(String.valueOf(lettre));
                boutonLettre.setOnAction(e -> traiterLettre(boutonLettre));
                boutonLettre.setMinSize(40, 40);
                boutonLettre.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                boutonLettre.setStyle("-fx-background-color: #f0fff0; -fx-border-color: #f4a261; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: #90c2c2;");
                GridPane.setHalignment(boutonLettre, HPos.CENTER);
                clavier.add(boutonLettre, startCol + col, row);
                index++;
            }
        }
    }

    private void traiterLettre(Button boutonLettre) {
        String lettreStr = boutonLettre.getText();
        boutonLettre.setDisable(true);
        char lettre = lettreStr.charAt(0);

        ArrayList<Integer> positions = dico.getPositions(lettre, motSecret);

        if (positions.isEmpty()) {
            nbVies--;
        } else {
            for (int pos : positions) {
                lettresTrouvees[pos] = lettre;
            }
        }

        mettreAJourAffichage();

        if (nbVies <= 0 || motTrouve()) {
            finPartie();
        }
    }

    private void mettreAJourAffichage() {
        labelVies.setText("Nombre de vies : " + nbVies);
        labelMot.setText(new String(lettresTrouvees));

        String imagePath;
        if (nbVies > 0) {
            imagePath = "/exercice6/pendu" + (NB_VIES_INITIALES - nbVies) + ".png";
        } else {
            imagePath = "/exercice6/pendu7.png";
        }

        var imageURL = getClass().getResource(imagePath);
        if (imageURL != null) {
            imageView.setImage(new Image(imageURL.toString()));
        } else {
            System.out.println("Image manquante : " + imagePath);
            imageView.setImage(null);
        }
    }

    private boolean motTrouve() {
        for (char c : lettresTrouvees) {
            if (c == '*') return false;
        }
        return true;
    }

    private void finPartie() {
        for (var node : clavier.getChildren()) {
            node.setDisable(true);
        }

        if (nbVies <= 0) {
            labelMot.setText("Perdu ! Le mot était : " + motSecret);
            var winImage = getClass().getResource("/exercice6/pendu7.png");
            if (winImage != null) {
                imageView.setImage(new Image(winImage.toString()));
            }
        } else {
            labelMot.setText("Bravo ! Mot trouvé : " + motSecret);
            var winImage = getClass().getResource("/exercice6/penduWin.png");
            if (winImage != null) {
                imageView.setImage(new Image(winImage.toString()));
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
