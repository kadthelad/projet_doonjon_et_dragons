package jeu.joueurs.classes;

import jeu.joueurs.equipement.Equipement;
import jeu.joueurs.sorts.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe
 */
public abstract class Classe {
  protected String nom;
  protected int pv;
  protected List<Equipement> listeEquipement;
  protected List<Sort> listeSorts;

  public List<Sort> getListeSorts() {
    return listeSorts;
  }

  public String getNom() {
    return nom;
  }

  public int getPV() {
    return pv;
  }

  public List<Equipement> getListeEquipement() {
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
