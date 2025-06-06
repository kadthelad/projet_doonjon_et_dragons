package jeu;

import jeu.joueurs.*;
import jeu.maitre.*;
import jeu.maitre.maitreJeu.InterfaceUtilisateur;

import java.util.ArrayList;
import java.util.Arrays;

public class Jeu {
  private static ArrayList<Carte> listeCartes = new ArrayList<>();

  private static int indexCarte = 0;

  public static ArrayList<Carte> getListeCartes() {
    return listeCartes;
  }

  public static int getNombreCartes() {
    return listeCartes.size();
  }

  public static Carte getCarte(int index) {
    return listeCartes.get(index);
  }

  public static Carte getCarteEnCours() {
    return (listeCartes.size() > indexCarte) ? listeCartes.get(indexCarte) : null;
  }

  private static ArrayList<Monstre> listeMonstres = new ArrayList<>();

  public static ArrayList<Monstre> getListeMonstres () {
    return listeMonstres;
  }

  public static Monstre getMonstre(int index) {
    return listeMonstres.get(index);
  }

  public static int getNombreMonstres() {
    return listeMonstres.size();
  }

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
  }
}
