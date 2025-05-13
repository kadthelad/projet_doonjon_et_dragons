package jeu.joueurs.races;

import jeu.joueurs.*;

/**
 * Race
 */
public interface Race {

  public String presentation();

  public int bonus(String c);

  public String getNom();
}
