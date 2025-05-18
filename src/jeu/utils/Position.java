package jeu.utils;

public class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    /// Constructeur de classe ///
    this.x = x;
    this.y = y;
  }

  public Position() {
    this.x = -1;
    this.y = -1;
  } 

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public boolean equalsTo(Position pos2) {
    return this.getX() == pos2.getX() && this.getY() == pos2.getY();
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
