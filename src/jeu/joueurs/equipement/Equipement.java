package jeu.joueurs.equipement;

import java.util.HashMap;
import java.util.Map;
import jeu.Caracteristique;


public abstract class Equipement {
  protected String nom;
  protected Map<Caracteristique, Integer> caracteristiquesBonus = new HashMap<>();

  public String getNom() {
    return nom;
  }

  public Map<Caracteristique, Integer> getCaracteristiqueBonus() {
    return this.caracteristiquesBonus;
  }
}
