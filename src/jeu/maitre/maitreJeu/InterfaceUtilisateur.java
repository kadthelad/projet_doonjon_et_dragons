package jeu.maitre.maitreJeu;
import jeu.joueurs.InitialisationJoueurs;
import jeu.joueurs.Personnage;
import jeu.maitre.Carte;
import jeu.maitre.Monstre;

import java.util.ArrayList;

public class InterfaceUtilisateur
{
  public static int MainMenu(ArrayList<Carte> listeCartes, ArrayList<Monstre> listeMonstres, ArrayList<Personnage> listePersonnages)
  {
    boolean game = true;
    while (game)
    {
      System.out.println("|=|=|=|=| PROJET DOOnjon et dragons |=|=|=|=|\n====== MENU PRINCIPAL ======\n" +
              "\n[0] Création des cartes" +
              "\n[1] Création des monstres" +
              "\n[2] Création des personnages" +
              "\n[3] Jouer" +
              "\n[1000] Crédits" +
              "\n[1001] Quitter" +
              "\n|=|=|=|=|=|=|=|=|=|=|=|=|=|=|");
      int userInt1 = jeu.utils.Input.entier();

      switch (userInt1)
      {
        case 0:
          CarteEditeur.carteEditeur(listeCartes);
          break;
        case 1:
          MonstreEditeur.monstreEditeur(listeMonstres);
          break;
        case 2:
          listePersonnages = InitialisationJoueurs.creationPersonnages(InitialisationJoueurs.combienJoueurs());
          break;
        case 3:
          boolean verif = true;
          if (listeCartes.isEmpty())
          {
            System.out.println("[!] Il faut avoir crée au moins une carte pour jouer!");
            verif = false;
          }
          if (listeMonstres.isEmpty())
          {
            System.out.println("[!] Il faut avoir crée au moins un monstre crée pour jouer!");
            verif = false;
          }
          if (listePersonnages.isEmpty())
          {
            System.out.println("[!] Il faut avoir crée au moins un joueur pour jouer!");
            verif = false;
          }
          if (verif)
          {
            return 1;
          }
          else
          {
            break;
          }
        case 1000:
          System.out.println("==== CREDITS ====\nProjet de Kadir EKICI et Ivanoë MEGNIN-PREISS\nTP3 INFO IUTRS Illkirch");
          break;
        case 1001:
          game = false;
          break;
        default:
          System.out.println("Entrez une commande valide!");
          break;
      }
    }
    return 0;
  }
}
