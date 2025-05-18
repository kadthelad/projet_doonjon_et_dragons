package jeu.joueurs.classes;

import java.util.ArrayList;
import java.util.List;
import jeu.joueurs.equipement.Equipement;

/**
 * Classe
 */
public abstract class Classe {
  protected String nom;
  protected int pv;
  protected ArrayList<Equipement> listeEquipement;

  public String getNom() {
    return nom;
  }

  public int getPV() {
    return pv;
  }

  public ArrayList<Equipement> getListeEquipement() {
    return listeEquipement;
  }

  @Override
  public String toString() {
    StringBuilder pres = new StringBuilder(nom + "\n\tPV : " + pv + "\n\tÉquipement :");
    for (Equipement equipement : listeEquipement) {
      pres.append("\n\t- ").append(equipement.toString());
    }
    return pres.toString();
  }

}
