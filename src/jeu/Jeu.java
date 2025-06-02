package jeu;

import jeu.joueurs.*;
import jeu.maitre.*;
import jeu.maitre.maitreJeu.InterfaceUtilisateur;

import java.util.ArrayList;
import java.util.Arrays;

public class Jeu {
  // Liste des Cartes
  private static ArrayList<Carte> listeCartes = new ArrayList<>();

  public static ArrayList<Carte> getListeCartes() {
    return listeCartes;
  }

  // Liste des Monstres
  private static ArrayList<Monstre> listeMonstres = new ArrayList<>();

  public static ArrayList<Monstre> getListeMonstres () {
    return listeMonstres;
  }

  // Liste des personnages
  private static ArrayList<Personnage> listePersonnages = new ArrayList<>();

  public static ArrayList<Personnage> getListePersonnages() {
    return listePersonnages;
  }



  public static void main(String args[]) {
    boolean game = true;
    while (game)
    {
      int exitValue = InterfaceUtilisateur.MainMenu(listeCartes, listeMonstres, listePersonnages);
      switch (exitValue)
      {
        case 0: // QUITTER LE JEU
          game = false;
          break;
        case 1: // CONTINUER LE JEU
          break;
      }
    }
    // creation des monstres cartes etc

    // creation des personnages
    int nombreJoueurs = InitialisationJoueurs.combienJoueurs();
    listePersonnages = InitialisationJoueurs.creationPersonnages(nombreJoueurs);

    // debut jeu
    
    // listePersonnages.get(1).getListeSorts().get(0).lancer();
  }
}
