package jeu.maitre;

import jeu.Entite;
import jeu.utils.*;

import java.security.PublicKey;
import java.util.ArrayList;


public class Carte {

    private String name = "Carte sans nom";
    private int taillex;
    private int tailley;
    private ArrayList<ArrayList<Entite>> map = new ArrayList<>();

    public Carte(int tailleMapx, int tailleMapy)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Carte ///
        this.taillex = tailleMapx;
        this.tailley = tailleMapy;

        // construction map
        for (int y=0; y<this.tailley; y++)
        {
            this.map.add(new ArrayList<>());
            for (int x=0; x<this.taillex; x++)
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
        this.map = autreCarte.getMap();
    }


    public boolean addChose(Position pos, Entite entity)
    {
        if (this.map.get(pos.getY()-1).get(pos.getX()-1).getAlias() == " . ")
        {
            this.map.get(pos.getY()-1).set(pos.getX()-1, entity);
            return true;
        }
        return false;
    }

    public boolean removeChose(Position pos)
    {
        if (this.map.get(pos.getY()-1).get(pos.getX()-1).getAlias() != " . ")
        {
            this.map.get(pos.getY()-1).set(pos.getX()-1, new Tile(pos));
            return true;
        }
        return false;
    }

    public Entite choseAt(Position pos)
    {
        if (this.taillex > pos.getX() && this.tailley > pos.getY())
        {
            return this.map.get(pos.getY()).get(pos.getX());
        }
        return new Tile(pos);
    }

    public boolean hasObstacles()
    {
        for (int y=0; y<this.tailley; y++)
        {
            for (int x=0; x<this.taillex; x++)
            {
                if (choseAt(new Position(x, y)).getAlias().equals("[x]"))
                {
                    return true;
                }
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

    public ArrayList<ArrayList<Entite>> getMap()
    {
        return this.map;
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
                points += this.map.get(y).get(x).getAlias();
            }
            System.out.println(points);
        }
    }

    public void changeSize(int tailleMapx, int tailleMapy)
    {
        ArrayList<ArrayList<Entite>> newMap = new ArrayList<>();

        for (int y=0; y<tailleMapy; y++)
        {
            newMap.add(new ArrayList<>());
            for (int x=0; x<tailleMapx; x++)
            {
                newMap.get(y).add(x, choseAt(new Position(x, y)));
            }
        }

        this.taillex = tailleMapx;
        this.tailley = tailleMapy;
        this.map = newMap;
    }
}
