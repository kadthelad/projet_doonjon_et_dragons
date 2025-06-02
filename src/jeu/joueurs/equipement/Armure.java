package jeu.joueurs.equipement;

import jeu.Entite;

public abstract class Armure extends Equipement {

  protected int classeArmure;

  public int getClasseArmure() {
    return this.classeArmure;
  }

  public void setClasseArmure(int classeArmure) {
    this.classeArmure = classeArmure;
  }

  @Override
  public String toString() {
    String pres = this.nom + ", Classe d'Armure: " + this.classeArmure;
    return pres;
  }
}
