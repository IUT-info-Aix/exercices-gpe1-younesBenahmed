package fr.amu.iut.exercice9;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;

public class CustomButton extends Parent {

    private ImageView image;

    public CustomButton() {
        StackPane st = new StackPane();

        // <- ici on passe par getResource pour avoir une URL valide :
        String imagePath = "/exercice9/Rond.png";
        URL url = getClass().getResource(imagePath);
        if (url == null) {
            throw new RuntimeException("Impossible de charger l’image : " + imagePath);
        }
        image = new ImageView(new Image(url.toExternalForm()));

        Label label = new Label("Clic");
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        label.setTextFill(Color.ORANGERED);

        st.getChildren().addAll(image, label);
        this.getChildren().add(st);
    }

    public void addOnMousePressed(EventHandler<MouseEvent> eventHandler) {
        image.setOnMousePressed(eventHandler);
    }
}
