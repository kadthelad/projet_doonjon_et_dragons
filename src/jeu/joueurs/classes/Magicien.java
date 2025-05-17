package jeu.joueurs.classes;

import java.util.ArrayList;
import java.util.Arrays;

import jeu.joueurs.equipement.Equipement;

/**
 * Magicien
 */
public class Magicien implements Classe {

  private int m_pv = 12;
  private String m_nom = "Magicien.ne";

  private ArrayList<Equipement> listeEquipement = new ArrayList<Equipement>();

  public String getNom() {
    return m_nom;
  }

  public int getPV() {
    return this.m_pv;
  }

  @Override
  public String toString() {
    String pres = m_nom + "\n\tPV : " + m_pv + "\n\tÉquipement :\n";
    for (int i = 0; i < listeEquipement.size(); i++) {
      pres += "\t- " + listeEquipement.get(i).toString() + "\n";
    }
    return pres;
  }

}
