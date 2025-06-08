package jeu;

import entite.Entite;
import entite.CaseVide;
import personnages.equipement.Catalogue;
import entite.Monstre;
import entite.Obstacle;
import utils.*;

import java.util.ArrayList;
import java.util.Random;

public class Carte {

  private Position taille;
  private Entite[][] carte;
  private static Random rand = new Random();
  private ArrayList<Monstre> listeMonstresEnJeu = new ArrayList<>();

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

  public ArrayList<Monstre> getListeMonstresEnJeu() {
    return listeMonstresEnJeu;
  }

  // ====== affichage ======

  public void afficherCarte() {
    Output.clear();
    String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String ligneAbscisse = "   *";
    int l = this.getLargeur();
    int h = this.getHauteur();

    Output.print("    ");
    for (int i = 0; i < l; i++) {
      Output.print(" " + (alph.charAt(i)) + " ");
      ligneAbscisse += "---";
    }

    Output.println();
    Output.println(ligneAbscisse + "*");

    for (int y = 0; y < h; y++) {
      if (y < 9)
        Output.print(" " + (y + 1) + " |");
      else
        Output.print((y + 1) + " |");
      String points = "";
      for (int x = 0; x < l; x++) {
        points += carte[y][x].getAlias();
      }
      Output.println(points);
    }
    afficherLegende();
  }

  public void afficherLegende() {
    Output.println("""

        Légende :
        +-----------------------------------+
        | orX / oXX = orc numéro X          |
        | drX / dXX = dragon numéro X       |
        | arX / aXX = arraignée numéro X    |
        |     ?     = équipement            |
        |    [x]    = obstacle              |
        +-----------------------------------+
        """);
  }

  // ====== Entités ======
  public void ajouteEntite(Entite e, Position p) {
    if (inCarte(p)) {
      if (inCarte(e.getPosition())) {
        enleveEntite(e.getPosition());
      }
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
        this.ajouteEntite(e, p);
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
  public static void menuCarte() {
    Carte carte = carteAleatoire();
    boolean menu = true;
    while (menu) {
      Output.println("""
          | =-=-=-=-= | EDITEUR DE CARTE | =-=-=-=-= |
          Voici votre carte :
          """);
      carte.afficherCarte();
      Output.println("""

          ---=-=-= | ACTION | =-=-=---
          [0] Choisir cette carte et quitter l'éditeur
          [1] Créer une carte manuellement
          [2] Générer une autre carte aléatoirement
          """);
      int choix = Input.entier(0, 2, "Choix : ");
      switch (choix) {
        case 1 -> carte = Carte.carteManuelle();
        case 2 -> carte = Carte.carteAleatoire();
        case 0 -> menu = false;
      }
      Donjon.setCarte(carte);
    }
  }

  // ====== Génération aléatoire ======
  public static Carte carteAleatoire() {
    Carte carte = new Carte(positionAleatoire(15, 25));
    int n = 0;

    // on rajoute des obstacles aléatoirement (~0-8% des cases)
    int nbObstacles = (int) (carte.getTaille().getSuperficie() * 0.02);
    n = 0;
    while (n < nbObstacles) {
      Position p = positionAleatoire(carte.getTaille());
      if (carte.getEntite(p).isEmpty()) {
        carte.ajouteEntite(new Obstacle(), p);
        n++;
      }
    }

    // on rajoute des monstres aléatoirement (~3-10% des cases)
    Monstre.resetMonstres();
    int nombreMonstres = (int) (carte.getTaille().getSuperficie() * 0.02 * (Jeu.getNombrePersonnages() * .95));
    n = 0;
    while (n < nombreMonstres) {
      Position p = positionAleatoire(carte.getTaille());
      if (carte.getEntite(p).isEmpty()) {
        Monstre m = Monstre.random();
        carte.ajouteEntite(m, p);
        carte.getListeMonstresEnJeu().add(m);
        n++;
      }
    }

    // on rajoute des équipements aléatoirement (~1% des cases)
    int nombreEquipement = (int) (carte.getTaille().getSuperficie() * 0.02);
    n = 0;
    while (n < nombreEquipement) {
      Position p = positionAleatoire(carte.getTaille());
      if (carte.getEntite(p).isEmpty()) {
        carte.ajouteEntite(
            (rand.nextInt(1) == 0 ? Catalogue.getArmureParIndice(rand.nextInt(Catalogue.getNombreArmure()))
                : Catalogue.getArmeParIndice(rand.nextInt(Catalogue.getNombreArmes()))),
            p);
        n++;
      }
    }

    // on ajoute les personnages
    n = 0;
    while (n < Jeu.getNombrePersonnages()) {
      Position p = positionAleatoire(carte.getTaille());
      if (carte.getEntite(p).isEmpty()) {
        carte.ajouteEntite(Jeu.getPersonnage(n), p);
        n++;
      }
    }

    return carte;
  }

  private static Position positionAleatoire(int min, int max) {
    return new Position(
        rand.nextInt(max - min + 1) + min,
        rand.nextInt(max - min + 1) + min);
  }

  private static Position positionAleatoire(Position p) {
    return new Position(
        rand.nextInt(p.y()),
        rand.nextInt(p.x()));
  }

  // ====== Création interactive ======
  public static Carte carteManuelle() {
    Monstre.resetMonstres();
    for (int i = 0; i < Jeu.getNombreMonstres(); i++) {
      Jeu.getMonstre(i).setNumero(0);
    }
    Output.println("Création d'une nouvelle carte.");
    int hauteur = Input.entier(15, 25, "Hauteur (Y) = ");
    // int largeur = Input.entier(15, 25, "Largeur (X) = ");
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
        Entite e = choixEntite(carte, choix);
        carte.afficherCarte();
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
    int x = Input.entierChar(c.getLargeur(), "X (colonne) = ");
    return new Position(y, x);
  }

  private static Entite choixEntite(Carte carte, int choix) {
    Entite e = new CaseVide();

    switch (choix) {
      case 1 -> {
        int n = Jeu.getNombrePersonnages();
        for (int i = 1; i <= n; i++) {
          Output.println("[" + i + "]" + Jeu.getPersonnage(i - 1).getAlias());
        }
        int index = Input.entier(1, n, "Votre choix : ");
        e = Jeu.getPersonnage(index - 1);
        e.sortCarte(carte);
      }
      case 2 -> {
        int nombreMonstres = Jeu.getNombreMonstres();
        Output.println("Voici la liste des monstres : ");
        for (int i = 1; i <= nombreMonstres; i++) {
          Output.println("[" + i + "] " + Jeu.getMonstre(i - 1).getNom());
        }
        Output.println("[0] Nouveau monstre");
        int n = Input.entier(0, nombreMonstres, "Quel monstre souhaitez-vous placer ?");
        e = (n != 0) ? Jeu.getMonstre(n - 1).copie() : Monstre.create();
      }
      case 3 -> e = new Obstacle();
      case 4 -> {
        int n = Catalogue.getNombreArmes();
        Output.println("Voici la liste des armes : ");
        for (int i = 1; i <= n; i++) {
          Output.println("[" + i + "] " + Catalogue.getArmeParIndice(i - 1));
        }
        e = Catalogue.getArmeParIndice(Input.entier(1, n, "Votre choix : "));
      }
      case 5 -> {
        int n = Catalogue.getNombreArmure();
        Output.println("Voici la liste des armures : ");
        for (int i = 1; i <= n; i++) {
          Output.println("[" + i + "] " + Catalogue.getArmureParIndice(i - 1));
        }
        e = Catalogue.getArmureParIndice(Input.entier(1, n, "Votre choix : "));
      }
    }
    return e;
  }
}
