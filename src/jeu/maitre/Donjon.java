package jeu.maitre;

import jeu.Caracteristique;
import jeu.Creature;
import jeu.Entite;
import jeu.joueurs.equipement.Catalogue;
import jeu.joueurs.Personnage;
import jeu.utils.Des;
import jeu.utils.Output;
import jeu.utils.Position;
import jeu.maitre.Carte;

import java.util.ArrayList;

public class Donjon {
  // La classe Donjon va gérer le donjon en cours
  // Elle passera les tours et gérera les opérations (combat, déplacement, autres actions...)

  private ArrayList<Carte> cartes;
  private ArrayList<Personnage> personnages;
  private ArrayList<Monstre> monstres;

  private int tour = 1;
  private ArrayList<Creature> listeOrdreJeu = new ArrayList<Creature>() {};

  static private int indexCarteActuelle = -1;

  static public int getIndexCarteActuelle() {
    return indexCarteActuelle;
  }

  static public void setIndexCarteActuelle(int index) {
    indexCarteActuelle = index;
  }

  public Donjon(ArrayList<Carte> carteJeu, ArrayList<Personnage> listPersonnages, ArrayList<Monstre> listMonstres) {
    /// CONSTRUCTEUR DE LA CLASSE Donjon ///
    this.cartes = carteJeu;
    this.personnages = listPersonnages;
    this.monstres = listMonstres;
  }

  public void lancerJeu() {
    /// Méthode pour créer le jeu ///
    // Lors de l'appel de la méthode, le maitre du jeu devra créer la carte,
    /// l'emplacement des obstacles, des
    // personnages et des equipement
    boolean jeu = true;
    ArrayList<Creature> listEveryone = new ArrayList<Creature>() {};
    listEveryone.addAll(this.personnages);
    listEveryone.addAll(this.monstres);

    while (jeu) {
      Output.print("Tour : " + this.tour);

      Output.print("Au tour du Maitre du Jeu!\n");

      // 1er tour, faire les initiatives
      if (this.tour == 1)
      {
        Output.print("Tous les joueurs et les monstres lancent un 1d20 et ajoutent sa valeur dans leur initiative!\n");
        for (Creature crea : listEveryone)
        {
          int deVal = Des.lancer();
          int val = crea.getCaracteristique(Caracteristique.INITIATIVE);
          Output.print(crea.getNom() + " (Initiative = " + val + ") aura une addition de + " + deVal + " points d'initiative!\n");
          crea.setCaracteristique(Caracteristique.INITIATIVE, val + deVal);
          ajouterDansListeOrdre(crea);
        }
        Output.print("Voici l'ordre de jeu : \n");
        for (int i=0; i<this.listeOrdreJeu.size(); i++)
        {
          Output.print(i + " : " + listeOrdreJeu.get(i).getNom());
        }
      }
    }
  }

  private void ajouterDansListeOrdre(Creature creatureAjoutee)
  {
    for (Creature crea : this.listeOrdreJeu)
    {
     if (crea.getCaracteristique(Caracteristique.INITIATIVE) > creatureAjoutee.getCaracteristique(Caracteristique.INITIATIVE))
     {
       this.listeOrdreJeu.add(this.listeOrdreJeu.indexOf(crea), creatureAjoutee);
       return;
     }
    }
  }

  public void jouerTour() {
    /// Methode pour faire jouer un tour ///
    // Faire jouer le tour

    // Ajouter 1 au tour
    tour++;
  }

  public void afficherCartes() {
    this.cartes.toString();
  }
}
