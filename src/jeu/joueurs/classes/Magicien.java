package jeu.joueurs.classes;

import java.util.Arrays;

import jeu.joueurs.equipement.*;

public class Magicien extends Classe {

  public Magicien() {
    nom = "Magicien.ne";
    pv = 12;
    listeArmes = Arrays.asList(
        Catalogue.getArmeParNom("Bâton"),
        Catalogue.getArmeParNom("Fronde"));
  }
}
