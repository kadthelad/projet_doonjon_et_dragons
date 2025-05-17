package jeu;

import jeu.joueurs.*;
import jeu.joueurs.races.*;
import jeu.joueurs.classes.*;
import jeu.joueurs.equipement.*;
import jeu.utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jeu {

  // Liste des personnages, races, classes, et caractéristiques
  // on ne peut pas appeler de methodes en dehors d'un bloc, donc pas de liste.add
  // directement dans la classe Main, donc Arrays.asList()
  private static ArrayList<Personnage> listePersonnages = new ArrayList<>();

  private static ArrayList<Race> listeRaces = new ArrayList<>(
      Arrays.asList(new Nain(), new Elfe(), new Humain(), new Halfelin()));
  private static ArrayList<Classe> listeClasses = new ArrayList<>(
      Arrays.asList(new Clerc(), new Guerrier(), new Magicien(), new Roublard()));

  private static ArrayList<Caracteristique> listeCaracteristiques = new ArrayList<>(
      Arrays.asList(Caracteristique.values()));

  public static ArrayList<Personnage> getListePersonnages() {
    return listePersonnages;
  }

  public static ArrayList<Caracteristique> getListeCaracteristiques() {
    return listeCaracteristiques;
  }

  public static void main(String args[]) {
    creationPersonnages(combienJoueurs());
  }

  // Demander le nombre de joueurs
  public static int combienJoueurs() {
    System.out.println("Sans compter la ou le MJ, combien de joueurs y aura-t-il ?");
    int n = Input.entier();
    while (n <= 0) {
      System.out.print("Merci d'entrer un nombre entier strictement positif : ");
      n = Input.entier();
    }
    return n;
  }

  // Création des personnages
  public static void creationPersonnages(int nombreJoueurs) {
    // pour chaque joueur...
    for (int i = 0; i < nombreJoueurs; i++) {
      System.out.println("Joueur " + (i + 1) + ", nommez votre personnage :");
      String nom = demanderNom();

      // Choix de la race
      System.out.println(nom + ", entrez le numéro associé à la race de votre choix :");
      afficherChoix(listeRaces);
      int indexRace = demanderIndiceValide(listeRaces.size());

      // Choix de la classe
      System.out.println(nom + ", entrez le numéro associé à la classe de votre choix :");
      afficherChoix(listeClasses);
      int indexClasse = demanderIndiceValide(listeClasses.size());

      // Création du personnage
      Race raceChoisie = listeRaces.get(indexRace);
      Classe classeChoisie = listeClasses.get(indexClasse);
      Personnage personnage = new Personnage(nom, raceChoisie, classeChoisie);
      listePersonnages.add(personnage);

      // Affichage récapitulatif
      System.out.println("\n" + personnage.toString());
    }
  }

  // Demander un nom valide
  private static String demanderNom() {
    Pattern pattern = Pattern.compile("([a-z]+([ -][a-z]+)?)+", Pattern.CASE_INSENSITIVE);
    String nom = Input.string();
    Matcher matcher = pattern.matcher(nom);
    while (!matcher.matches()) {
      System.out
          .println("Votre nom ne doit contenir que des lettres et éventuellement un \" \" ou un \"-\" au milieu.");
      nom = Input.string();
      matcher = pattern.matcher(nom);
    }
    return nom;
  }

  // Afficher les choix possibles (races ou classes)
  private static <T> void afficherChoix(ArrayList<T> liste) {
    for (int i = 0; i < liste.size(); i++) {
      System.out.println(i + " : " + liste.get(i).toString());
    }
  }

  // Demander un indice valide pour les choix
  private static int demanderIndiceValide(int tailleListe) {
    int choix = Input.entier();
    while (choix < 0 || choix >= tailleListe) {
      System.out.println("Merci de choisir un numéro valide entre 0 et " + (tailleListe - 1) + " :");
      choix = Input.entier();
    }
    return choix;
  }
}
