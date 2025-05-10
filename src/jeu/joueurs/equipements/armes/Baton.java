package jeu.joueurs.equipements.armes;

import jeu.utils.Des;

public class Baton implements Arme {

  String m_nom = "bâton";
  String m_des = "1d6";
  int m_portee = 1;

  public int degats() {
    return Des.lancer(1, 6);
  }

  public int portee() {
    return m_portee;
  }

  public String presentation() {
    return m_nom + ", dégats : " + m_des + ", portée : " + m_portee + " case.";
  }
}
