package jeu.maitre;

import jeu.utils.*;

import java.util.ArrayList;


public class Carte {

    private String name = "Carte sans nom";
    private int taille;
    private ArrayList<Position> obstacles = new ArrayList<Position>() {};

    public Carte(int tailleMap)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Carte ///
        this.taille = tailleMap;
    }

    public ArrayList<Position> getObstacles()
    {
        return this.obstacles;
    }

    public void addObstacle(Position pos)
    {
        this.obstacles.add(pos);
    }

    public void removeObstacle(Position pos)
    {
        for (int i=0; i<this.obstacles.size(); i++)
        {
            if (pos.equalsTo(this.obstacles.get(i)))
            {
                this.obstacles.remove(i);
            }
        }
    }

    public boolean obstacleAt(Position pos)
    {
        for (Position obs : obstacles)
        {
            if (obs.equalsTo(pos))
            {
                return true;
            }
        }
        return false;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String setName)
    {
        this.name = setName;
    }

    public int getTaille()
    {
        return this.taille;
    }

    public void afficherCarte()
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
                if (!this.obstacles.isEmpty())
                {
                    for (Position obsPos : this.obstacles) {
                        if (new Position(i+1, y+1).equalsTo(obsPos)) {
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
