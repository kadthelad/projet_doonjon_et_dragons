package jeu.joueurs.equipement;

import jeu.Entite;
import jeu.Caracteristique;
import java.util.HashMap;
import java.util.Map;


public abstract class Equipement extends Entite {
  protected String nom;
  protected Map<Caracteristique, Integer> caracteristiquesBonus = new HashMap<>();

  public String getNom() {
    return this.nom;
  }

  public abstract Equipement copie();

  public Map<Caracteristique, Integer> getCaracteristiqueBonus() {
    return this.caracteristiquesBonus;
  }
}
