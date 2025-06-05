package jeu.maitre;

import jeu.Entite;
import jeu.carte.CaseVide;
import jeu.utils.*;

public class Carte {

  private String name = "Carte sans nom";
  private Position taille;

  Entite carte[][];

  public Carte(Position nouvelleTaille) {
    this.taille = nouvelleTaille;
    int y = this.getHauteur();
    int x = this.getLargeur();
    this.carte = new Entite[y][x];

    for (int i = 0; i < y; i++) {
      for (int j = 0; j < x; j++) {
        this.carte[i][j] = new CaseVide();
      }
    }
  }

  public Carte(int y, int x) {
    this(new Position(y, x));
  }

  public Carte(Carte c) {
    this(c.getTaille());
    this.copieCarte(c);
    this.name = c.getName() + " - copie";
  }

  public int getLargeur() {
    return this.taille.x();
  }

  public int getHauteur() {
    return this.taille.y();
  }

  public Position getTaille() {
    return this.taille;
  }

  public void copieCarte(Carte c) {
    for (int y = 0; y < this.getHauteur() && y < c.getHauteur(); y++) {
      for (int x = 0; x < this.getLargeur() && x < c.getLargeur(); x++) {
        Position p = new Position(y, x);
        this.ajouteEntite(c.getEntite(p), p);
      }
    }
  }

  public void ajouteEntite(Entite e, Position p) {
    if (checkCase(p)) {
      this.carte[p.y()][p.x()] = e;
      e.entreCarte(p);
    }
    return;
  }

  public boolean checkCase(Position p) {
    boolean check = true;
    if (!inCarte(p)) {
      check = false;
    } else if (this.getEntite(p).isEmpty()) {
      check = false;
      Output.println("Cette case n'est pas disponible, " + this.getEntite(p).getAlias());
    } else {
      check = true;
    }
    return check;
  }

  public boolean inCarte(Position pos) {
    int y = pos.y();
    int x = pos.x();
    int h = this.getHauteur();
    int a = this.getLargeur();

    // on vérifie si la position {x,y} existe dans la carte
    if (x >= 0 && x < a && y >= 0 && y < h) {
      return true;
    } else {
      Output.print("Cette case n'existe pas dans la carte.");
      return false;
    }
  }

  public void enleveEntite(Position pos) {
    this.ajouteEntite(new CaseVide(), pos);
  }

  public Entite getEntite(Position pos) {
    return this.carte[pos.y()][pos.x()];
  }

  public Entite[][] getCarte() {
    return this.carte;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String setName) {
    this.name = setName;
  }

  // A TERMINER
  public void changeTaille(Position taille) {
    Carte copie = new Carte(taille);
    copie.copieCarte(this);
    this.carte = copie.getCarte();
    this.name = copie.getName();
    this.taille = copie.getTaille();
  }

  public void afficherCarte() {
    String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String ligneAbscisse = "  *";
    int l = this.getLargeur();
    int h = this.getHauteur();

    Output.print("   ");
    // Créer la ligne du haut
    for (int i = 0; i < l && i < 26; i++) {
      Output.print(" " + (alph.charAt(i)) + " ");
      ligneAbscisse += "---";
    }
    Output.print("\n");
    Output.println(ligneAbscisse + "*");

    for (int y = 0; y < h; y++) {
      if (y < 9) {
        Output.print(" " + (y + 1) + "|");
      } else {
        Output.print((y + 1) + "|");
      }
      String points = "";
      for (int x = 0; x < l; x++) {
        Position p = new Position(y, x);
        points += this.getEntite(p).getAlias();
      }
      Output.println(points);
    }
  }
}
