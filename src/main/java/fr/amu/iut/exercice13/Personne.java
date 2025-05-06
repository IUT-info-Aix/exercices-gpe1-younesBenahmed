package fr.amu.iut.exercice3;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Personne {

    private final String nom;
    private final IntegerProperty age;
    private final StringProperty villeDeNaissance;

    public Personne(String nom, int age) {
        this.nom = nom;
        // Transforme l’attribut age en propriété observable
        this.age = new SimpleIntegerProperty(age);
        this.villeDeNaissance = new SimpleStringProperty("Paris");
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    /** Pour que FXCollections.observableArrayList(...) puisse écouter les changements d’âge */
    public IntegerProperty ageProperty() {
        return age;
    }

    public String getVilleDeNaissance() {
        return villeDeNaissance.get();
    }

    public StringProperty villeDeNaissanceProperty() {
        return villeDeNaissance;
    }
}
