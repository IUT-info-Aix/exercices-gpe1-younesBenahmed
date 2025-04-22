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

        // Barre de menu
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(new Menu("File"), new Menu("Edit"), new Menu("Help"));

        // Barre verticale de gauche (avec boutons centrés)
        VBox leftPane = new VBox(10);
        leftPane.setPadding(new Insets(15));
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPrefWidth(150);

        Label boutonsLabel = new Label("Boutons :");
        Button btn1 = new Button("Bouton 1");
        Button btn2 = new Button("Bouton 2");
        Button btn3 = new Button("Bouton 3");
        btn1.setMaxWidth(Double.MAX_VALUE);
        btn2.setMaxWidth(Double.MAX_VALUE);
        btn3.setMaxWidth(Double.MAX_VALUE);

        leftPane.getChildren().addAll(boutonsLabel, btn1, btn2, btn3);

        // Ajout séparateur entre gauche et centre
        Separator sepVertical = new Separator(Orientation.VERTICAL);

        // Formulaire au centre parfaitement centré
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

        // Label en bas
        Label bottomLabel = new Label("Ceci est un label de bas de page");
        bottomLabel.setMaxWidth(Double.MAX_VALUE);
        bottomLabel.setAlignment(Pos.CENTER);
        bottomLabel.setPadding(new Insets(10));

        // Séparateur horizontal bas
        Separator sepHorizontalBas = new Separator();

        // Assemblage général précis avec séparateurs visibles
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(menuBar);

        // Utilisation d'un HBox pour intégrer la barre gauche et le séparateur vertical
        HBox leftWithSeparator = new HBox();
        leftWithSeparator.getChildren().addAll(leftPane, sepVertical);

        mainLayout.setLeft(leftWithSeparator);
        mainLayout.setCenter(formPane);

        // VBox pour le bas avec séparateur horizontal et label
        VBox bottomBox = new VBox(sepHorizontalBas, bottomLabel);
        mainLayout.setBottom(bottomBox);

        Scene scene = new Scene(mainLayout, 800, 450);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}

