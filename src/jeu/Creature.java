package jeu;

import jeu.utils.Position;

/**
 * Creature
 */
public abstract class Creature extends Entite {
  protected Position position;


  public abstract boolean attaque(Creature cible);

  public abstract int getCA();

  public abstract void changePV(int pv);

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

}
