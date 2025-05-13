package jeu.maitre;

import jeu.joueurs.equipements.Equipement;
import jeu.joueurs.Personnage;
import jeu.utils.*;

import java.util.Scanner;

public class Donjon {
    // La classe Donjon va gérer le jeu en général
    // Elle passera les tours et calculera les opérations dans le tour (combat, déplacement, autres actions...)
    private static Scanner sc = new Scanner(System.in);


    private Carte carte;
    private Position[] obstacles;
    private Personnage[] personnages;
    private Equipement[] equipements;

    private int tour = 0;

    public Donjon()
    {

    }

    public Donjon(Carte carteJeu, Position[] lstObstacles, Personnage[] lstPersonnages, Equipement[] lstEquipements)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Donjon ///
        this.carte = carteJeu;
        this.obstacles = lstObstacles;
        this.personnages = lstPersonnages;
        this.equipements = lstEquipements;
    }

    public Donjon creerJeu()
    {
        ///  Méthode pour créer le jeu ///
        // Lors de l'appel de la méthode, le maitre du jeu devra créer la carte, l'emplacement des obstacles, des
        // personnages et des equipements
        System.out.println("Au tour du Maitre du Jeu!");

        // Création de la carte //
        System.out.println("Quelle sera la taille de la carte?");
        int t = sc.nextInt();
        afficherCarteAvecCurseur(t, new Position(0, 0));

        //Carte creationCarte = new Carte(t, obs);

        return null; // pour enlever l'erreur, je changerai plus tard
    }

    public void afficherCarteAvecCurseur(int taille, Position curseurPos)
    {
        // Afficher l'axe des abscisses
        String ligneAbscisse = "   *";
        System.out.print("   ");
        for (int i=0; i<taille; i++) {
            System.out.print(" " + (i + 1) + " ");
            if (i>9)
            {
                ligneAbscisse += "----";
            }
            else
            {
                ligneAbscisse += "---";
            }
        }
        System.out.print("\n");
        System.out.println(ligneAbscisse+"*");

        // Afficher les lignes
        String points = "";
        for (int i=0; i<taille; i++) {
            if (i>=9)
            {
                points += " .  ";
            }
            else
            {
                points += " . ";
            }
        }

        for (int i=0; i<taille; i++) {
            if (i<9)
            {
                System.out.println((i+1) + " |" + points);
            }
            else
            {
                System.out.println((i+1) + "|" + points);
            }
        }
    }

    public void initialiserCarte(Carte carteJeu)
    {
        /// Methode pour initialiser le jeu ///
    }

    public void jouerTour()
    {
        /// Methode pour faire jouer un tour ///
        // Faire jouer le tour

        // Ajouter 1 au tour
        tour++;
    }

    public void quitterJeu()
    {
        /// Methode pour finir le jeu, ou à appeler lorsque le joueur souhaite quitter ///
    }
}
