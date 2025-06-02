package jeu;

import jeu.utils.Position;

public abstract class Entite {
  protected Position position;

  public Position getPosition() {
    return this.position.get();
  }

  public Position TP(int x, int y) {
    this.position = this.position.TP(x, y);
    return this.position;
  }

  public Position move(int x, int y) {
    this.position = this.position.move(x, y);
    return this.position;
  }

  public int distance(int x, int y) {
    return this.position.distance(x, y);
  }

  public int distance(Position p) {
    return this.position.distance(p);
  }

  public abstract boolean attaque(Entite cible);

  public abstract int getCA();

  public abstract String getNom();

  public abstract void changePV(int pv);

  public abstract String getAlias();
}
