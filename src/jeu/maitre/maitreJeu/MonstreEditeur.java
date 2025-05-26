package jeu.maitre.maitreJeu;
import jeu.maitre.Monstre;
import jeu.utils.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonstreEditeur
{
  public static int monstreEditeur(ArrayList<Monstre> listMonstres)
  {
    int userInput;
    String userStrInput;

    // Création des monstres //
    System.out.println("| =-=-=-=-= | EDITEUR DE MONSTRES | =-=-=-=-= |");
    boolean inMstrEditor = true;

    while (inMstrEditor)
    {
      if (listMonstres.isEmpty())
      {
        System.out.println("[Vous n'avez aucun monstre crée pour l'instant!]");
      }
      else
      {
        System.out.println("Vous avez déjà crée ce(s) monstre(s):");
        for (int i=0; i<listMonstres.size(); i++)
        {
          System.out.println(listMonstres.get(i).getEspece());
        }
      }

      System.out.println("----------------------\nChoisissez une action:\n[0] Ajouter un monstre\n[1] Supprimer un monstre\n[2] Continuer");
      userInput = jeu.utils.Input.entier();
      switch (userInput)
      {
        case 0:
          listMonstres.add(ajoutMonstre());
          break;
        case 1:
          supprMonstre(listMonstres);
          break;
        case 2:
          inMstrEditor = false;
          break;
        default:
          System.out.println("Veuillez entrer une commande valide!");
          break;
      }
    }
    return 1;
  }

  private static Monstre ajoutMonstre()
  {
    String nomMstr = "";
    String attMstr = "";
    int[] speAttMstr = {0, 0, 0};
    int pdv = 0;
    Map<String, Integer> caraMstr = new HashMap<>();
    {
      caraMstr.put("force", 0);
      caraMstr.put("dexterite", 0);
      caraMstr.put("vitesse", 0);
      caraMstr.put("initiative", 0);
      caraMstr.put("classeArmure", 0);
    }


    System.out.println("----- CREATION DE MONSTRE -----");
    // nommer le monstre
    boolean chooseName = true;
    while (chooseName)
    {
      System.out.println("Donnez un nom à l'espèce du monstre");
      nomMstr = Input.string();
      if (nomMstr != "")
      {
        chooseName = false;
      }
      else
      {
        System.out.println("[!] Veuillez écrire un nom pour l'espèce du monstre!\n");
      }
    }


    // créer une attaque
    boolean inAttEditor = true;
    while (inAttEditor)
    {
      System.out.println("Donnez un nom à l'attaque du monstre " + nomMstr);
      attMstr = Input.string();

      System.out.println("Combien de dégâts fera votre attaque?");
      speAttMstr[0] = jeu.utils.Input.entier();
      System.out.println("Combien de dés devra-t'il jeter?");
      speAttMstr[1] = jeu.utils.Input.entier();
      System.out.println("Quelle sera la valeur de son/ses dé(s)?");
      speAttMstr[2] = jeu.utils.Input.entier();

      System.out.println("----- Caractéristiques de l'attaque " + attMstr + " -----");
      System.out.println(speAttMstr[0] + " dégàt(s), " + speAttMstr[1]+"d"+speAttMstr[2]);
      System.out.println("--------------\nVoulez vous garder cette attaque?\n[0] Oui, continuer\n[1] Non, refaire");
      int userInt = jeu.utils.Input.entier();
      switch (userInt)
      {
        case 0:
          inAttEditor = false;
          break;
        case 1:
          break;
        default:
          System.out.println("Veuillez entrer une commande valide!");
          break;
      }
    }

    // set les caractéristiques du monstre
    boolean inCaraEditor = true;
    while (inCaraEditor)
    {
      // pour avoir chaque valeur des caractéristiques dans une clé
      List<String> keys = new ArrayList<>(caraMstr.keySet());
      System.out.println("Choisir les caractéristiques du monstre : " +
              "\n[0] - " + pdv + " HP");

      for (int i=0; i<caraMstr.size(); i++)
      {
        System.out.println("["+(i+1)+"] - " + caraMstr.get(keys.get(i)) + " " + keys.get(i));
      }
      System.out.println("["+(caraMstr.size()+1)+"] - Pour quitter l'éditeur de caractéristiques.");
      int userInt1 = jeu.utils.Input.entier();
      int userInt2;
      if (userInt1 < 0 || userInt1>caraMstr.size())
      {
        inCaraEditor = false;
      }
      else if (userInt1 == 0)
      {
        System.out.print("Entrez une nouvelle valeur pour cette caracteristique: ");
        userInt2 = jeu.utils.Input.entier();
        pdv = userInt2;
      }
      else
      {
        System.out.print("Entrez une nouvelle valeur pour cette caracteristique: ");
        userInt2 = jeu.utils.Input.entier();
        caraMstr.replace(keys.get(userInt1-1), userInt2);
      }
    }

    System.out.println("Le monstre " + nomMstr + " est ajouté dans la liste des monstres!");
    return new Monstre(nomMstr, attMstr, speAttMstr, pdv, caraMstr);
  }

  private static void supprMonstre(ArrayList<Monstre> listMonstres)
  {
    boolean inDeleting = true;

    while (inDeleting)
    {
      if (listMonstres.isEmpty())
      {
        System.out.println("[!] Il n'y a pas de monstre crée!\n");
        return;
      }
      // supprimer un monstre
      System.out.println("-- Quel monstre voulez-vous supprimer? --");
      for (int i = 0; i < listMonstres.size(); i++)
      {
        System.out.println("[" + (i) + "] - " + listMonstres.get(i).getEspece());
      }
      System.out.println("[" + listMonstres.size() + "] - Revenir à l'éditeur de monstre");
      int userInt = jeu.utils.Input.entier();
      if (userInt == listMonstres.size() + 1)
      {
        return;
      }
      else if (userInt <= listMonstres.size())
      {
        System.out.println("[!] Monstre " + listMonstres.get(userInt).getEspece() + " supprimé!\n");
        listMonstres.remove(userInt);
      }
      else
      {
        System.out.println("Entrez une commande existante!");
      }
    }
  }
}
