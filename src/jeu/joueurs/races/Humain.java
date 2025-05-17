package jeu.joueurs.races;

import java.util.HashMap;

public class Humain extends Race {
    static int valeurBonus = 2;

    public Humain() {
        nom = "Humain.e";
        bonusCaracteristiques = new HashMap<>();
        bonusCaracteristiques.put("dexterite", valeurBonus);
        bonusCaracteristiques.put("vitesse", valeurBonus);
        bonusCaracteristiques.put("initiative", valeurBonus);
        bonusCaracteristiques.put("force", valeurBonus);
        bonusCaracteristiques.put("pv", valeurBonus);
    }

    public static int getBonus() {
        return valeurBonus;
    }
}
