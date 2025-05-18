package jeu;

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
