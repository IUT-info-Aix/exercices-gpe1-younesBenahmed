module tp.intro.javafx {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;

    // pour vos autres exercices
    exports com.example.partie1;
    exports com.example.partie3;
    opens   com.example.partie3 to javafx.fxml;
    exports fr.amu.iut.exercice2;
    // pour l’exo7 (CounterMain + CounterController)
    exports fr.amu.iut.exercice7;              // nécessaire à LauncherImpl pour instancier CounterMain
    opens   fr.amu.iut.exercice7 to javafx.fxml;  // nécessaire à FXMLLoader pour injecter @FXML


    // 1) Export pour que javafx.graphics (LauncherImpl) puisse voir et instancier LoginMain
    exports fr.amu.iut.exercice8;
    opens fr.amu.iut.exercice8 to javafx.fxml;
    exports fr.amu.iut.exercice9;
    exports fr.amu.iut.exercice10;
    opens   fr.amu.iut.exercice10 to javafx.fxml;
    exports fr.amu.iut.exercice1;
    exports fr.amu.iut.exercice5;
    opens   fr.amu.iut.exercice5 to javafx.fxml;
    exports fr.amu.iut.exercice6;
    opens   fr.amu.iut.exercice6 to javafx.fxml;

}


