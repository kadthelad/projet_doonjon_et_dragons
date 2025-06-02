package jeu.joueurs.classes;

import java.util.ArrayList;
import java.util.List;
import jeu.joueurs.equipement.Arme;
import jeu.joueurs.equipement.Armure;

/**
 * Classe
 */
public abstract class Classe {
  protected String nom;
  protected int pv;
  protected List<Arme> listeArmes;
  protected List<Armure> listeArmures;

  public String getNom() {
    return nom;
  }

  public int getPV() {
    return pv;
  }

  public List<Arme> getListeArmes() {
    return this.listeArmes;
  }

  public List<Armure> getListeArmures() {
    return this.listeArmures;
  }

  @Override
  public String toString() {
    StringBuilder pres = new StringBuilder(nom + "\n\tPV : " + pv + "\n\tÉquipement :");
    for (Arme arme : listeArmes) {
      pres.append("\n\t- ").append(arme.toString());
    }
    for (Armure armure : listeArmures) {
      pres.append("\n\t- ").append(armure.toString());
    }
    return pres.toString();
  }

}
