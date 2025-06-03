package jeu.joueurs.equipement;

import jeu.Caracteristique;

public class ArmeDistance extends Arme {
  private Caracteristique caracteristique = Caracteristique.DEXTERITE;

  public ArmeDistance(String nom, String degats, int portee) {
    super(nom, degats, portee);
  }

  public ArmeDistance copie() {
    return new ArmeDistance(this.nom, this.degats, this.portee);
  }

  @Override
  public Caracteristique getCaracteristique() {
    return caracteristique;
  }
}
