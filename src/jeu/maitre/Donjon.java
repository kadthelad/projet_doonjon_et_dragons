package jeu.maitre;

import jeu.joueurs.equipements.Equipement;
import jeu.joueurs.Personnage;

import java.util.Scanner;

public class Donjon {
    // La classe Donjon va gérer le jeu en général
    // Elle passera les tours et calculera les opérations dans le tour (combat, déplacement, autres actions...)


    private Personnage[] personnages;
    private Equipement[] equipements;

    private int tour = 0;

    public Donjon(Personnage[] lstPersonnages, Equipement[] lstEquipements)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Donjon ///
        this.personnages = lstPersonnages;
        this.equipements = lstEquipements;
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
