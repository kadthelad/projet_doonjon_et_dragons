package jeu;

import jeu.maitre.Carte;
import jeu.maitre.Donjon;
import jeu.utils.Position;

public abstract class Entite {
  protected Position position;

  public abstract String getNom();

  public String getAlias() {
    return " ? ";
  }

  public Position getPosition() {
    return this.position;
  }

  public void entreCarte(Position p) {
    this.position = p;
  }

  public void sortCarte(Position p) {
    this.position = null;
  }

  public void TP(Position p) {
    Carte carte = Donjon.getCarte();
    if (carte.inCarte(p)) {
      carte.enleveEntite(p);
      this.position = p;    
      carte.ajouteEntite(this, p);
    }
  }

  public int distance(Entite e) {
    return distance(e.getPosition());
  }

  public int distance(Position p) {
    return Math.abs(p.x() - this.position.x() + p.y() - this.position.y());
  }

  public boolean isEmpty() {
    return false;
  }

}
