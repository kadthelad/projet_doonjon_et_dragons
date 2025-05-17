package jeu.joueurs.races;

import java.util.HashMap;

public class Nain extends Race {
    public Nain() {
        nom = "Nain.e";
        bonusCaracteristiques = new HashMap<>();
        bonusCaracteristiques.put("force", 6);
    }
}
