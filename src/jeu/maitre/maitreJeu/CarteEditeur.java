package jeu.maitre.maitreJeu;
import jeu.maitre.Carte;
import jeu.utils.Input;

import java.util.ArrayList;

public class CarteEditeur
{

  public static Carte carteEditeur(ArrayList<Carte> listCartes)
  {
    int userInput;
    String userStrInput;

    // Création de la carte //
    System.out.println("| =-=-=-=-= | EDITEUR DE CARTE | =-=-=-=-= |");
    System.out.println("Quelle sera la taille de la carte?(exemple:2, 3): ");
    userStrInput = Input.string();
    boolean inMapSizeEditor = true;
    String[] result;
    int taillex = 15;
    int tailley = 15;

    while (inMapSizeEditor)
    {
      userStrInput = Input.string();
      result = userStrInput.split(", ");
      if (result.length != 2)
      {
        System.out.println("Veuillez respecter le format '2, 3'.");
      }
      else
      {
        try
        {
          taillex = Integer.parseInt(result[0]);
          taillex = Integer.parseInt(result[0]);
          tailley = Integer.parseInt(result[1]);

          if ((taillex >= 15 && taillex <= 25) && (tailley >= 15 && tailley <= 25))
          {
            inMapSizeEditor = false;
          }
          else
          {
            System.out.println("Vous devez choisir une taille entre 15 et 25!");
          }
        }
        catch (NumberFormatException e)
        {
          System.out.println("Entrez des valeurs numériques!");
        }
      }
    }
    Carte carteCreee = new Carte(taillex, tailley);
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
    return carteCreee;
  }
  private static void ajoutObstacle(Carte carteCreee)
  {
    String userStrInput;
    String[] result;

    System.out.print("--- AJOUT D'UN OBSTACLE ---\nEntrez la position à laquelle vous voulez créer un obstacle (exemple:" +
            "2, 3): ");
    // Avoir la position pour poser l'obstacle
    userStrInput = Input.string();
    boolean dansAjout = true;

    while (dansAjout)
    {
      userStrInput = Input.string();
      result = userStrInput.split(", ");
      if (result.length != 2)
      {
        System.out.println("Veuillez respecter le format '2, 3'.");
      }
      else
      {
        try
        {
          int posx = Integer.parseInt(result[0]);
          int posy = Integer.parseInt(result[1]);

          if (posx <= carteCreee.getTaille().getX() && posy <= carteCreee.getTaille().getY()) {
            if (!carteCreee.obstacleAt(new jeu.utils.Position(posx, posy))) {
              carteCreee.addObstacle(new jeu.utils.Position(posx, posy));
              dansAjout = false;
            }
            else
            {
              System.out.println("Il y a déjà un obstacle à cette position!");
            }
          }
          else
          {
            System.out.println("Vous devez choisir une position qui se trouve dans la carte!");
          }
        }
        catch (NumberFormatException e)
        {
          System.out.println("Entrez des valeurs numériques!");
        }
      }
    }
  }

  private static void supprObstacle(Carte carteCreee)
  {
    String userStrInput;
    String[] result;

    if (!carteCreee.getObstacles().isEmpty()) {
      System.out.print("--- SUPPRESSION D'UN OBSTACLE ---\nEntrez la position à laquelle vous voulez retirer un obstacle (exemple:" +
              "2, 3): ");
      userStrInput = Input.string();
      boolean dansSuppr = true;

      while (dansSuppr)
      {
        userStrInput = Input.string();
        result = userStrInput.split(", ");
        if (result.length != 2)
        {
          System.out.println("Veuillez respecter le format '2, 3'.");
        }
        else
        {
          try
          {
            int posx = Integer.parseInt(result[0]);
            int posy = Integer.parseInt(result[1]);
            if (posx <= carteCreee.getTaille().getX() && posy <= carteCreee.getTaille().getY()) {
              if (carteCreee.obstacleAt(new jeu.utils.Position(posx, posy))) {
                carteCreee.removeObstacle(new jeu.utils.Position(posx, posy));
                dansSuppr = false;
              }
              else
              {
                System.out.println("Il n'y a pas obstacle à cette position!");
              }
            }
            else
            {
              System.out.println("Vous devez choisir une position qui se trouve dans la carte!");
            }
          }
          catch (NumberFormatException e)
          {
            System.out.println("Entrez des valeurs numériques!");
          }
        }
      }
      return;
    }
    else
    {
      System.out.println("Il n'y a pas d'obstacles dans la carte!");
    }
  }
}
