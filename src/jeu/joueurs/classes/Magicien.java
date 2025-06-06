package jeu.joueurs.classes;

import java.util.Arrays;

import jeu.joueurs.equipement.*;
import jeu.joueurs.sorts.*;

public class Magicien extends Classe {

  public Magicien() {
    nom = "Magicien.ne";
    pv = 12;
    listeArmes = Arrays.asList(
        Catalogue.getArmeParNom("Bâton"),
        Catalogue.getArmeParNom("Fronde"));
    // listeArmures = Arrays.asList();

    listeSorts = Arrays.asList(
      new Guerison(),
      new BoogieWoogie(),
      new ArmeMagique());
  }
}
