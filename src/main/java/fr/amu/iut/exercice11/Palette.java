package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Palette extends Application {

    // compteurs séparés pour chaque couleur
    private int nbVert  = 0;
    private int nbRouge = 0;
    private int nbBleu  = 0;

    // 1) Propriétés observables
    private final IntegerProperty nbFois         = new SimpleIntegerProperty(0);
    private final StringProperty  couleurNom     = new SimpleStringProperty("");
    private final StringProperty  message        = new SimpleStringProperty("");
    private final StringProperty  couleurPanneau = new SimpleStringProperty("#FFFFFF");

    // Noeuds UI dont on a besoin dans createBindings()
    private Label texteDuHaut;
    private Pane  panneau;
    private Label texteDuBas;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Palette de couleurs");

        // --- Top : Label ---
        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);

        // --- Center : Panneau coloré ---
        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        // --- Bottom : HBox de boutons + label ---
        Button vert  = new Button("Vert");
        Button rouge = new Button("Rouge");
        Button bleu  = new Button("Bleu");

        // 2) Handlers : ils ne touchent QUE les propriétés et compteurs
        vert.setOnAction(e -> {
            nbVert++;
            nbFois.set(nbVert);
            couleurNom.set("Vert");
            message.set("Le Vert est une jolie couleur !");
            couleurPanneau.set("#00FF00");
        });
        rouge.setOnAction(e -> {
            nbRouge++;
            nbFois.set(nbRouge);
            couleurNom.set("Rouge");
            message.set("Le Rouge est une jolie couleur !");
            couleurPanneau.set("#FF0000");
        });
        bleu.setOnAction(e -> {
            nbBleu++;
            nbFois.set(nbBleu);
            couleurNom.set("Bleu");
            message.set("Le Bleu est une jolie couleur !");
            couleurPanneau.set("#0000FF");
        });

        HBox boutons = new HBox(10, vert, rouge, bleu);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10));

        texteDuBas = new Label();
        texteDuBas.setPadding(new Insets(5, 0, 0, 0));

        VBox bas = new VBox(5, boutons, texteDuBas);
        bas.setAlignment(Pos.CENTER);

        // --- Assemblage dans un BorderPane ---
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setTop(texteDuHaut);
        root.setCenter(panneau);
        root.setBottom(bas);

        // 3) Création des bindings
        createBindings();

        // --- Affichage ---
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Met en place tous les bindings entre propriétés et interface.
     */
    private void createBindings() {
        // a) BooleanBinding qui vaut true tant qu'aucun clic n'a eu lieu
        BooleanBinding pasEncoreDeClic = Bindings.equal(nbFois, 0);

        // b) Texte du label du haut :
        //    "Cliquez sur un bouton" si nbFois == 0,
        //    sinon "<Couleur> choisi <nbFois> fois"
        texteDuHaut.textProperty().bind(
                Bindings.when(pasEncoreDeClic)
                        .then("Cliquez sur un bouton")
                        .otherwise(
                                Bindings.concat(
                                        couleurNom,
                                        " choisi ",
                                        nbFois.asString(),
                                        " fois"
                                )
                        )
        );

        // c) Fond du panneau lié à couleurPanneau
        panneau.styleProperty().bind(
                Bindings.concat("-fx-background-color: ", couleurPanneau, ";")
        );

        // d) Texte du bas et couleur du texte, liés à message et couleurPanneau
        texteDuBas.textProperty().bind(message);
        texteDuBas.styleProperty().bind(
                Bindings.concat("-fx-text-fill: ", couleurPanneau, ";")
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
