package jeu.maitre;

import jeu.Entite;
import jeu.carte.CaseVide;
import jeu.utils.*;

public class Carte {

  private String name = "Carte sans nom";
  private int largeur;
  private int hauteur;

  Entite carte[][];

  public Carte(int x, int y) {
    this.largeur = x;
    this.hauteur = y;
    this.carte = new Entite[this.hauteur][this.largeur];
    for (int i = 0; i < y; i++) {
      for (int j = 0; j < x; j++) {
        this.carte[i][j] = new CaseVide();
      }
    }
  }

  public Carte(Position taille) {
    this.largeur = taille.x();
    this.hauteur = taille.y();
    this.carte = new Entite[this.hauteur][this.largeur];
  }

  public Carte(Carte c) {
    this.copieCarte(c);
  }

  // on retourne le tableau d'entité à 2 dimensions symbolisant les cases de la
  // carte
  public Entite[][] getCarte() {
    return this.carte;
  }

  public int getLargeur() {
    return largeur;
  }

  public int getHauteur() {
    return hauteur;
  }

  public void copieCarte(Carte c) {
    this.name = c.getName() + " - copie";
    for (int y = 0; y < this.hauteur && y < c.getHauteur(); y++) {
      for (int x = 0; x < this.largeur && y < c.getLargeur(); x++) {
        this.carte[y][x] = c.getCarte()[y][x];
      }
    }
    this.largeur = c.getLargeur();
    this.hauteur = c.getHauteur();
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
      Output.print("Cette case n'est pas disponible, " + this.getEntite(p).getAlias());
    } else {
      check = true;
    }
    return check;
  }

  public boolean inCarte(Position pos) {
    int x = pos.x();
    int y = pos.y();
    int a = this.largeur;
    int h = this.hauteur;

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

  public String getName() {
    return this.name;
  }

  public void setName(String setName) {
    this.name = setName;
  }

  public Position getTaille() {
    return new Position(this.largeur, this.hauteur);
  }

  // A TERMINER
  public void changeTaille(Position taille) {
    Carte nouvelleCarte = new Carte(taille);
    nouvelleCarte.copieCarte(this);
    this.carte = nouvelleCarte.getCarte();
  }

  public void afficherCarte() {
    String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String ligneAbscisse = "  *";

    Output.print("   ");
    // Créer la ligne du haut
    for (int i = 0; i < largeur && i < 26; i++) {
      Output.print(" " + (alph.charAt(i)) + " ");
      ligneAbscisse += "---";
    }
    Output.print("\n");
    Output.println(ligneAbscisse + "*");
    for (int y = 0; y < this.hauteur; y++) {
      if (y < 9) {
        Output.print((y + 1) + "| ");
      } else {
        Output.print((y + 1) + "|");
      }
      String points = "";
      for (int x = 0; x < this.largeur; x++) {
        points += this.carte[y][x].getAlias();
      }
      Output.println(points);
    }
  }
}
