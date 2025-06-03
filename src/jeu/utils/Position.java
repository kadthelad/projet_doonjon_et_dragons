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

  // IMPLEMENTER LA VERIFICATION POUR CHAQUE MOUVEMENT, besoin de la carte avant
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

  public int distance(int x, int y) {
    return Math.abs((x - this.x) + (y - this.y));
  }

  public int distance(Position p) {
    return this.distance(p.getX(), p.getY());
  }


  public boolean equalsTo(Position pos2) {
    return this.getX() == pos2.getX() && this.getY() == pos2.getY();
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
