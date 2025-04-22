package fr.amu.iut.exercice5;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Fantome extends Personnage {

    private Circle oeilGauche, oeilDroit, retineGauche, retineDroite;
    private Rectangle basCorps;

    public Fantome() {
        super("gauche", Color.BLUE, Color.BLUE);

        oeilGauche = new Circle(6, 6, 2, Color.WHITE);
        retineGauche = new Circle(6, 6, 1, Color.BLACK);

        oeilDroit = new Circle(14, 6, 2, Color.WHITE);
        retineDroite = new Circle(14, 6, 1, Color.BLACK);

        basCorps = new Rectangle(0, 10, 20, 10);
        basCorps.setFill(Color.BLUE);

        getChildren().addAll(basCorps, oeilGauche, oeilDroit, retineGauche, retineDroite);
    }

    @Override
    public void deplacerAGauche() {
        super.deplacerAGauche();
        majYeux(-1, 0);
    }

    @Override
    public void deplacerADroite(double largeurJeu) {
        super.deplacerADroite(largeurJeu);
        majYeux(1, 0);
    }

    @Override
    public void deplacerEnBas(double hauteurJeu) {
        if (getLayoutY() < hauteurJeu - LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() + LARGEUR_PERSONNAGE);
            majYeux(0, 1);
        }
    }

    @Override
    public void deplacerEnHaut() {
        if (getLayoutY() >= LARGEUR_PERSONNAGE) {
            setLayoutY(getLayoutY() - LARGEUR_PERSONNAGE);
            majYeux(0, -1);
        }
    }

    private void majYeux(double dx, double dy) {
        retineGauche.setCenterX(oeilGauche.getCenterX() + dx);
        retineGauche.setCenterY(oeilGauche.getCenterY() + dy);
        retineDroite.setCenterX(oeilDroit.getCenterX() + dx);
        retineDroite.setCenterY(oeilDroit.getCenterY() + dy);
    }
}
