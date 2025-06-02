package jeu.joueurs.classes;

import java.util.Arrays;

import jeu.joueurs.sorts.*;
import jeu.joueurs.equipement.*;

public class Clerc extends Classe {

  public Clerc() {
    nom = "Clerc";
    pv = 16;
    listeEquipement = Arrays.asList(
        new ArmeCourante("Masse d'Armes", "1d6", 1),
        new ArmureLegere("Armure d'Ecailles", 9),
        new ArmeDistance("Arbalete Legere", "1d8", 16));
    // listeSorts = Arrays.asList ( new ArmeMagique());
  }
}
