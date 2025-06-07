package entite;

public enum Caracteristique {
  FORCE,
  DEXTERITE,
  VITESSE,
  INITIATIVE,
  CLASSE_ARMURE,
  PV_MAX,
  PV;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
