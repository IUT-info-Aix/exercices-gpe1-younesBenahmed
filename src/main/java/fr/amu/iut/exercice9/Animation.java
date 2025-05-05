package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Parent customButton = new CustomButton();
        root.setCenter(customButton);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animation Tour + Retour");

        // longueur de chaque côté du tour (depuis le centre)
        double distance = 150;
        // durée de chaque segment
        Duration segmentDuration = Duration.millis(500);

        // 1) Aller à droite
        TranslateTransition t1 = new TranslateTransition(segmentDuration, customButton);
        t1.setByX(distance);

        // 2) Puis en bas
        TranslateTransition t2 = new TranslateTransition(segmentDuration, customButton);
        t2.setByY(distance);

        // 3) Puis à gauche
        TranslateTransition t3 = new TranslateTransition(segmentDuration, customButton);
        t3.setByX(-distance);

        // 4) Puis en haut (retour au centre)
        TranslateTransition t4 = new TranslateTransition(segmentDuration, customButton);
        t4.setByY(-distance);

        // On enchaîne t1→t2→t3→t4, puis (cycleCount=2 + autoReverse) le chemin inverse...
        SequentialTransition seq = new SequentialTransition(
                customButton,
                t1, t2, t3, t4
        );
        seq.setCycleCount(2);        // joue 1× en avant + 1× en arrière
        seq.setAutoReverse(true);

        // Lance l’animation au clic
        customButton.setOnMousePressed(evt -> seq.playFromStart());

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
