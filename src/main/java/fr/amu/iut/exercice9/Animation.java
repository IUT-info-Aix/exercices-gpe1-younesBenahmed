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
        CustomButton customButton = new CustomButton();
        root.setCenter(customButton);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Animation Tour Complet");

        // Affiche la fenêtre pour que le bouton soit layouté
        primaryStage.show();
        // Force le calcul des CSS + layout pour avoir de vrais bounds
        customButton.applyCss();
        customButton.layout();

        // Récupère dimensions via les bounds
        double W  = scene.getWidth();
        double H  = scene.getHeight();
        double bw = customButton.getBoundsInParent().getWidth();
        double bh = customButton.getBoundsInParent().getHeight();

        // Calcul des demi-distances du centre aux bords
        double dx = (W  - bw) / 2.0;  // distance centre → bord droit
        double dy = (H  - bh) / 2.0;  // distance centre → bord bas

        Duration dur = Duration.seconds(1);

        // 1) Centre → bord droit
        TranslateTransition t1 = new TranslateTransition(dur, customButton);
        t1.setByX( dx);

        // 2) bord droit → coin haut-droit
        TranslateTransition t2 = new TranslateTransition(dur, customButton);
        t2.setByY(-dy);

        // 3) coin haut-droit → coin haut-gauche
        TranslateTransition t3 = new TranslateTransition(dur, customButton);
        t3.setByX(- (W - bw));

        // 4) haut-gauche → bas-gauche
        TranslateTransition t4 = new TranslateTransition(dur, customButton);
        t4.setByY(  H - bh);

        // 5) bas-gauche → bas-droit
        TranslateTransition t5 = new TranslateTransition(dur, customButton);
        t5.setByX( W - bw);

        // 6) bas-droit → mi-hauteur droite
        TranslateTransition t6 = new TranslateTransition(dur, customButton);
        t6.setByY(-dy);

        // 7) mi-hauteur droite → retour centre
        TranslateTransition t7 = new TranslateTransition(dur, customButton);
        t7.setByX(-dx);

        SequentialTransition seq = new SequentialTransition(
                customButton,
                t1, t2, t3, t4, t5, t6, t7
        );
        seq.setCycleCount(1);

        // On déclenche au clic
        customButton.setOnMousePressed(evt -> seq.playFromStart());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
