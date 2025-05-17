package jeu;

import jeu.Caracteristique;

public enum Caracteristique {
  FORCE,
  DEXTERITE,
  VITESSE,
  INITIATIVE,
  PV;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
