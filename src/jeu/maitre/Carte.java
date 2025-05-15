package jeu.maitre;

import jeu.utils.*;


public class Carte {

    private int taille;
    private Position[] obstacles = {};

    public Carte(int tailleMap)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Carte ///
        this.taille = tailleMap;
    }

    public Position[] getObstacles()
    {
        return this.obstacles;
    }

    public void afficherCarte(int taille, Position[] obs)
    {
        String ligneAbscisse = "   *";
        System.out.print("   ");
        // Créer la ligne du haut
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

        // Faire le tableau
        for (int i=0; i<taille; i++) {
            if (i<9)
            {
                System.out.print((i+1) + " |");
            }
            else
            {
                System.out.print((i+1) + "|");
            }
            String points = "";
            for (int y=0; y<taille; y++)
            {
                // Si dans une case il y a un obstacle, changer le point . en [ ]
                boolean isObstacle = false;
                if (obs.length>0)
                {
                    for (Position obsPos : obs) {
                        if (new Position(i, y).equalsTo(obsPos)) {
                            isObstacle = true;
                            break;
                        }
                    }
                }
                if (isObstacle) {
                    if (y>=9)
                    {
                        points += "[ ] ";
                    }
                    else
                    {
                        points += "[ ]";
                    }
                }
                else
                {
                    if (y>=9)
                    {
                        points += " .  ";
                    }
                    else
                    {
                        points += " . ";
                    }
                }
            }
            System.out.println(points);
        }
    }

}
