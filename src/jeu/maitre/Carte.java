package jeu.maitre;

import jeu.Entite;
import jeu.Jeu;
import jeu.carte.CaseVide;
import jeu.joueurs.Personnage;
import jeu.joueurs.equipement.Arme;
import jeu.joueurs.equipement.Catalogue;
import jeu.maitre.Monstre;
import jeu.maitre.Obstacle;
import jeu.utils.*;

import java.util.Random;

public class Carte {

  private Position taille;
  private Entite[][] carte;

  // ====== Constructeurs ======

  public Carte(Position taille) {
    this.taille = taille;
    int y = this.getHauteur();
    int x = this.getLargeur();
    this.carte = new Entite[y][x];
    for (int i = 0; i < y; i++) {
      for (int j = 0; j < x; j++) {
        this.carte[i][j] = new CaseVide();
      }
    }
  }

  public Carte(int largeur, int hauteur) {
    this(new Position(largeur, hauteur));
  }

  public Carte(Carte c) {
    this(new Position(c.getLargeur(), c.getHauteur()));
    this.copieCarte(c);
  }

  // ====== getters ======

  public int getLargeur() {
    return this.taille.x();
  }

  public int getHauteur() {
    return this.taille.y();
  }

  public Position getTaille() {
    return this.taille;
  }

  public Entite[][] getCarte() {
    return this.carte;
  }

  // ====== affichage ======

  public void afficherCarte() {
    Output.clear();
    String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String ligneAbscisse = "  *";
    int l = this.getLargeur();
    int h = this.getHauteur();

    Output.print("   ");
    for (int i = 0; i < l && i < 26; i++) {
      Output.print(" " + (alph.charAt(i)) + " ");
      ligneAbscisse += "---";
    }
    Output.println();
    Output.println(ligneAbscisse + "*");

    for (int y = 0; y < h; y++) {
      if (y < 9)
        Output.print(" " + (y + 1) + "|");
      else
        Output.print((y + 1) + "|");
      String points = "";
      for (int x = 0; x < l; x++) {
        points += carte[y][x].getAlias();
      }
      Output.println(points);
    }
  }

  // ====== Entités ======

  public void ajouteEntite(Entite e, Position p) {
    if (inCarte(p)) {
      carte[p.y()][p.x()] = e;
      e.entreCarte(p);
    }
  }

  public void enleveEntite(Position p) {
    if (inCarte(p)) {
      carte[p.y()][p.x()] = new CaseVide();
    }
  }

  public Entite getEntite(Position p) {
    if (inCarte(p)) {
      return carte[p.y()][p.x()];
    }
    return null;
  }

  public boolean inCarte(Position pos) {
    int y = pos.y();
    int x = pos.x();
    return x >= 0 && x < getLargeur() && y >= 0 && y < getHauteur();
  }

  // ====== Copie ======
  public void copieCarte(Carte c) {
    for (int y = 0; y < getHauteur() && y < c.getHauteur(); y++) {
      for (int x = 0; x < getLargeur() && x < c.getLargeur(); x++) {
        Position p = new Position(y, x);
        Entite e = c.getEntite(p);
        if (e != null) {
          // On fait une nouvelle instance de l'entité si besoin
          if (e instanceof Obstacle)
            this.ajouteEntite(new Obstacle(), p);
          else if (e instanceof CaseVide)
            this.ajouteEntite(new CaseVide(), p);
          else if (e instanceof Monstre)
            this.ajouteEntite(new Monstre((Monstre) e), p);
          else
            this.ajouteEntite(e, p); // fallback (à adapter)
        }
      }
    }
  }

  // ====== Changement de taille ======
  public void changeTaille(Position taille) {
    Carte copie = new Carte(taille);
    copie.copieCarte(this);
    this.carte = copie.carte;
    this.taille = copie.taille;
  }

  // ====== Menu principal de gestion de cartes ======
  public static Carte menuCarte() {
    Carte carte = carteAleatoire();
    boolean menu = true;
    while (menu) {
      Output.println("""
          | =-=-=-=-= | EDITEUR DE CARTE | =-=-=-=-= |
          Voici votre carte :
          """);
      carte.afficherCarte();
      Output.println("""
          [0] Choisir cette carte et quitter l'éditeur
          [1] Créer une carte manuellement
          [2] Générer une autre carte aléatoirement
          """);
      int choix = Input.entier(1, 3, "Choix : ");
      switch (choix) {
        case 1 -> carte = Carte.carteManuelle();
        case 2 -> carte = Carte.carteAleatoire();
        case 3 -> menu = false;
      }
    }
    return carte;
  }

  // ====== Génération aléatoire ======
  public static Carte carteAleatoire() {
    Random rand = new Random();
    int largeur = rand.nextInt(11) + 15; // [15, 25]
    int hauteur = rand.nextInt(11) + 15;
    Carte carte = new Carte(largeur, hauteur);

    // Remplir la carte d'obstacles aléatoires (10% des cases)
    int nbObstacles = (int) (largeur * hauteur * 0.09);
    for (int i = 0; i < nbObstacles; i++) {
      int x = rand.nextInt(largeur);
      int y = rand.nextInt(hauteur);
      carte.ajouteEntite(new Obstacle(), new Position(y, x));
    }
    return carte;
  }

  // ====== Création interactive ======
  public static Carte carteManuelle() {
    Output.println("Création d'une nouvelle carte.");
    int hauteur = Input.entier(15, 25, "Hauteur (Y) = ");
    int largeur = Input.entier(15, 25, "Largeur (X) = ");
    Carte carte = new Carte(hauteur, largeur);

    boolean editing = true;
    while (editing) {
      carte.afficherCarte();

      Output.println("""
          [1] Placer un personnage
          [2] Placer un monstre
          [3] Placer un obstable
          [4] Placer une arme
          [5] Placer une armure
          [6] Vider une case
          [0] Terminer et sauvegarder
          """);
      int choix = Input.entier(0, 6, "Action : ");
      if (choix > 0 && choix < 6) {
        Entite e = choixEntite(choix);
        Output.println("Où que ça va ?");
        Position p = choixCase(carte);
        carte.ajouteEntite(e, p);
        Output.println("Entité ajoutée.");
      } else if (choix == 6) {
        Output.println("Quelle case souhaitez-vous vider ?");
        carte.enleveEntite(choixCase(carte));
        Output.println("Entité retirée.");
      } else {
        editing = false;
      }
    }
    return carte;
  }

  public static Position choixCase(Carte c) {
    int y = Input.entier(1, c.getHauteur(), "Y (ligne) = ") - 1;
    int x = Input.entier(1, c.getLargeur(), "X (colonne) = ") - 1;
    return new Position(y, x);
  }

  private static Entite choixEntite(int choix) {
    Entite e = new CaseVide();

    switch (choix) {
      case 1 -> {
        int n = Jeu.getNombrePersonnages();
        for (int i = 1; i <= n; i++) {
          Output.println("[" + i + "]" + Jeu.getPersonnage(i-1));
        }
        int index = Input.entier(1, n, "Votre choix : ");
        e = Jeu.getPersonnage(index-1);
        e.sortCarte();
      }
      case 2 -> {
        int nombreMonstres = Jeu.getNombreMonstres();
        Output.println("Voici la liste des monstres : ");
        for (int i = 1; i <= nombreMonstres; i++) {
          Output.println("[" + i + "] " + Jeu.getMonstre(i-1).getNom());
        }
        Output.println("[0] Nouveau monstre");
        int n = Input.entier(1, nombreMonstres, "Quel monstre souhaitez-vous placer ?");
        e = (n != nombreMonstres) ? Jeu.getMonstre(n) : Monstre.random();
      }
      case 3 -> e = new Obstacle();
      case 4 -> {
        int n = Catalogue.getNombreArmes();
        Output.println("Voici la liste des armes : ");
        for (int i = 1; i <= n; i++) {
          Output.println("[" + i + "] " + Catalogue.getArmeParIndice(i-1));
        }
        e = Catalogue.getArmeParIndice(Input.entier(1, n, "Votre choix : "));
      }
      case 5 -> {
        int n = Catalogue.getNombreArmure();
        Output.println("Voici la liste des armures : ");
        for (int i = 1; i <= n; i++) {
          Output.println("[" + i + "] " + Catalogue.getArmureParIndice(i-1));
        }
        e = Catalogue.getArmureParIndice(Input.entier(1, n, "Votre choix : "));
      }
    }
    return e;
  }
}
