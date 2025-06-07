package jeu;

import jeu.joueurs.*;
import jeu.maitre.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Jeu {
  private static ArrayList<Monstre> listeMonstres = new ArrayList<>();
  static {
    listeMonstres.add(Monstre.orc());
    listeMonstres.add(Monstre.dragon());
    listeMonstres.add(Monstre.arrachnide());
  }
  public static Monstre getMonstre(int index) { return listeMonstres.get(index); }
  public static int getNombreMonstres() { return listeMonstres.size(); }
  public static ArrayList<Monstre> getListeMonstres() {
    return listeMonstres;
  }

  private static ArrayList<Personnage> listePersonnages = new ArrayList<>();
  public static Personnage getPersonnage(int index) {
    return listePersonnages.get(index);
  }
  public static int getNombrePersonnages() {
    return listePersonnages.size();
  }
  public static void addPersonnage(Personnage p) {
    listePersonnages.add(p);
  }
  public static ArrayList<Personnage> getListePersonnages() {
    return listePersonnages;
  }


  public static void main(String args[]) {

    InitialisationJoueurs.main();
    Donjon donjon = new Donjon();
    donjon.lancerJeu();
  }
}
