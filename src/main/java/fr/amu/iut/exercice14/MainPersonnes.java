// src/main/java/fr/amu/iut/exercice4/MainPersonnes.java
package fr.amu.iut.exercice4;

import javafx.beans.Observable;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static SimpleListProperty<Personne> lesPersonnes;
    private static IntegerProperty ageMoyen;
    private static IntegerProperty nbParisiens;

    private static IntegerBinding calculAgeMoyen;
    private static IntegerBinding calculnbParisiens;

    public static void main(String[] args) {

        // 1) Liste observable ÉCOUTANT age et ville de naissance
        lesPersonnes = new SimpleListProperty<>(
                FXCollections.observableArrayList(
                        (Personne p) -> new Observable[]{
                                p.ageProperty(),
                                p.villeDeNaissanceProperty()
                        }
                )
        );

        ageMoyen    = new SimpleIntegerProperty(0);
        nbParisiens = new SimpleIntegerProperty(0);

        // 2) Binding bas-niveau pour l’âge moyen
        calculAgeMoyen = new IntegerBinding() {
            {
                bind(lesPersonnes);
            }
            @Override
            protected int computeValue() {
                if (lesPersonnes.isEmpty()) return 0;
                int sum = 0;
                for (Personne p : lesPersonnes) {
                    sum += p.getAge();
                }
                return sum / lesPersonnes.size();
            }
        };
        ageMoyen.bind(calculAgeMoyen);

        // 3) Binding bas-niveau pour le nombre de Parisiens
        calculnbParisiens = new IntegerBinding() {
            {
                bind(lesPersonnes);
            }
            @Override
            protected int computeValue() {
                int count = 0;
                for (Personne p : lesPersonnes) {
                    if ("Paris".equals(p.getVilleDeNaissance())) {
                        count++;
                    }
                }
                return count;
            }
        };
        nbParisiens.bind(calculnbParisiens);

        // 4) Tests
        question1();
//        question2();
    }

    public static void question1() {
        System.out.println("1 - L'âge moyen est de " + ageMoyen.get() + " ans");
        Personne pierre  = new Personne("Pierre", 20);
        Personne paul    = new Personne("Paul",   40);
        Personne jacques = new Personne("Jacques",60);
        lesPersonnes.add(pierre);
        System.out.println("2 - L'âge moyen est de " + ageMoyen.get() + " ans");
        lesPersonnes.add(paul);
        System.out.println("3 - L'âge moyen est de " + ageMoyen.get() + " ans");
        lesPersonnes.add(jacques);
        System.out.println("4 - L'âge moyen est de " + ageMoyen.get() + " ans");
        paul.setAge(100);
        System.out.println("5 - L'âge moyen est de " + ageMoyen.get() + " ans");
        lesPersonnes.remove(paul);
        System.out.println("6 - L'âge moyen est de " + ageMoyen.get() + " ans");
    }

    public static void question2() {
        System.out.println("Il y a " + nbParisiens.get() + " parisiens");
        lesPersonnes.get(0).setVilleDeNaissance("Marseille");
        System.out.println("Il y a " + nbParisiens.get() + " parisiens");
        lesPersonnes.get(1).setVilleDeNaissance("Montpellier");
        System.out.println("Il y a " + nbParisiens.get() + " parisiens");
        for (Personne p : lesPersonnes) {
            p.setVilleDeNaissance("Paris");
        }
        System.out.println("Il y a " + nbParisiens.get() + " parisiens");
    }
}
