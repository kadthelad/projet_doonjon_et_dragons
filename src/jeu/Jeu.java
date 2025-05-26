package jeu;

import jeu.joueurs.*;
import jeu.maitre.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Jeu {

  // Liste des personnages
  private static ArrayList<Personnage> listePersonnages = new ArrayList<>();

  public static ArrayList<Personnage> getListePersonnages() {
    return listePersonnages;
  }

  public static void main(String args[]) {
    // creation des monstres cartes etc

    // creation des personnages
    int nombreJoueurs = InitialisationJoueurs.combienJoueurs();
    listePersonnages = InitialisationJoueurs.creationPersonnages(nombreJoueurs);

    // debut jeu
    
  }
}
