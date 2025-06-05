package jeu.utils;

public class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int x() {
    return this.x;
  }

  public int y() {
    return this.y;
  }

  public void x(int x) {
    this.x = x;
  }

  public void y(int y) {
    this.y = y;
  }
}
