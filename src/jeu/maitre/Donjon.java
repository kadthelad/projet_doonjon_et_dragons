package jeu.maitre;

import jeu.joueurs.equipement.Catalogue;
import jeu.joueurs.Personnage;
import jeu.utils.Position;
import jeu.maitre.Carte;

import java.util.ArrayList;

public class Donjon {
  // La classe Donjon va gérer le donjon en cours
  // Elle passera les tours et gérera les opérations (combat, déplacement, autres actions...)

  private Carte carte;
  private Personnage[] listePersonnages;
  private int tour = 0;

  public Donjon(Carte c, Personnage[] listePerso) {
    this.carte = c;
    this.listePersonnages = listePerso;
  }

  public void jouerTour() {
    /// Methode pour faire jouer un tour ///
    // Faire jouer le tour

    // Ajouter 1 au tour
    tour++;
  }

  public void afficherCartes() {
    this.carte.afficherCarte();
  }
}
