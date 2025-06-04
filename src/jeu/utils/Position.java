package jeu.utils;
import jeu.Jeu;
import jeu.maitre.Carte;
import jeu.maitre.Donjon;

public class Position {
  private int x;
  private int y;
  Carte carteActuelle = Jeu.getListeCartes().get(Donjon.getIndexCarteActuelle());

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

  public Position TP(Position p) {
    if (this.checkCase(p)) {
      this.x = p.getX();
      this.y = p.getY();
    } else {
      Output.print("Malheuresement cette case n'est pas accessible");
    }
    return this;
  }

  public Position move(Position p) {
    if (this.checkCase(p)) {
    this.x += p.getX();
    this.y += p.getY();
    } else {
      Output.print("Malheuresement cette case n'est pas accessible");
    }
    return this;
  } 

  public Position get() {
    return this;
  }

  public int distance(Position p) {
    return Math.abs((p.getX() - this.x) + (p.getY() - this.y));
  }

  public boolean equalsTo(Position pos2) {
    return this.getX() == pos2.getX() && this.getY() == pos2.getY();
  }

  public boolean checkCase(Position p) {
    return carteActuelle.choseAt(p) == null;
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
