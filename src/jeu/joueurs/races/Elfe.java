package jeu.joueurs.races;

import jeu.Caracteristique;
import java.util.HashMap;

public class Elfe extends Race {
  public Elfe() {
    nom = "Elfe";
    bonusCaracteristiques = new HashMap<>();
    bonusCaracteristiques.put(Caracteristique.DEXTERITE, 6);
  }
}
