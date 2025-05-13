package jeu.joueurs.races;

import jeu.Jeu;
import jeu.joueurs.*;

public class Nain implements Race {
  String m_nom = "Nain";

  public String presentation() {
    String pres = m_nom + ": ";
    for (String s : Jeu.getListeCaracteristiques()) {
      int n = bonus(s);
      if (n != 0) {
        pres += s + " + " + n;
      }
    }
    return pres;
  }

  public int bonus(String c) {
    if (c == "force") {
      return 6;
    } else {
      return 0;
    }
  }

  public String getNom() {
    return m_nom;
  }

}
