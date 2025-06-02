package jeu.joueurs.equipement;

public class ArmureLegere extends Armure {
  int classeArmure;
  String nom;

  public ArmureLegere(String nom, int CA) {
    this.nom = nom;
    this.classeArmure = CA;
  }
}
