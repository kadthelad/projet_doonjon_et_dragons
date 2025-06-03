package jeu.maitre;

import jeu.joueurs.equipement.Equipement;
import jeu.joueurs.Personnage;
import jeu.utils.Position;
import jeu.maitre.Carte;

import java.util.ArrayList;


public class Donjon {
    // La classe Donjon va gérer le jeu en général
    // Elle passera les tours et calculera les opérations dans le tour (combat, déplacement, autres actions...)


    private ArrayList<Carte> cartes;
    private Personnage[] personnages;
    private Equipement[] equipement;

    private int tour = 0;

    public Donjon(ArrayList<Carte> carteJeu, Personnage[] lstPersonnages, Equipement[] lstEquipements)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Donjon ///
        this.cartes = carteJeu;
        this.personnages = lstPersonnages;
        this.equipement = lstEquipements;
    }

    public Donjon créerJeu()
    {
        ///  Méthode pour créer le jeu ///
        // Lors de l'appel de la méthode, le maitre du jeu devra créer la carte, l'emplacement des obstacles, des
        // personnages et des equipement
        boolean jeu = true;

        while (jeu)
        {
            System.out.println("Au tour du Maitre du Jeu!");

            // Création de la carte //
            System.out.println("");
        }

        return null; // pour enlever l'erreur, je changerai plus tard
    }

    public void jouerTour()
    {
        /// Methode pour faire jouer un tour ///
        // Faire jouer le tour

        // Ajouter 1 au tour
        tour++;
    }

    public String afficherCartes()
    {
        return this.cartes.toString();
    }
}
