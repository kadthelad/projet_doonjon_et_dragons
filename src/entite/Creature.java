package entite;

import java.util.HashMap;
import java.util.Map;

import utils.Position;

public abstract class Creature extends Entite
{

  protected Map<Caracteristique, Integer> caracteristiques = new HashMap<>();
  {
    caracteristiques.put(Caracteristique.FORCE, 0);
    caracteristiques.put(Caracteristique.DEXTERITE, 0);
    caracteristiques.put(Caracteristique.VITESSE, 0);
    caracteristiques.put(Caracteristique.INITIATIVE, 0);
    caracteristiques.put(Caracteristique.PV, 0);
  }

  public boolean move(Position p) {
    jeu.Carte carte = jeu.Donjon.getCarte();
    if (!carte.inCarte(p))
      return false;
    if (this.distance(p) > this.caracteristiques.get(Caracteristique.VITESSE))
      return false;
    carte.ajouteEntite(this, p); // gère tout (retrait ancien + placement nouveau)
    return true;
  }

  public abstract int getCaracteristique(Caracteristique c);

  public abstract void setCaracteristique(Caracteristique c, int v);

  public abstract boolean attaque(Creature cible);

  public abstract boolean equipperArme();

  public abstract boolean equipperArmure();

  public abstract boolean ramasser(jeu.Carte carte);

  public abstract int getCA();

  public abstract void changePV(int pv);
}
