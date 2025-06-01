package jeu;

public enum Caracteristique {
  FORCE,
  DEXTERITE,
  VITESSE,
  INITIATIVE,
  PV,
  CLASSE_ARMURE;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
