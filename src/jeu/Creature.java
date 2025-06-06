package jeu;

import java.util.HashMap;
import java.util.Map;

import jeu.maitre.Carte;
import jeu.maitre.Donjon;
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


  public void move(Position p) {
    Carte carte = Donjon.getCarte();
    if (!carte.inCarte(p)) { return; }
    if (this.distance(p) > this.caracteristiques.get(Caracteristique.VITESSE)) {
      Output.println("Cette case est trop éloignée.");
      return;
    }
    carte.enleveEntite(p);
    this.position = p;    
    carte.ajouteEntite(this, p);
  }


  public abstract boolean attaque(Creature cible);

  public abstract int getCA();

  public abstract void changePV(int pv);
}
