package jeu.joueurs.races;

import java.util.HashMap;

public class Elfe extends Race {
    public Elfe() {
        nom = "Elfe";
        bonusCaracteristiques = new HashMap<>();
        bonusCaracteristiques.put("dexterite", 6);
    }
}
