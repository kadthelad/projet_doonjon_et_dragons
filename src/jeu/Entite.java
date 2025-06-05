package jeu;

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

  public int distance(Entite e) {
    Position p = e.getPosition();
    return Math.abs(p.x - this.position.x + p.y - this.position.y);
  }

}
