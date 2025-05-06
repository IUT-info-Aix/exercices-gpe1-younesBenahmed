package fr.amu.iut.exercice6;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // -- °C --
        Label labelC = new Label("°C");
        labelC.setAlignment(Pos.CENTER);
        Slider sliderC = new Slider(0, 100, 0);
        sliderC.setOrientation(Orientation.VERTICAL);
        sliderC.setShowTickMarks(true);
        sliderC.setShowTickLabels(true);
        sliderC.setMajorTickUnit(10);
        sliderC.setMinorTickCount(0);
        sliderC.setPrefHeight(500);
        TextField textC = new TextField("0,00");
        textC.setAlignment(Pos.CENTER);
        textC.setPrefWidth(60);

        VBox paneC = new VBox(5, labelC, sliderC, textC);
        paneC.setAlignment(Pos.CENTER);
        VBox.setVgrow(sliderC, Priority.ALWAYS);

        // -- °F --
        Label labelF = new Label("°F");
        labelF.setAlignment(Pos.CENTER);
        Slider sliderF = new Slider(0, 212, 32);
        sliderF.setOrientation(Orientation.VERTICAL);
        sliderF.setShowTickMarks(true);
        sliderF.setShowTickLabels(true);
        sliderF.setMajorTickUnit(20);
        sliderF.setMinorTickCount(0);
        sliderF.setPrefHeight(500);
        TextField textF = new TextField("32,00");
        textF.setAlignment(Pos.CENTER);
        textF.setPrefWidth(60);

        VBox paneF = new VBox(5, labelF, sliderF, textF);
        paneF.setAlignment(Pos.CENTER);
        VBox.setVgrow(sliderF, Priority.ALWAYS);

        // Empêche les boucles infinies
        SimpleBooleanProperty updating = new SimpleBooleanProperty(false);

        // Conversion °C → °F
        sliderC.valueProperty().addListener((obs, oldV, newV) -> {
            if (updating.get()) return;
            updating.set(true);
            double c = newV.doubleValue();
            double f = c * 9.0 / 5.0 + 32.0;
            sliderF.setValue(f);
            updating.set(false);
        });

        // Conversion °F → °C
        sliderF.valueProperty().addListener((obs, oldV, newV) -> {
            if (updating.get()) return;
            updating.set(true);
            double f = newV.doubleValue();
            double c = (f - 32.0) * 5.0 / 9.0;
            sliderC.setValue(c);
            updating.set(false);
        });

        // TextField ↔ Slider bidirectionnel
        NumberStringConverter conv = new NumberStringConverter("#0.00");
        javafx.beans.binding.Bindings.bindBidirectional(
                textC.textProperty(), sliderC.valueProperty(), conv
        );
        javafx.beans.binding.Bindings.bindBidirectional(
                textF.textProperty(), sliderF.valueProperty(), conv
        );

        // Assembly
        HBox root = new HBox(10, paneC, paneF);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        // Scene en portrait
        Scene scene = new Scene(root, 150, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Convertisseur °C ↔ °F");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
