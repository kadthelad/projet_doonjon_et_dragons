package jeu;

import jeu.utils.Position;

public abstract class Creature extends Entite {
  protected Position position;


  public abstract boolean attaque(Creature cible);

  public abstract int getCA();

  public abstract void changePV(int pv);

  public Position getPosition() {
    return this.position.get();
  }

  public Position TP(Position p) {
    this.position = this.position.TP(p);
    return this.position;
  }

  public Position move(Position p) {
    this.position = this.position.move(p);
    return this.position;
  }

  public int distance(Position p) {
    return this.position.distance(p);
  }

}
