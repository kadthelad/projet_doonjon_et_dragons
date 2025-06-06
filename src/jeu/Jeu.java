package jeu;

import jeu.joueurs.*;
import jeu.maitre.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Jeu {
  private static ArrayList<Personnage> listePersonnages = new ArrayList<>();
  private static ArrayList<Monstre> listeMonstres = new ArrayList<>();

  public static Personnage getPersonnage(int index) { return listePersonnages.get(index); }
  public static int getNombrePersonnages() { return listePersonnages.size(); }

  public static Monstre getMonstre(int index) { return listeMonstres.get(index); }
  public static int getNombreMonstres() { return listeMonstres.size(); }


  public static void main(String args[]) {

    InitialisationJoueurs.creationPersonnages();
    Carte carte = Carte.menuCarte();
  }
}
