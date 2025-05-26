package jeu.joueurs.equipement;

public class Armure implements Equipement {
  protected int classeArmure;
  protected String nom;

  public int getClasseArmure() {
    return classeArmure;
  }

  public void setClasseArmure(int classeArmure) {
    this.classeArmure = classeArmure;
  }

  public String getNom() {
    return nom;
  }

  @Override
  public String toString() {
    String pres = this.nom + ", Classe d'Armure: " + this.classeArmure;
    return pres;
  }

}
