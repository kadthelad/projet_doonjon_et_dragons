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
      System.out.println("| =-=-=-=-= | EDITEUR DE CARTE | =-=-=-=-= |\n[0] Éditer une carte\n[1] Créer une nouvelle carte\n[2] Supprimer une carte\n[3] Quitter l'éditeur");
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
          System.out.println("Veuillez écrire une commande valide!");
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
        System.out.println("[!] Il n'y a pas de carte crée!\n");
        return;
      }

      System.out.println("-- Quel carte voulez-vous éditer? --");
      for (int i = 0; i < listCartes.size(); i++)
      {
        System.out.println("[" + i + "] - " + listCartes.get(i).getName());
      }
      System.out.println("[" + listCartes.size() + "] - Revenir au menu d'éditeur de carte");
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
          System.out.println("Vous éditez la carte " + listCartes.get(userInt).getName());
          carteSelectionnee.afficherCarte();
          System.out.println("\n[0] Changer la taille de la carte" +
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
                System.out.print("Donnez un nom à votre carte: ");
                userStrInput = Input.string();
                if (userStrInput == "")
                {
                  carteSelectionnee.setName("Carte sans nom" + listCartes.size());
                }
                else
                {
                  carteSelectionnee.setName(userStrInput);
                }
                System.out.println("Cette carte sera nommée " + carteSelectionnee.getName());
                inNameEditor = false;
              }
              break;
            case 4: // SAUVEGARDER LA CARTE ET QUITTER
              System.out.println("Carte souvegardée sous le nom de " + carteSelectionnee.getName() + "!");
              inEditing = false;
              break;
            case 5: // QUITTER SANS SAUVEGARDER LA CARTE
              boolean inNotSaveAndQuit = true;

              while (inNotSaveAndQuit)
              {
                System.out.println("Êtes-vous sûr.e de quitter sans sauvegarder? (toutes vos modifications seront annulées!)" +
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
                  System.out.println("Entrez une commande valide!");
                }
                else
                {
                  inNotSaveAndQuit = false;
                }
                break;
              }
              default:
                System.out.println("Entrez une commande valide!");
                break;
          }
        }
      }
      else
      {
        System.out.println("Entrez une commande existante!");
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
      System.out.println("----------------------\nChoisissez une action:\n[0] Ajouter un obstacle\n[1] Supprimer un obstacle\n[2] Continuer");
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
            System.out.println("Êtes-vous sûr de sauvegarder votre carte?\n[0] Oui, sauvegarder et quitter\n[1] Non, retour à la création de la carte\n[2] Revoir la carte");
            userInput2 = Input.entier();

            switch (userInput2)
            {
              case 0:
                System.out.print("Donnez un nom à votre carte: ");
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
                System.out.println("Veuillez écrire une commande valide!");
                break;
            }
          }
          break;
        default:
          System.out.println("Veuillez écrire une commande valide!");
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
        System.out.println("[!] Il n'y a pas de carte crée!\n");
        return;
      }
      // supprimer un monstre
      System.out.println("-- Quel carte voulez-vous supprimer? --");
      for (int i = 0; i < listCartes.size(); i++)
      {
        System.out.println("[" + (i) + "] - " + listCartes.get(i).getName());
      }
      System.out.println("[" + listCartes.size() + "] - Revenir à l'éditeur de carte");
      int userInt = jeu.utils.Input.entier();
      if (userInt == listCartes.size() + 1)
      {
        return;
      }
      else if (userInt <= listCartes.size())
      {
        System.out.println("[!] Carte " + listCartes.get(userInt).getName() + " supprimé!\n");
        listCartes.remove(userInt);
      }
      else
      {
        System.out.println("Entrez une commande existante!");
      }
    }
  }

  private static void changerTailleMap(Carte carteCreee)
  {
    System.out.println("Quelle sera la taille de la carte?: ");
    boolean inMapSizeEditor = true;

    int taillex = 15;
    int tailley = 15;

    while (inMapSizeEditor)
    {
      System.out.print("X =  ");
      taillex = Input.entier();
      System.out.print("Y =  ");
      tailley = Input.entier();
      System.out.print("\n");
      if ((taillex >= 15 && taillex <= 25) && (tailley >= 15 && tailley <= 25))
      {
        inMapSizeEditor = false;
      }
      else
      {
        System.out.println("Vous devez choisir une taille entre 15 et 25!");
      }
    }
    carteCreee.changeSize(taillex, tailley);
  }

  private static void ajoutObstacle(Carte carteCreee)
  {
    System.out.println("--- AJOUT D'UN OBSTACLE ---\nEntrez la position à laquelle vous voulez créer un obstacle : ");
    // Avoir la position pour poser l'obstacle
    boolean inObstacleAdd = true;

    int taillex = 15;
    int tailley = 15;

    while (inObstacleAdd)
    {
      System.out.print("X =  ");
      taillex = Input.entier();
      System.out.print("Y =  ");
      tailley = Input.entier();
      System.out.print("\n");
      if ((taillex <= carteCreee.getTaille().getX() && taillex >= 1) && (tailley <= carteCreee.getTaille().getY() && tailley >= 1))
      {
        carteCreee.addChose(new Position(taillex, tailley), new Obstacle());
        inObstacleAdd = false;
      }
      else
      {
        System.out.println("Vous devez choisir une position qui se trouve dans la carte!");
      }
    }
  }

  private static void supprObstacle(Carte carteCreee)
  {
    if (carteCreee.hasObstacles())
    {
      System.out.println("--- AJOUT D'UN OBSTACLE ---\nEntrez la position à laquelle vous voulez supprimer un obstacle : ");
      // Avoir la position pour poser l'obstacle
      boolean inObstacleRemv = true;

      int taillex = 15;
      int tailley = 15;

      while (inObstacleRemv)
      {
        System.out.print("X =  ");
        taillex = Input.entier();
        System.out.print("Y =  ");
        tailley = Input.entier();
        System.out.print("\n");
        if ((taillex <= carteCreee.getTaille().getX() && taillex >= 1) && (tailley <= carteCreee.getTaille().getY() && tailley >= 1))
        {
          if (carteCreee.choseAt(new Position(taillex, tailley)).getAlias() == "[x]")
          {
            carteCreee.removeChose(new Position(taillex, tailley));
            inObstacleRemv = false;
          }
          else
          {
            System.out.println("Il n'y a pas d'obstacle à " + new Position(taillex, tailley).toString());
            inObstacleRemv = false;
          }
        }
        else
        {
          System.out.println("Vous devez choisir une position qui se trouve dans la carte!");
        }
      }
    }
    else
    {
      System.out.println("Il n'y a pas d'obstacles dans la carte!");
    }
  }
}
