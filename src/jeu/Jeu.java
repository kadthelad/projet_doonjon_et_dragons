package jeu;

import jeu.joueurs.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Jeu {

  // Liste des personnages
  private static ArrayList<Personnage> listePersonnages = new ArrayList<>();

  public static ArrayList<Personnage> getListePersonnages() {
    return listePersonnages;
  }

  public static void main(String args[]) {
    int nombreJoueurs = InitialisationJoueurs.combienJoueurs();
    // creation des personnages
    listePersonnages = InitialisationJoueurs.creationPersonnages(nombreJoueurs);
  }
}
