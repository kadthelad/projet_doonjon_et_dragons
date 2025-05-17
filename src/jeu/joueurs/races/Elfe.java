package jeu.joueurs.races;

import jeu.Jeu;
import jeu.joueurs.*;

import java.util.Map;
import java.util.HashMap;

public class Elfe implements Race {
  String m_nom = "Elfe";

  // caracteristiques par defaut
  private Map<String, Integer> bonusCaracteristiques = new HashMap<>() {
    {
      put("dexterite", 6);
    }
  };

  public String getNom() {
    return m_nom;
  }

  public int bonus(String c) {
    return bonusCaracteristiques.getOrDefault(c, 0);

  }

  @Override
  public String toString() {
    String pres = m_nom;
    for (String s : Jeu.getListeCaracteristiques()) {
      int n = bonus(s);
      if (n != 0) {
        pres += "\n\t" + s + " + " + n;
      }
    }
    return pres;
  }

}
