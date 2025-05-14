package jeu;

import jeu.joueurs.*;
import jeu.joueurs.races.*;
import jeu.joueurs.classes.*;
import jeu.joueurs.equipements.*;
import jeu.utils.*;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jeu {

  // Liste des personnages, pour pouvoir facilement les utiliser partout
  private static ArrayList<Personnage> listePersonnages = new ArrayList<>();

  public static ArrayList<Personnage> getListePersonnages() {
    return listePersonnages;
  }

  // liste pour facilement énumerer races/classes lors de la création des persos
  private static ArrayList<Race> listeRaces = new ArrayList<>(
      Arrays.asList(new Nain()));
  // on ne peut pas appeler de methodes en dehors d'un bloc, donc pas de liste.add
  // directement dans la classe Main, donc Arrays.asList()

  private static ArrayList<Classe> listeClasses = new ArrayList<Classe>(
      Arrays.asList(new Clerc(), new Guerrier(), new Magicien(), new Roublard()));

  static private ArrayList<String> listeCaracteristiques = new ArrayList<>(
      Arrays.asList("force", "dexterite", "vitesse", "initiative"));

  static public ArrayList<String> getListeCaracteristiques() {
    return listeCaracteristiques;
  }

  // Fonction principale, qui appelle les differentes methodes, doit rester le
  // plus simple possible
  public static void main(String args[]) {
    creationPersonnages(combienJoueurs());
  }

  public static int combienJoueurs() {
    System.out.println("Sans compter le maitre du jeu, combien de joueurs y aura-t-il ?");
    int n = Input.entier();
    while (n < 0) {
      System.out.print("Merci d'entrer un nombre entier positif : ");
      n = Input.entier();
    }
    return n;
  }

  public static int creationPersonnages(int nombreJoueurs) {

    // pour chaque joueur...
    for (int i = 0; i < nombreJoueurs; i++) {
      Pattern pattern = Pattern.compile("([a-z]+([ -][a-z]+)?)*", Pattern.CASE_INSENSITIVE);
      System.out.println("Joueur " + (i + 1) + ", nommez votre personnage");
      String nom = Input.string();
      Matcher matcher = pattern.matcher(nom);
      while (!matcher.matches()) {
        System.out
            .println("Votre nom ne doit contenir que des lettres et eventuellement un \" \" ou un \"-\" au milieu.");
        nom = Input.string();
        matcher = pattern.matcher(nom);
      }

      System.out.println(nom + ", entrer le numéro associé à la race de votre choix :");
      for (int j = 0; j < listeRaces.size(); j++) {
        // on affiche les informations de chaque race
        System.out.print(j + " : " + listeRaces.get(j).presentation());
      }
      int race = Input.entier();

      System.out.println(nom + ", entrer le numéro associé à la classe de votre choix :");
      for (int j = 0; j < listeClasses.size(); j++) {
        // informations pour les classes
        System.out.print(j + " : " + listeClasses.get(j).presentation() + "\n");
      }
      int classe = Input.entier();

      // on crée le personnage en fonction des parametre choisis
      listePersonnages.add(new Personnage(nom, listeRaces.get(race), listeClasses.get(classe)));
      Personnage p = listePersonnages.get(i);
      System.out.println(p.toString());
    }

    return 1;
  }

}
