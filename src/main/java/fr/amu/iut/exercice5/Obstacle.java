package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {

    public Obstacle(double x, double y, double largeur, double hauteur) {
        super(x, y, largeur, hauteur);
        setFill(Color.GRAY);
    }
}
