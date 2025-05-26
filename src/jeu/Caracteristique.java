package jeu;

public enum Caracteristique {
  FORCE,
  DEXTERITE,
  VITESSE,
  INITIATIVE,
  PV_MAX,
  PV;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
