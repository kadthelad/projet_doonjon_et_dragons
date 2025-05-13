package jeu.maitre;

import jeu.utils.*;


public class Carte {

    private int taille;
    private Position[] obstacles;

    public Carte(int tailleMap, Position[] obs)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Carte ///
        this.taille = tailleMap;
        this.obstacles = obs;
    }

    public void afficherCarte()
    {
        /// Methode pour afficher la carte ///
    }

}
