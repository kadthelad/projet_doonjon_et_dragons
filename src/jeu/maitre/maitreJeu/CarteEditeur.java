package jeu.maitre.maitreJeu;
import jeu.maitre.Carte;
import jeu.maitre.Monstre;
import jeu.maitre.Obstacle;
import jeu.utils.Input;
import jeu.utils.Output;
import jeu.utils.Position;

import java.util.ArrayList;

public class CarteEditeur
{

  public static void carteEditeur(ArrayList<Carte> listCartes)
  {
    int userInput;
    boolean inEditor = true;
    while (inEditor)
    {
      // Création de la carte //
      Output.println("| =-=-=-=-= | EDITEUR DE CARTE | =-=-=-=-= |\n[0] Éditer une carte\n[1] Créer une nouvelle carte\n[2] Supprimer une carte\n[3] Quitter l'éditeur");
      userInput = Input.entier();

      switch (userInput)
      {
        case 0:
          editerCarte(listCartes);
          break;
        case 1:
          creerCarte(listCartes);
          break;
        case 2:
          supprCarte(listCartes);
          break;
        case 3:
          inEditor = false;
          break;
        default:
          Output.println("Veuillez choisir une commande valide.");
          break;
      }
    }
  }

  private static void editerCarte(ArrayList<Carte> listCartes)
  {
    int userInput;
    String userStrInput;
    boolean inEditing = true;

    while (inEditing)
    {
      if (listCartes.isEmpty())
      {
        Output.println("[!] Il n'y a pas de carte crée!\n");
        return;
      }

      Output.println("-- Quel carte voulez-vous éditer? --");
      for (int i = 0; i < listCartes.size(); i++)
      {
        Output.println("[" + i + "] - " + listCartes.get(i).getName());
      }
      Output.println("[" + listCartes.size() + "] - Revenir au menu d'éditeur de carte");
      int userInt = jeu.utils.Input.entier();
      if (userInt == listCartes.size())
      {
        return;
      }
      else if (userInt < listCartes.size())
      {
        // EDITER LA CARTE
        boolean inMapEditor = true;
        while (inMapEditor)
        {
          Carte carteDefault = new Carte(listCartes.get(userInt));
          Carte carteSelectionnee = listCartes.get(userInt);
          Output.println("Vous éditez la carte " + listCartes.get(userInt).getName());
          carteSelectionnee.afficherCarte();
          Output.println("\n[0] Changer la taille de la carte" +
                  "\n[1] Ajouter un obstacle" +
                  "\n[2] Retirer un obstacle" +
                  "\n[3] Changer le nom de la carte" +
                  "\n[4] Sauvegarder et quitter" +
                  "\n[5] Quitter sans sauvegarder");

          int userInt2 = Input.entier();

          switch (userInt2)
          {
            case 0: // CHANGER LA TAILLE DE LA CARTE
              carteSelectionnee.afficherCarte();
              changerTailleMap(carteSelectionnee);
              break;
            case 1: // AJOUTER UN OBSTACLE SUR LA CARTE
              carteSelectionnee.afficherCarte();
              ajoutObstacle(carteSelectionnee);
              break;
            case 2: // RETIRER UN OBSTACLE SUR LA CARTE
              carteSelectionnee.afficherCarte();
              supprObstacle(carteSelectionnee);
              break;
            case 3: // CHANGER LE NOM DE LA CARTE
              boolean inNameEditor = true;
              while (inNameEditor)
              {
                Output.print("Donnez un nom à votre carte: ");
                userStrInput = Input.string();
                if (userStrInput == "")
                {
                  carteSelectionnee.setName("Carte sans nom" + listCartes.size());
                }
                else
                {
                  carteSelectionnee.setName(userStrInput);
                }
                Output.println("Cette carte sera nommée " + carteSelectionnee.getName());
                inNameEditor = false;
              }
              break;
            case 4: // SAUVEGARDER LA CARTE ET QUITTER
              Output.println("Carte souvegardée sous le nom de " + carteSelectionnee.getName() + "!");
              inMapEditor = false;
              break;
            case 5: // QUITTER SANS SAUVEGARDER LA CARTE
              boolean inNotSaveAndQuit = true;

              while (inNotSaveAndQuit)
              {
                Output.println("Êtes-vous sûr.e de quitter sans sauvegarder? (toutes vos modifications seront annulées!)" +
                        "\n[0] Oui" +
                        "\n[1] Non");
                int userInput3 = Input.entier();
                if (userInput3 == 0)
                {
                  listCartes.set(userInt, carteDefault);
                  inNotSaveAndQuit = false;
                  inMapEditor = false;
                } else if (userInt != 1)
                {
                  Output.println("Entrez une commande valide!");
                }
                else
                {
                  inNotSaveAndQuit = false;
                }
                break;
              }
              default:
                Output.println("Entrez une commande valide!");
                break;
          }
        }
      }
      else
      {
        Output.println("Entrez une commande existante!");
      }
    }
  }

  private static void creerCarte(ArrayList<Carte> listCartes)
  {
    int userInput;
    String userStrInput;

    Carte carteCreee = new Carte(15, 15);
    changerTailleMap(carteCreee);

    boolean inMapEditor = true;

    while (inMapEditor)
    {
      carteCreee.afficherCarte();
      Output.println("----------------------\nChoisissez une action:\n[0] Ajouter un obstacle\n[1] Supprimer un obstacle\n[2] Continuer");
      userInput = Input.entier();
      switch (userInput)
      {
        case 0:
          ajoutObstacle(carteCreee);
          break;

        case 1:
          supprObstacle(carteCreee);
          break;

        case 2:
          boolean confirmExit = true;
          int userInput2;
          while (confirmExit)
          {
            Output.println("Êtes-vous sûr de sauvegarder votre carte?\n[0] Oui, sauvegarder et quitter\n[1] Non, retour à la création de la carte\n[2] Revoir la carte");
            userInput2 = Input.entier();

            switch (userInput2)
            {
              case 0:
                Output.print("Donnez un nom à votre carte: ");
                userStrInput = Input.string();
                if (userStrInput == "")
                {
                  carteCreee.setName("Carte sans nom" + listCartes.size());
                }
                else
                {
                  carteCreee.setName(userStrInput);
                }

                listCartes.add(carteCreee);
                Output.print("La carte " + carteCreee.getName() + " à été sauvegardé avec succès!");
                confirmExit = false;
                inMapEditor = false;
                break;

              case 1:
                confirmExit = false;
                break;

              case 2:
                carteCreee.afficherCarte();
                break;

              default:
                Output.println("Veuillez écrire une commande valide!");
                break;
            }
          }
          break;
        default:
          Output.println("Veuillez écrire une commande valide!");
          break;
      }
    }
  }

  private static void supprCarte(ArrayList<Carte> listCartes)
  {
    boolean inDeleting = true;

    while (inDeleting)
    {
      if (listCartes.isEmpty())
      {
        Output.println("[!] Il n'y a pas de carte crée!\n");
        return;
      }
      // supprimer une carte
      Output.println("-- Quel carte voulez-vous supprimer? --");
      for (int i = 0; i < listCartes.size(); i++)
      {
        Output.println("[" + i + "] - " + listCartes.get(i).getName());
      }
      Output.println("[" + listCartes.size() + "] - Revenir à l'éditeur de carte");
      int userInt = jeu.utils.Input.entier();
      if (userInt == listCartes.size())
      {
        return;
      }
      else if (userInt < listCartes.size())
      {
        Output.println("[!] Carte " + listCartes.get(userInt).getName() + " supprimé!\n");
        listCartes.remove(userInt);
      }
      else
      {
        Output.println("Entrez une commande existante!");
      }
    }
  }

  private static void changerTailleMap(Carte carteCreee)
  {
    Output.println("Quelle sera la taille de la carte ? (min:15  max:25)");

    Position taille = new Position();

    int n = 0;
    do {
      Output.print("X (largeur) =  ");
      n = Input.entier();
    } while (checkSize(n));
    taille.x(n);

    n = 0;
    do {
      Output.print("Y (hauteur) =  ");
      n = Input.entier();
    } while (checkSize(n));
    taille.y(n);
    carteCreee.changeTaille(taille);
  }

  public static boolean checkSize(int t) {
    return (t < 15 || t > 25);
  }

  private static void ajoutObstacle(Carte carteCreee)
  {
    Output.println("--- AJOUT D'UN OBSTACLE ---\nEntrez la position à laquelle vous voulez créer un obstacle : ");
    // Avoir la position pour poser l'obstacle
    boolean inObstacleAdd = true;

    Position taille = new Position(15, 15);

    while (inObstacleAdd)
    {
      Output.print("X =  ");
      taille.x(Input.entier());
      Output.print("Y =  ");
      taille.y(Input.entier());
      Output.print("\n");
      if ((taille.x() <= carteCreee.getTaille().x() && taille.x() >= 1) && (taille.y() <= carteCreee.getTaille().y() && taille.y() >= 1))
      {
        carteCreee.ajouteEntite(new Obstacle(), taille);
        inObstacleAdd = false;
      }
      else
      {
        Output.println("Vous devez choisir une position qui se trouve dans la carte!");
      }
    }
  }

  private static void supprObstacle(Carte carteCreee)
  {
      Output.println("--- AJOUT D'UN OBSTACLE ---\nEntrez la position à laquelle vous voulez supprimer un obstacle : ");
      // Avoir la position pour poser l'obstacle
      boolean inObstacleRemv = true;

      Position taille = new Position(15, 15);
    
      while (inObstacleRemv)
      {
        Output.print("X =  ");
        taille.x(Input.entier());
        Output.print("Y =  ");
        taille.y(Input.entier());
        Output.print("\n");
        if ((taille.x() <= carteCreee.getTaille().x() && taille.x() >= 1) && (taille.y() <= carteCreee.getTaille().y() && taille.y() >= 1))
        {
          carteCreee.enleveEntite(new Position(taille.x(), taille.y()));
          inObstacleRemv = false;
        }
        else
        {
          Output.println("Vous devez choisir une position qui se trouve dans la carte!");
        }
      }
  }
}
