package fr.amu.iut.exercice3;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;
    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args) {
        // 1. Instanciation de la liste observable EN ÉCOUTANT la propriété age
        lesPersonnes = FXCollections.observableArrayList(
                (Personne p) -> new Observable[]{ p.ageProperty() }
        );

        // 2. Création d’un listener qui gère AJOUT, SUPPRESSION, et MISE À JOUR
        unChangementListener = change -> {
            while (change.next()) {
                // Ajout
                if (change.wasAdded()) {
                    for (Personne p : change.getAddedSubList()) {
                        System.out.println("Ajout de : " + p.getNom());
                    }
                }
                // Suppression
                if (change.wasRemoved()) {
                    for (Personne p : change.getRemoved()) {
                        System.out.println("Suppression de : " + p.getNom());
                    }
                }
                // Mise à jour (ici, suite à changement d’âge)
                if (change.wasUpdated()) {
                    int idx = change.getFrom();        // indice du ou des éléments modifiés
                    Personne p = lesPersonnes.get(idx);
                    System.out.println(p.getNom() + " a maintenant " + p.getAge() + " ans");
                }
            }
        };

        // 3. Affectation du listener à la liste
        lesPersonnes.addListener(unChangementListener);

        // 4. TESTS : décommentez successivement pour tester chaque question

        System.out.println("=== Question 1 : Ajouts ===");
        question1();

        System.out.println("\n=== Question 2 : Ajout puis suppression ===");
        question2();

        System.out.println("\n=== Question 3 : Changement d'âge (sans bindings initiaux) ===");
        question3();

        System.out.println("\n=== Question 5 : Séries d'opérations (ajouts, mises à jour, suppressions) ===");
        question5();
    }

    public static void question1() {
        Personne pierre  = new Personne("Pierre", 20);
        Personne paul    = new Personne("Paul",   40);
        Personne jacques = new Personne("Jacques",60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre  = new Personne("Pierre", 20);
        Personne paul    = new Personne("Paul",   40);
        Personne jacques = new Personne("Jacques",60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        Personne pierre  = new Personne("Pierre", 20);
        Personne paul    = new Personne("Paul",   40);
        Personne jacques = new Personne("Jacques",60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        // Change l’âge de Paul : sans extractor sur age, aucun événement « updated » ne déclenche
        paul.setAge(5);
    }

    public static void question5() {
        Personne pierre  = new Personne("Pierre", 20);
        Personne paul    = new Personne("Paul",   40);
        Personne jacques = new Personne("Jacques",60);
        lesPersonnes.addAll(pierre, paul, jacques);
        // Mise à jour de l’âge de chacun
        for (Personne p : lesPersonnes) {
            p.setAge(p.getAge() + 10);
        }
        // Puis suppression
        lesPersonnes.removeAll(paul, pierre);
    }
}
