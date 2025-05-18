package jeu.utils;

public class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    /// Constructeur de classe ///
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public Position TP(int x, int y) {
    this.x = x;
    this.y = y;
    return this;
  }

  public Position move(int x, int y) {
    this.x += x;
    this.y += y;
    return this;
  } 

  public Position get() {
    return this;
  }


  public boolean equalsTo(Position pos2) {
    return this.getX() == pos2.getX() && this.getY() == pos2.getY();
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
