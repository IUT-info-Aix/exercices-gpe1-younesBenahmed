package fr.amu.iut.exercice1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class FenetreLogiciel extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Premier exemple manipulant les conteneurs");

        // 1) Barre de menu en haut
        MenuBar menuBar = new MenuBar(
                new Menu("File"),
                new Menu("Edit"),
                new Menu("Help")
        );

        // 2) Pane de gauche : VBox avec spacing, padding et boutons full-width
        VBox leftPane = new VBox(10);
        leftPane.setPadding(new Insets(15));
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setPrefWidth(150);

        Label boutonsLabel = new Label("Boutons :");
        Button btn1 = new Button("Bouton 1");
        Button btn2 = new Button("Bouton 2");
        Button btn3 = new Button("Bouton 3");
        for (Button b : new Button[]{btn1, btn2, btn3}) {
            b.setMaxWidth(Double.MAX_VALUE);
        }
        leftPane.getChildren().addAll(boutonsLabel, btn1, btn2, btn3);

        // 3) Séparateur vertical plein écran
        Separator sepVertical = new Separator(Orientation.VERTICAL);
        sepVertical.setPrefWidth(2);
        sepVertical.setMaxHeight(Double.MAX_VALUE);

        // 4) Formulaire centré dans un GridPane
        GridPane formPane = new GridPane();
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(20));
        formPane.setHgap(10);
        formPane.setVgap(10);

        formPane.add(new Label("Name:"), 0, 0);
        formPane.add(new TextField(), 1, 0);
        formPane.add(new Label("Email:"), 0, 1);
        formPane.add(new TextField(), 1, 1);
        formPane.add(new Label("Password:"), 0, 2);
        formPane.add(new PasswordField(), 1, 2);

        HBox btnBox = new HBox(10, new Button("Submit"), new Button("Cancel"));
        btnBox.setAlignment(Pos.CENTER_RIGHT);
        formPane.add(btnBox, 1, 3);

        // 5) Séparateur horizontal et label de bas de page
        Separator sepHorizontal = new Separator(Orientation.HORIZONTAL);
        sepHorizontal.setPrefHeight(2);
        sepHorizontal.setMaxWidth(Double.MAX_VALUE);

        Label bottomLabel = new Label("Ceci est un label de bas de page");
        bottomLabel.setMaxWidth(Double.MAX_VALUE);
        bottomLabel.setAlignment(Pos.CENTER);
        bottomLabel.setPadding(new Insets(10));

        VBox bottomBox = new VBox(sepHorizontal, bottomLabel);
        bottomBox.setFillWidth(true);

        // 6) Assemblage dans un BorderPane
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(menuBar);

        // On place la VBox + sepVertical dans un HBox pour garantir que le sep s'étire
        HBox leftWithSep = new HBox(leftPane, sepVertical);
        leftWithSep.setFillHeight(true);
        mainLayout.setLeft(leftWithSep);

        mainLayout.setCenter(formPane);
        mainLayout.setBottom(bottomBox);

        // 7) Scene & Stage
        Scene scene = new Scene(mainLayout, 800, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
