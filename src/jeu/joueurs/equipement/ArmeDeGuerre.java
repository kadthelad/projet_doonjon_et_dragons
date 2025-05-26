package jeu.joueurs.equipement;

public class ArmeDeGuerre extends Arme {
  public ArmeDeGuerre(String nom, String d, int p) {
    this.nom = nom;
    this.degats = d;
    this.portee = p;
  }
}
