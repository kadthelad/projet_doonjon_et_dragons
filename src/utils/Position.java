package utils;

public class Position {
  private int x;
  private int y;

  public Position(int y, int x) {
    this.y = y;
    this.x = x;
  }

  public Position() {
    this.y = -1;
    this.x = -1;
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

  public int getSuperficie() {
    return this.y * this.x;
  }

  @Override
  public String toString()
  {
    return "X = " + this.x + " Y = " + this.y;
  }
}
