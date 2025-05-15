package jeu;

import jeu.joueurs.*;
import jeu.joueurs.races.*;
import jeu.joueurs.classes.*;
import jeu.joueurs.equipements.*;
import jeu.utils.*;

import jeu.maitre.*;

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

  // Liste des cartes (il faudra probablement naviguer sur plusieurs cartes dans l'étape 2, donc je prévois le coup)
  private static ArrayList<Carte> listeCartes = new ArrayList<>();

  // Fonction principale, qui appelle les differentes methodes, doit rester le
  // plus simple possible
  public static void main(String args[]) {
    //creationPersonnages(combienJoueurs());
    //creationCarte();
    ajoutCarte();
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

  public static int ajoutCarte()
  {
    listeCartes.add(creationCarte());
    return 1;
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

  public static Carte creationCarte()
  {
    int userInput;
    String userStrInput;

    // Création de la carte //
    System.out.println("Quelle sera la taille de la carte?");
    int mapTaille = Input.entier();
    Carte carteCree = new Carte(mapTaille);
    boolean inMapEditor = true;

    while (inMapEditor)
    {
      carteCree.afficherCarte(mapTaille, carteCree.getObstacles());
      System.out.println("----------------------\nChoisissez une action:\n[0] Ajouter un obstacle\n[1] Supprimer un obstacle\n[2] Continuer");
      userInput = Input.entier();
      switch (userInput)
      {
        case 0:
          System.out.print("--- AJOUT D'UN OBSTACLE ---\nEntrez la position à laquelle vous voulez créer un obstacle (exemple:" +
                  "2, 3): ");
          userStrInput = Input.string();
          String[] result = userStrInput.split(",");
          if (result.length != 2)
          {
            System.out.println("Veuillez respecter le format '2, 3'.");
          }
          break;
        case 1:
          if (carteCree.getObstacles().length > 0) {
            System.out.print("--- SUPPRESSION D'UN OBSTACLE ---\nEntrez la position à laquelle vous voulez retirer un obstacle (exemple:" +
                    "2, 3): ");
            userStrInput = Input.string();
            // COMPLETER
          }
          else
          {
            System.out.println("Il n'y a pas d'obstacles dans la carte!");
          }
          break;
        case 2:
          inMapEditor = false;
          break;
      }
    }

    return carteCree;
  }
}
