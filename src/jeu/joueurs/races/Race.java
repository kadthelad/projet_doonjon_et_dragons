package jeu.joueurs.races;

import jeu.*;
import java.util.Map;

public abstract class Race {
  protected String nom;

  protected Map<Caracteristique, Integer> bonusCaracteristiques;

  public String getNom() {
    return nom;
  }

  public int bonus(Caracteristique c) {
    return bonusCaracteristiques.getOrDefault(c, 0);
  }

  @Override
  public String toString() {
    StringBuilder pres = new StringBuilder(nom);
    for (Caracteristique c : Caracteristique.values()) {
      int n = bonus(c); if (n != 0) {
        pres.append("\n\t").append(c).append(" + ").append(n);
      }
    }
    return pres.toString();
  }
}
