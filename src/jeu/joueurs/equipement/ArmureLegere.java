package jeu.joueurs.equipement;

public class ArmureLegere extends Armure {
  int classeArmure;
  String nom;

  public ArmureLegere(String nom, int CA) {
    this.nom = nom;
    this.classeArmure = CA;
  }

  public int getClasseArmure() {
    return classeArmure;
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
