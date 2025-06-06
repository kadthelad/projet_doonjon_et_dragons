package jeu;

import java.util.HashMap;
import java.util.Map;

import jeu.joueurs.Personnage;
import jeu.maitre.Carte;
import jeu.utils.Output;
import jeu.utils.Position;

public abstract class Creature extends Entite {

  protected Map<Caracteristique, Integer> caracteristiques = new HashMap<>();
  {
    caracteristiques.put(Caracteristique.FORCE, 0);
    caracteristiques.put(Caracteristique.DEXTERITE, 0);
    caracteristiques.put(Caracteristique.VITESSE, 0);
    caracteristiques.put(Caracteristique.INITIATIVE, 0);
    caracteristiques.put(Caracteristique.PV, 0);
  }


  public boolean move(Position p) {
    Carte carte = Jeu.getCarteEnCours();
    if (!carte.checkCase(p)) { return false; }
    if (this.distance(p) > this.caracteristiques.get(Caracteristique.VITESSE)) { return false; }
    carte.enleveEntite(p);
    this.position = p;
    carte.ajouteEntite(this, p);
    return true;
  }

  public abstract int getCaracteristique(Caracteristique c);

  public abstract void setCaracteristique(Caracteristique c, int v);

  public abstract boolean attaque(Creature cible);

  public abstract boolean equipperArme();

  public abstract boolean equipperArmure();

  public abstract int getCA();

  public abstract void changePV(int pv);
}
