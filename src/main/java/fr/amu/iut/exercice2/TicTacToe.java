package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.util.Random;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);

        GridPane grid = new GridPane();

        Random random = new Random();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Label label = new Label();
                label.setPrefSize(100, 100);

                int randomChoice = random.nextInt(3);
                String imagePath;

                if (randomChoice == 0) {
                    imagePath = "/exercice2/Croix.png";
                } else if (randomChoice == 1) {
                    imagePath = "/exercice2/Rond.png";
                } else {
                    imagePath = "/exercice2/Vide.png";
                }

                InputStream imageStream = getClass().getResourceAsStream(imagePath);
                if (imageStream != null) {
                    Image image = new Image(imageStream);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(80);
                    imageView.setFitHeight(80);
                    label.setGraphic(imageView);
                }

                grid.add(label, col, row);
            }
        }

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
