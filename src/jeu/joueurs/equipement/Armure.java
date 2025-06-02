package jeu.joueurs.equipement;

import jeu.Entite;

public abstract class Armure extends Entite {

  protected int classeArmure;
  protected String nom;

  public int getClasseArmure() {
    return this.classeArmure;
  }

  public void setClasseArmure(int classeArmure) {
    this.classeArmure = classeArmure;
  }

  public String getNom() {
    return this.nom;
  }

  @Override
  public String toString() {
    String pres = this.nom + ", Classe d'Armure: " + this.classeArmure;
    return pres;
  }
}
