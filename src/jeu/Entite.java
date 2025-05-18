package jeu;

import jeu.utils.Position;

public abstract class Entite {
  private Position position;  

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
}
