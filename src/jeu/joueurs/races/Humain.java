package jeu.joueurs.races;

import jeu.Caracteristique;
import java.util.HashMap;

public class Humain extends Race {
  static int valeurBonus = 2;

  public Humain() {
    nom = "Humain.e";
    bonusCaracteristiques = new HashMap<>();
    bonusCaracteristiques.put(Caracteristique.DEXTERITE, valeurBonus);
    bonusCaracteristiques.put(Caracteristique.VITESSE, valeurBonus);
    bonusCaracteristiques.put(Caracteristique.INITIATIVE, valeurBonus);
    bonusCaracteristiques.put(Caracteristique.FORCE, valeurBonus);
    bonusCaracteristiques.put(Caracteristique.PV, valeurBonus);
  }

  public static int getBonus() {
    return valeurBonus;
  }
}
