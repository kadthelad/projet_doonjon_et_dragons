package jeu.joueurs.equipement;

import jeu.Caracteristique;

public class ArmeDistance extends Arme {
  private Caracteristique caracteristique = Caracteristique.DEXTERITE;
  private boolean isArme = true;

  @Override
  public Caracteristique getCaracteristique() {
    return caracteristique;
  }

  public ArmeDistance(String nom, String d, int p) {
    this.nom = nom;
    this.degats = d;
    this.portee = p;
  }

}
