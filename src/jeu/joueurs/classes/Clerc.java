package jeu.joueurs.classes;

import java.util.ArrayList;
import java.util.Arrays;

import jeu.joueurs.equipement.*;

/**
 * Clerc
 */
public class Clerc implements Classe {

  private String m_nom = "Clerc";

  private int m_pv = 16;

  private ArrayList<Equipement> listeEquipement = new ArrayList<Equipement>(
      Arrays.asList(
          new ArmeCourante("Masse d'Armes", "1d6", 1),
          new ArmureLegere("Armure d'Ecailles", 9),
          new ArmeDistance("Arbalete Legere", "1d8", 16)));

  public String getNom() {
    return m_nom;
  }

  public int getPV() {
    return this.m_pv;
  }

  // presentation de la classe, pv et armes
  @Override
  public String toString() {
    String pres = m_nom + "\n\tPV : " + m_pv + "\n\tÉquipement :";
    for (int i = 0; i < listeEquipement.size(); i++) {
      pres += "\n\t- " + listeEquipement.get(i).toString();
    }
    return pres;
  }

}
