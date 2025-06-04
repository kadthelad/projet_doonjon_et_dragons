package jeu.joueurs.classes;

import java.util.Arrays;
import jeu.joueurs.sorts.*;
import jeu.joueurs.equipement.*;

public class Clerc extends Classe {

  public Clerc() {
    nom = "Clerc";
    pv = 16;
    
    this.listeArmes = Arrays.asList(
      Catalogue.getArmeParNom("Masse d'armes"),
      Catalogue.getArmeParNom("Arbalète légère"));
    this.listeArmures = Arrays.asList(
      Catalogue.getArmureParNom("Armure d'écailles"));

    listeSorts = Arrays.asList ( new ArmeMagique());
  }
}
