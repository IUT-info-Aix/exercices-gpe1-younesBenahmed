 module tp.intro.javafx {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.desktop;

    exports com.example.partie1;
    exports com.example.partie3;

    opens com.example.partie3 to javafx.fxml;



}
