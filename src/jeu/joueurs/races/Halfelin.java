package jeu.joueurs.races;

import java.util.HashMap;

public class Halfelin extends Race {
  public Halfelin() {
    nom = "Halfelin";
    bonusCaracteristiques = new HashMap<>();
    bonusCaracteristiques.put("dexterite", 4);
    bonusCaracteristiques.put("vitesse", 2);
  }
}
