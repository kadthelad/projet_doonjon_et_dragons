package jeu;

import jeu.utils.Position;

public abstract class Entite {
  protected Position position;

  public abstract String getNom();

  public String getAlias() {
    return " ? ";
  }

  public Position getPosition() {
    return this.position.get();
  }

  public void entreCarte(Position p) {
    this.position = p;
  }
}
