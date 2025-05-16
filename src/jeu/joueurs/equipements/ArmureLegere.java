package jeu.joueurs.equipements;

public class ArmureLegere implements Armure {
  int classeArmure;
  String nom;

  public ArmureLegere(String nom, int CA) {
    this.nom = nom;
    this.classeArmure = CA;
  }

  public int getClasseArmure() {
    return classeArmure;
  }

  public void setClasseArmure(int classeArmure) {
    this.classeArmure = classeArmure;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  @Override
  public String toString() {
    String pres = this.nom + ", Classe d'Armure: " + this.classeArmure;
    return pres;
  }

}
