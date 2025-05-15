package jeu.joueurs.races;

import jeu.Jeu;
import jeu.joueurs.*;

import java.util.Map;
import java.util.HashMap;

public class Humain implements Race {
  String m_nom = "Humain";
  static int valeurBonus = 2;

  // caracteristiques par defaut
  private Map<String, Integer> bonusCaracteristiques = new HashMap<>() {
    {
      put("dexterite", valeurBonus);
      put("vitesse", valeurBonus);
      put("initiative", valeurBonus);
      put("force", valeurBonus);
    }
  };

  public String getNom() {
    return m_nom;
  }

  public int bonus(String c) {
    return bonusCaracteristiques.getOrDefault(c, 0);
  }

  static public int getBonus() {
    return valeurBonus;
  }

  @Override
  public String toString() {
    String pres = m_nom + ": ";
    for (String s : Jeu.getListeCaracteristiques()) {
      int n = bonus(s);
      if (n != 0) {
        pres += "\n    " + s + " + " + n;
      }
    }
    return pres;
  }

}
