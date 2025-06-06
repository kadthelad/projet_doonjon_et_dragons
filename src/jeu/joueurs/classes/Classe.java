package jeu.joueurs.classes;

import java.util.ArrayList;
import java.util.List;
import jeu.joueurs.equipement.Arme;
import jeu.joueurs.equipement.Armure;
import jeu.joueurs.sorts.*;

/**
 * Classe
 */
public abstract class Classe {
  protected String nom;
  protected int pv;
  protected List<Arme> listeArmes = new ArrayList<>();
  protected List<Armure> listeArmures = new ArrayList<>();
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

  public List<Arme> getListeArmes() {
    return this.listeArmes;
  }

  public List<Armure> getListeArmures() {
    return this.listeArmures;
  }

  @Override
  public String toString() {
    StringBuilder pres = new StringBuilder(nom + "\n\tPV : " + pv + "\n\tÉquipement :");
    if (!this.listeArmes.isEmpty()) {
      for (Arme arme : this.listeArmes) {
        pres.append("\n\t- ").append(arme.toString());
      }
    }
    if (!this.listeArmures.isEmpty()) {

      for (Armure armure : this.listeArmures) {
        pres.append("\n\t- ").append(armure.toString());
      }
    }
    return pres.toString();
  }

}
