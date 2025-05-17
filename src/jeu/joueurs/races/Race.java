package jeu.joueurs.races;

import java.util.Map;

public abstract class Race {
    protected String nom;
    protected Map<String, Integer> bonusCaracteristiques;

    public String getNom() {
        return nom;
    }

    public int bonus(String c) {
        return bonusCaracteristiques.getOrDefault(c, 0);
    }

    @Override
    public String toString() {
        StringBuilder pres = new StringBuilder(nom);
        for (String s : jeu.Jeu.getListeCaracteristiques()) {
            int n = bonus(s);
            if (n != 0) {
                pres.append("\n\t").append(s).append(" + ").append(n);
            }
        }
        return pres.toString();
    }
}
