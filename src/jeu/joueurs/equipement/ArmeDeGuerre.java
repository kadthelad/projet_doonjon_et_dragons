package jeu.joueurs.equipement;

import java.util.HashMap;
import java.util.Map;

import jeu.Caracteristique;

public class ArmeDeGuerre extends Arme {

  private Map<Caracteristique, Integer> caracteristiquesBonus = new HashMap<>() {
    {
      put(Caracteristique.VITESSE, -2);
      put(Caracteristique.FORCE, 4);
    }
  };

  public ArmeDeGuerre(String nom, String degats, int portee) {
    super(nom, degats, portee);
  }

  public ArmeDeGuerre copie() {
    return new ArmeDeGuerre(this.nom, this.degats, this.portee);
  }
}
