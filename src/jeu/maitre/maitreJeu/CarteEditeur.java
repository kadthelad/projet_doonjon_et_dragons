package jeu.maitre.maitreJeu;

import jeu.Entite;
import jeu.Jeu;
import jeu.joueurs.equipement.Catalogue;
import jeu.joueurs.equipement.Equipement;
import jeu.maitre.Carte;
import jeu.maitre.Monstre;
import jeu.maitre.Obstacle;
import jeu.utils.Input;
import jeu.utils.Output;
import jeu.utils.Position;

import java.util.ArrayList;

import jdk.javadoc.internal.doclets.formats.html.markup.Entity;

public class CarteEditeur {

  public static void carteEditeur() {
    while (true) {
      Output.clear();
      Output.println("""
          | =-=-=-=-= | EDITEUR DE CARTE | =-=-=-=-= |
          [0] Éditer une carte
          [1] Créer une nouvelle carte
          [2] Supprimer une carte
          [3] Quitter l'éditeur
           """);
      int userInput = Input.entier(0, 3, "Choisissez une commande : ");

      switch (userInput) {
        case 0 -> editerCarte();
        case 1 -> creerCarte();
        case 2 -> supprimerCarte();
        case 3 -> {
          return;
        }
      }
    }
  }

  private static void editerCarte() {
    int nombreCartes = Jeu.getNombreCartes();
    if (nombreCartes < 1) {
      Output.println("[!] Il n'y a pas encore de carte !");
      return;
    }

    while (true) {
      Output.println("-- Quelle carte voulez-vous éditer ? --");
      for (int i = 0; i < nombreCartes; i++)
        Output.println("[" + i + "] - " + Jeu.getCarte(i).getName());

      Output.println("[" + nombreCartes + "] - Retour");

      int choix = Input.entier(0, nombreCartes, "Choisissez une carte : ");
      if (choix == nombreCartes)
        return;

      Carte sauvegardeAvantEdition = new Carte(Jeu.getCarte(choix));
      Carte carte = Jeu.getCarte(choix);

      boolean editing = true;
      while (editing) {
        Output.clear();
        Output.println("Vous éditez la carte : " + carte.getName());
        carte.afficherCarte();
        Output.println("""
            [0] Changer la taille de la carte
            [1] Ajouter une entite
            [2] Enlever une entite
            [3] Changer le nom de la carte
            [4] Sauvegarder et quitter
            [5] Quitter sans sauvegarder
            """);
        int action = Input.entier(0, 5, "Action : ");
        switch (action) {
          case 0 -> changerTailleMap(carte);
          case 1 -> ajouteEntite(carte);
          case 2 -> enleveEntite(carte);
          case 3 -> carte.setName(Input.string("Choisissez un nom pour votre carte : "));
          case 4 -> {
            Output.println("Carte sauvegardée sous le nom de " + carte.getName() + " !");
            editing = false;
          }
          case 5 -> {
            if (Input.confirmer("Êtes-vous sûr de quitter sans sauvegarder ? (toutes vos modifications seront annulées !)")) {
              listCartes.set(choix, sauvegardeAvantEdition);
              Output.println("Modifications annulées.");
              editing = false;
            }
          }
        }
      }
    }
  }

  private static void creerCarte(ArrayList<Carte> listCartes) {
    Carte carte = new Carte(15, 15);
    changerTailleMap(carte);

    while (true) {
      carte.afficherCarte();
      Output.println("""
              ----------------------
              Choisissez une action :
              [0] Ajouter un obstacle
              [1] Supprimer un obstacle
              [2] Sauvegarder et quitter
              [3] Annuler et quitter
          """);
      int action = Input.entier(0, 3, "Action : ");
      switch (action) {
        case 0 -> ajoutObstacle(carte);
        case 1 -> supprimerObstacle(carte);
        case 2 -> {
          changerNomCarte(carte, nombreCartes);
          listCartes.add(carte);
          Output.println("La carte " + carte.getName() + " a été sauvegardée !");
          return;
        }
        case 3 -> {
          if (Input.confirmer("Annuler la création de la carte ?")) {
            Output.println("Création annulée.");
            return;
          }
        }
      }
    }
  }

  private static void supprimerCarte(ArrayList<Carte> listCartes) {
    if (listCartes.isEmpty()) {
      Output.println("[!] Il n'y a pas de carte créée !");
      return;
    }
    while (true) {
      Output.println("-- Quelle carte voulez-vous supprimer ? --");
      for (int i = 0; i < nombreCartes; i++)
        Output.println("[" + i + "] - " + listCartes.get(i).getName());
      Output.println("[" + nombreCartes + "] - Retour");

      int choix = Input.entier(0, nombreCartes, "Choisissez une carte à supprimer : ");
      if (choix == nombreCartes)
        return;

      if (Input.confirmer("Supprimer la carte \"" + listCartes.get(choix).getName() + "\" ?")) {
        listCartes.remove(choix);
        Output.println("Carte supprimée !");
        return;
      }
    }
  }

  private static void changerTailleMap(Carte carte) {
    Output.println("Quelle sera la taille de la carte ? (min:15 max:25)");
    int x = Input.entier(15, 25, "X (largeur) = ");
    int y = Input.entier(15, 25, "Y (hauteur) = ");
    carte.changeTaille(new Position(x, y));
  }

  private static void ajouteEntite(Carte carte) {
    carte.afficherCarte();
    Output.println("--- AJOUT D'ENTITE ---");

    int x = Input.entier(1, carte.getLargeur(), "X (abcisse)= ");
    int y = Input.entier(1, carte.getHauteur(), "Y (ordonnée) = ");
    carte.ajouteEntite(new Obstacle(), new Position(x, y));
  }

  private static Entite choixEntite() {
    Output.println("""
      Quelle entite souhaitez-vous ajouter sur la carte ?
      [1] Monstre
      [2] Obstacle
      [3] Arme
      [4] Armure
      """);
    int choix = Input.entier(1, 3, "Choix : ");
    Entite e;

    switch (choix) {
      case 1 -> {
        Output.println("Voici la liste des monstres : "); 
        for (int i = 0; i < Jeu.getNombreMonstres(); i++) {
          Output.println("[" + i+1 + "] " + Jeu.getMonstre(i).getNom());
        }
        e = Jeu.getMonstre(Input.entier(1, Jeu.getNombreMonstres(), "Quel monstre souhaitez-vous placer ?"));
      }
      case 2 -> e = new Obstacle();
      case 3 -> {
        Output.println("Voici la liste des armes : "); 
        for (int i = 0; i < Catalogue.getNombreArmes(); i++) {
          Output.println("[" + i+1 + "] " + Catalogue.getArmeParIndice(i));
        }
      }
    }

    return e;
  }

  private static void enleveEntite(Carte carte) {
    Output.println("--- SUPPRESSION D'UN OBSTACLE ---");
    Output.println("Quelle case souhaitez-vous vider ?");
    int x = Input.entier(1, carte.getLargeur(), "X (abcisse) = ");
    int y = Input.entier(1, carte.getHauteur(), "Y (abcisse) = ");
    carte.enleveEntite(new Position(x, y));
  }

  private static void changerNomCarte(Carte carte, int defaultIndex) {
    Output.print("Donnez un nom à votre carte : ");
    String nom = Input.string();
    if (nom == null || nom.isBlank())
      nom = "Carte sans nom" + defaultIndex;
    carte.setName(nom);
    Output.println("Cette carte sera nommée : " + carte.getName());
  }
}
