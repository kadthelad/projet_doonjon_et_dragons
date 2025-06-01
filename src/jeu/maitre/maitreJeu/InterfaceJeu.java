package jeu.maitre.maitreJeu;

import jeu.joueurs.Personnage;
import jeu.joueurs.equipement.ArmureLegere;
import jeu.joueurs.equipement.Equipement;
import jeu.maitre.Carte;
import jeu.maitre.Donjon;
import jeu.maitre.Monstre;
import jeu.utils.*;
import jeu.joueurs.equipement.*;

import java.util.ArrayList;

public class InterfaceJeu
{

  public static int commencerJeu(ArrayList<Carte> listeCartes, ArrayList<Monstre> listeMonstres, ArrayList<Personnage> listePersonnages)
  {
    boolean inCommencerJeu = true;

    // =============================== // EQUIPEMENTS // ===================================== //
    ArrayList<Equipement> listeEquipements = new ArrayList<>() {};
    // ARMURES
    listeEquipements.add(new ArmureLegere("Armure d'Ecailles", 9));
    listeEquipements.add(new ArmureLegere("Demi-plate", 10));
    listeEquipements.add(new ArmureLourde("Cotte de Maille", 11));
    listeEquipements.add(new ArmureLourde("Harnois", 12));
    // ARMES
    listeEquipements.add(new ArmeCourante("Bâton", "1d6", 1));
    listeEquipements.add(new ArmeCourante("Masse d'Armes", "1d6", 1));
    listeEquipements.add(new ArmeDeGuerre("Épée Longue", "1d8", 1));
    listeEquipements.add(new ArmeDeGuerre("Rapière", "1d8", 1));
    listeEquipements.add(new ArmeDistance("Arbalète légère", "1d8", 16));
    listeEquipements.add(new ArmeDistance("Fronde", "1d4", 6));
    listeEquipements.add(new ArmeDistance("Arc Court", "1d6", 16));
    // ======================================================================================= //

    ArrayList<Carte> listeCartesDonjon = new ArrayList<>() {};

    while (inCommencerJeu)
    {
      Output.print("|=|=|=|=| PROJET DOOnjon et dragons |=|=|=|=|\n====== JEU ======\n" +
              "[0] Choisir les cartes pour le donjon\n" +
              "[1] Préparer les cartes du donjon\n" +
              "[2] Lancer le jeu!\n" +
              "[1000] Menu Principal\n" +
              "[1001] Quitter le jeu" +
              "\n|=|=|=|=|=|=|=|=|=|=|=|=|=|=|");
      int userInt = Input.entier();

      switch (userInt)
      {
        case 0: // CHOISIR LES CARTES
          if (listeCartes.isEmpty())
          {
            Output.print("[!!!] Il n'y a pas de carte crée!\n");
            return 1;
          }
          choisirCartes(listeCartes, listeCartesDonjon);
          break;
        case 1: // PREPARER LES CARTES
          preparerCartes(listeCartesDonjon);
          break;
        case 2: // COMMENCER LE JEU
          //Donjon donjonActuel = new Donjon(listeCartesDonjon, listePersonnages, liste);
          break;
        case 1000: // RETOURNER AU MENU PRINCIPAL
          inCommencerJeu = false;
          break;
        case 1001: // QUITTER LE JEU
          return 0;
        default:
          break;
      }
    }
    return 1;
  }

  private static void choisirCartes(ArrayList<Carte> listCartes, ArrayList<Carte> listCartesDonjon)
  {
    boolean inChoosingMaps = true;

    while (inChoosingMaps)
    {
      // choisir une carte
      Output.print("-- Quelle.s carte.s voulez-vous ajouter à votre donjon? --\n");
      for (int i = 0; i < listCartes.size(); i++)
      {
        if (listCartesDonjon.contains(listCartes.get(i)))
        {
          Output.print("[!] [" + i + "] - " + listCartes.get(i).getName() + " (Déja dans le donjon)\n");
        }
        else
        {

          Output.print("[" + i + "] - " + listCartes.get(i).getName());
        }
      }
      Output.print("[" + listCartes.size() + "] - Revenir au menu de jeu\n");
      int userInt = jeu.utils.Input.entier();
      if (userInt == listCartes.size()) // Retourner au menu de jeu
      {
        return;
      }
      else if (userInt < listCartes.size()) // ajouter cette carte au donjon
      {
        if (listCartesDonjon.contains(listCartes.get(userInt)))
        {
          listCartesDonjon.remove(listCartes.get(userInt));
          Output.print("La carte " + listCartes.get(userInt).getName() + " a été retiré du donjon!\n");
        }
        else
        {
          listCartesDonjon.add(listCartes.get(userInt));
          Output.print("La carte " + listCartes.get(userInt).getName() + " a été ajouté dans le donjon!\n");
        }
      }
    }
  }

  private static void preparerCartes(ArrayList<Carte> listCartesDonjon)
  {
    boolean inPreparingMap = true;

    while (inPreparingMap)
    {
      // choisir une carte
      Output.print("-- Quelle carte voulez-vous modifier? --\n");
      for (int i = 0; i < listCartesDonjon.size(); i++)
      {
        Output.print("[" + i + "] - " + listCartesDonjon.get(i).getName());
      }
      Output.print("[" + listCartesDonjon.size() + "] - Revenir au menu de jeu\n");
      int userInt = jeu.utils.Input.entier();
      if (userInt == listCartesDonjon.size()) // Retourner au menu de jeu
      {
        return;
      }
      else if (userInt < listCartesDonjon.size()) // préparer cette carte
      {
        boolean inMapEditor = true;
        while (inMapEditor)
        {
          Output.print("==== Modification de la carte " + listCartesDonjon.get(userInt).getName() + " ====\n" +
                  "[0] Ajouter un objet au sol" +
                  "[1] Ajouter un monstre" +
                  "[2] Placer les joueurs" +
                  "[3] Retour au menu de jeu");
          int userInt2 = Input.entier();
          switch (userInt2)
          {
            case 0: // ajouter objet au sol
              break;
            case 1: // ajouter monstre sur la carte
              break;
            case 2: // placer joueurs
              break;
            case 3: // retour en arrière
              inMapEditor = false;
              break;
            default:
              Output.print("Veuillez entrer une commande valide!");
              break;
          }
        }
      }
    }
  }

}
