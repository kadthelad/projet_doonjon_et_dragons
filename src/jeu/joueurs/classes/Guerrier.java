package jeu.joueurs.classes;

import java.util.Arrays;

import jeu.joueurs.equipement.*;

public class Guerrier extends Classe {

  public Guerrier() {
    nom = "Guerrier.re";
    pv = 20;
    listeArmes = Arrays.asList(
        Catalogue.getArmeParNom("Épée longue"),
        Catalogue.getArmeParNom("Arbalète légère"));
    listeArmures = Arrays.asList(
        Catalogue.getArmureParNom("Cotte de mailles"));
  }
}
