package jeu;

import jeu.maitre.Carte;
import jeu.maitre.Donjon;
import jeu.utils.Position;

public abstract class Entite {
  protected Position position = new Position(-1, -1);

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

  public void sortCarte() {
    Donjon.getCarte().enleveEntite(this.position);
    this.position = new Position(-1, -1);
  }

  public void sortCarte(Carte carte) {
    if (carte != null && carte.inCarte(this.position) && !this.position.equals(new Position(-1, -1))) {
      carte.enleveEntite(this.position);
      this.position = new Position(-1, -1);
    }
  }

  public void TP(Position p) {
    Carte carte = Donjon.getCarte();
    if (carte.inCarte(p)) {
      carte.enleveEntite(p);
      this.position = p;
      carte.ajouteEntite(this, p);
    }
  }

  public void TP(Carte carte, Position p) {
    if (carte.inCarte(p)) {
      carte.enleveEntite(this.position);
      this.sortCarte(carte);
      this.position = p;
      carte.ajouteEntite(this, this.position);
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
