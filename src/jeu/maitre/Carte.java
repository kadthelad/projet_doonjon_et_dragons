package jeu.maitre;

import jeu.Entite;
import jeu.utils.*;

import java.security.PublicKey;
import java.util.ArrayList;

public class Carte {

  private ArrayList<ArrayList<Entite>> map = new ArrayList<>();
  private String name = "Carte sans nom";
  private int largeur;
  private int hauteur;

  Entite carte[][];

  public Carte(int x, int y) {
    /// Constructeur de la Carte ///
    this.largeur = x;
    this.hauteur = y;
    this.carte = new Entite[y][x];
  }

  public Entite[][] getCarte() {
    return this.carte;
  }

  public int getLargeur() {
    return largeur;
  }

  public int getHauteur() {
    return hauteur;
  }

  // pour copier la carte (utilisé dans CarteEditeur.editerCarte())
  public void copieCarte(Carte c) {
    this.largeur = c.getLargeur();
    this.hauteur = c.getHauteur();
    this.name = c.getName() + " - copie";
    for (int y = 0; y < c.getHauteur(); y++) {
      for (int x = 0; x < c.getLargeur(); x++) {
        this.carte[y][x] = c.getCarte()[y][x];
      }
    }
  }

  public void ajouteEntite(Entite e, Position p) {
    if (checkCase(p)) {
      this.carte[p.y][p.x] = e;
      e.entreCarte(p);
    }
    return;
  }

  public boolean checkCase(Position p) {
    boolean check = true;
    if (!inCarte(p)) {
      check = false;
    } else if (this.carte[p.y][p.x] != null) {
      check = false;
      Output.print("Cette case n'est pas disponible, " + this.carte[p.y][p.x].getAlias());
    } else {
      check = true;
    }
    return check;
  }

  public boolean inCarte(Position pos) {
    int x = pos.x;
    int y = pos.y;
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
    this.carte[pos.y][pos.x] = null;
  }

  public Entite getEntite(Position pos) {
    return this.carte[pos.y][pos.x];
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
    Entite[][] nouvelleCarte = new Entite[taille.y][taille.x];
    nouvelle.copieCarte(c);
  }

  public void afficherCarte() {
    String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    String ligneAbscisse = "   *";
    System.out.print("   ");
    // Créer la ligne du haut
    for (int i = 0; i < largeur; i++) {
      System.out.print(" " + (alph.charAt(i)) + " ");
      ligneAbscisse += "---";
    }
    System.out.print("\n");
    System.out.println(ligneAbscisse + "*");

    // Faire le tableau
    for (int y = 0; y < hauteur; y++) {
      if (y < 9) {
        System.out.print((y + 1) + " |");
      } else {
        System.out.print((y + 1) + "|");
      }
      String points = "";
      for (int x = 0; x < largeur; x++) {
        points += this.map.get(y).get(x).getAlias();
      }
      System.out.println(points);
    }
  }

  public void changeSize(int tailleMapx, int tailleMapy) {
    ArrayList<ArrayList<Entite>> newMap = new ArrayList<>();

    for (int y = 0; y < tailleMapy; y++) {
      newMap.add(new ArrayList<>());
      for (int x = 0; x < tailleMapx; x++) {
        newMap.get(y).add(x, choseAt(new Position(x, y)));
      }
    }

    this.largeur = tailleMapx;
    this.hauteur = tailleMapy;
    this.map = newMap;
  }
}
