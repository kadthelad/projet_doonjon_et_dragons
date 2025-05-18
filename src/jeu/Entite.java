package jeu;

import jeu.utils.Position;

public abstract class Entite {
  private Position position;  

  public Position getPosition() {
    return this.position;
  }

  public Position TP(int x, int y) {
    this.position = new Position(x, y);
    return this.position;
  }

  public Position move(int movex, int movey) {
    int x = this.position.getX();
    int y = this.position.getY();
    this.position = new Position(x + movex, y + movey);
    return this.position;
  }
}
