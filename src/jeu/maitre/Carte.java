package jeu.maitre;

import jeu.Entite;
import jeu.utils.*;

import java.security.PublicKey;
import java.util.ArrayList;


public class Carte {

    private String name = "Carte sans nom";
    private int taillex;
    private int tailley;
    private ArrayList<ArrayList<Tile>> map = new ArrayList<>();
    private ArrayList<Position> obstacles = new ArrayList<Position>() {};

    public Carte(int tailleMapx, int tailleMapy)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Carte ///
        this.taillex = tailleMapx;
        this.tailley = tailleMapy;

        // construction map
        for (int y=0; y<tailley; y++)
        {
            this.map.add(new ArrayList<>());
            for (int x=0; x<taillex; x++)
            {
                this.map.get(y).add(x, new Tile(new Position(x, y)));
            }
        }
    }

    // pour copier la carte (utilisé dans CarteEditeur.editerCarte())
    public Carte(Carte autreCarte)
    {
        this.taillex = autreCarte.getTaille().getX();
        this.tailley = autreCarte.getTaille().getY();
        this.name = autreCarte.getName();
        this.obstacles.addAll(autreCarte.getObstacles());
    }

    public ArrayList<Position> getObstacles()
    {
        return this.obstacles;
    }

    public void addObstacle(Position pos)
    {
        this.map.get(pos.getY()-1).get(pos.getX()-1).setChose("[x]");
        this.obstacles.add(pos);
    }

    public void removeObstacle(Position pos)
    {
        for (int i=0; i<this.obstacles.size(); i++)
        {
            if (pos.equalsTo(this.obstacles.get(i)))
            {
                this.map.get(pos.getY()-1).get(pos.getX()-1).setChose(" . ");
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

    public Position getTaille()
    {
        return new Position(this.taillex, this.tailley);
    }

    public void afficherCarte()
    {
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String ligneAbscisse = "   *";
        System.out.print("   ");
        // Créer la ligne du haut
        for (int i=0; i<taillex; i++) {
            System.out.print(" " + (alph.charAt(i)) + " ");
            ligneAbscisse += "---";
        }
        System.out.print("\n");
        System.out.println(ligneAbscisse+"*");

        // Faire le tableau
        for (int y=0; y<tailley; y++) {
            if (y<9)
            {
                System.out.print((y+1) + " |");
            }
            else
            {
                System.out.print((y+1) + "|");
            }
            String points = "";
            for (int x=0; x<taillex; x++)
            {
                points += this.map.get(y).get(x).getChose();
            }
            System.out.println(points);
        }
    }

    public void changeSize(int x, int y)
    {
        this.taillex = x;
        this.tailley = y;

        if (!this.obstacles.isEmpty())
        {
            ArrayList<Position> posToDelete = new ArrayList<>() {};
            for (Position obsPos : this.obstacles)
            {
                // Supprimer tous les tiles out of bounds
                if (obsPos.getX() > x || obsPos.getY() > y)
                {
                    this.map.get(obsPos.getY()).remove(obsPos.getX());
                }
            }
            for (Position pos : posToDelete)
            {
                this.obstacles.remove(pos);
            }
        }
    }
}
