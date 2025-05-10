package maitre;

import mapLogic.Vector;

import java.util.Scanner;

public class Carte {
    private static Scanner sc = new Scanner(System.in);
    //int n = sc.nextInt();

    private int taille;
    private Vector[] obstacles;

    public Carte(int tailleMap, Vector[] obs)
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
