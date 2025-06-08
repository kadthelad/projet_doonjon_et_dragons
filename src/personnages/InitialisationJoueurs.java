package personnages;

import entite.Personnage;
import personnages.races.*;
import jeu.Jeu;
import personnages.classes.*;
import utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InitialisationJoueurs {

  // Liste des races, classes, et caractéristiques
  // on ne peut pas appeler de méthodes en dehors d'un bloc, donc pas de liste.add
  private static ArrayList<Race> listeRaces = new ArrayList<>(
      Arrays.asList(new Nain(), new Elfe(), new Humain(), new Halfelin()));
  private static ArrayList<Classe> listeClasses = new ArrayList<>(
      Arrays.asList(new Clerc(), new Guerrier(), new Magicien(), new Roublard()));

  public static void main() {
    // nombre de joueurs
    Output.clear();
    int n = Input.entier(1, 10, "Sans compter la ou le MJ, combien de joueurs y aura-t-il ?\n");
    for (int i = 0; i < n; i++) {
      Jeu.addPersonnage(creationPersonnage(i + 1));
    }
  }

  // Création d'un personnage
  public static Personnage creationPersonnage(int numero) {
    Personnage personnage;
    do {
      Output.clear();
      Output.println("Joueur " + numero + ", nommez votre personnage :");
      String nom = demanderNom();
      int indexRace;
      int indexClasse;

      // Choix de la race
      Output.clear();
      Output.println(nom + ", entrez le numéro associé à la race de votre choix :");
      indexRace = choix(listeRaces);

      // Choix de la classe
      Output.clear();
      Output.println(nom + ", entrez le numéro associé à la classe de votre choix :");
      indexClasse = choix(listeClasses);

      // Création du personnage
      Race raceChoisie = listeRaces.get(indexRace);
      Classe classeChoisie = listeClasses.get(indexClasse);
      personnage = new Personnage(nom, raceChoisie, classeChoisie);

      // récap + confirmation
      Output.clear();
      Output.println(personnage.toString());
    } while (!Input.confirmer("Valider le personnage ?"));
    return personnage;
  }

  // Demander un nom valide
  private static String demanderNom() {
    Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z -]{1,}[a-zA-Z]", Pattern.CASE_INSENSITIVE);
    String nom = Input.string();
    Matcher matcher = pattern.matcher(nom);
    while (!matcher.matches()) {
      Output.println("Votre nom ne doit contenir que des lettres et éventuellement un \" \" ou un \"-\" au milieu.");
      nom = Input.string();
      matcher = pattern.matcher(nom);
    }
    return nom;
  }

  // Afficher les choix possibles (races ou classes)
  private static <T> int choix(ArrayList<T> liste) {
    for (int i = 0; i < liste.size(); i++) {
      Output.println((i + 1) + " : " + liste.get(i).toString());
    }
    return Input.entier(1, liste.size(), "Votre choix : ") - 1;
  }
}
