package jeu.maitre.maitreJeu;
import jeu.Caracteristique;
import jeu.maitre.Carte;
import jeu.maitre.Monstre;
import jeu.utils.Input;
import jeu.utils.Output;

import java.lang.reflect.MalformedParameterizedTypeException;
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


      System.out.println("----------------------\nChoisissez une action:\n[0] Ajouter un monstre\n[1] Supprimer un monstre\n[2] Modifier un monstre\n[3] Continuer");
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
          modifMonstre(listMonstres);
          break;
        case 3:
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
    // variables
    String nomMstr = "";
    String attMstr[];
    String icone = "";
    Map<Caracteristique, Integer> caraMstr = new HashMap<>();
    {
      caraMstr.put(Caracteristique.PV, 0);
      caraMstr.put(Caracteristique.DEXTERITE, 0);
      caraMstr.put(Caracteristique.VITESSE, 0);
      caraMstr.put(Caracteristique.INITIATIVE, 0);
      caraMstr.put(Caracteristique.FORCE, 0);
      caraMstr.put(Caracteristique.CLASSE_ARMURE, 0);
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

    boolean inIconEditing = true;
    while (inIconEditing)
    {
      Output.print("Entrez une nouvelle icone (2 caractères total OBLIGATIOIRE) : ");
      String userString = Input.string();
      if (userString.length() == 2)
      {
        icone = userString;
        inIconEditing = false;
      }
      else
      {
        Output.print("[!] Veuillez mettre 2 caractères au total!\n");
      }
    }

    // setup son attaque
    attMstr = editMonstreAttaque();


    // setup les caractéristiques
    Output.print("--- Caractéristiques ---\n");
    for (Map.Entry<Caracteristique, Integer> cara : caraMstr.entrySet())
    {
      Output.print("Entrez une valeur pour la caractéristique de " + cara.getKey() + " : ");
      int userInt = Input.entier();
      caraMstr.replace(cara.getKey(), userInt);
    }

    Monstre monstreCree = new Monstre(nomMstr, attMstr[0], attMstr[1], caraMstr, icone, 0);

    Output.print("Voici le monstre crée : \n" + monstreCree);

    System.out.println("Le monstre " + monstreCree.getEspece() + " est ajouté dans la liste des monstres!");
    return monstreCree;
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
        System.out.println("[" + i + "] - " + listMonstres.get(i).getEspece());
      }
      System.out.println("[" + listMonstres.size() + "] - Revenir à l'éditeur de monstre");
      int userInt = jeu.utils.Input.entier();
      if (userInt == listMonstres.size())
      {
        return;
      }
      else if (userInt < listMonstres.size())
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

  private static void modifMonstre(ArrayList<Monstre> listMonstre)
  {
    if (listMonstre.isEmpty())
    {
      Output.print("Vous n'avez pas crée de monstre pour l'instant!");
      return;
    }

    Output.print("--- Modification Monstre ---\nQuel monstre voulez-vous modifier?\n");
    for (Monstre mstr : listMonstre)
    {
      Output.print("[" + listMonstre.indexOf(mstr) + "]" + mstr.getEspece());
    }
    int userInt = Input.entier();
    if (userInt < listMonstre.size())
    {
      boolean inMstrEditing = true;
      Monstre mstrChoisi = listMonstre.get(userInt);
      Monstre mstrNonSauvegarde = mstrChoisi.copie();

      while (inMstrEditing)
      {
        Output.print("Vous éditez " + mstrChoisi.getEspece() +
                "\n[0] Changer son nom" +
                "\n[1] Changer son icone" +
                "\n[2] Changer son attaque" +
                "\n[3] Changer ses caractéristiques" +
                "\n[4] Voir le monstre" +
                "\n[5] Sauvegarder et revenir à l'éditeur de monstre" +
                "\n[6] Revenir à l'éditeur de monstre sans sauvegarder");
        int userInt2 = Input.entier();
        switch (userInt2)
        {
          case 0:
            boolean chooseName = true;
            while (chooseName)
            {
              System.out.println("Donnez un nom à l'espèce du monstre");
              String nomMstr = Input.string();
              if (nomMstr != "")
              {
                chooseName = false;
                mstrChoisi.setEspece(nomMstr);
              }
              else
              {
                System.out.println("[!] Veuillez écrire un nom pour l'espèce du monstre!\n");
              }
            }
            break;
          case 1:
            boolean inIconEditing = true;
            while (inIconEditing)
            {
              Output.print("Entrez une nouvelle icone (2 caractères total OBLIGATIOIRE) : ");
              String userString = Input.string();
              if (userString.length() == 2)
              {
                mstrChoisi.setAlias(userString);
                inIconEditing = false;
              }
              else
              {
                Output.print("[!] Veuillez mettre 2 caractères au total!\n");
              }
            }
            break;
          case 2:
            String[] attInfo = editMonstreAttaque();
            mstrChoisi.setNomAtt(attInfo[0]);
            mstrChoisi.setSpeAtt(attInfo[1]);
            break;
          case 3:
            editMonstreCaracteristiques(mstrChoisi);
            break;
          case 4:
            Output.print(mstrChoisi.toString());
            break;
          case 5:
            Output.println("Le monstre " + mstrChoisi.getEspece() + " à été sauvegardé avec succès!");
            inMstrEditing = false;
            break;
          case 6:
            Output.print("Êtes-vous sûr.e de quitter sans sauvegarder? (toutes vos modifications seront perdues!)" +
                    "\n[0] Oui" +
                    "\n[1] Non");
            int userDecision = Input.entier();
            switch (userDecision)
            {
              case 0:
                listMonstre.set(userInt, mstrNonSauvegarde);
                Output.print("Le monstre " + mstrNonSauvegarde.getEspece() + " à été rétabli par défaut");
                inMstrEditing = false;
                break;
              case 1:
                break;
              default:
                Output.print("Vous n'avez pas entré une commande valide, retour en arrière");
                break;
            }
            break;
          default:
            Output.print("Veuillez entrer une commande valide!");
            break;
        }
      }
    }
  }

  private static String[] editMonstreAttaque()
  {
    String attMstr = "";
    String speAttMstr = "";

    // créer une attaque
    boolean inAttEditor = true;
    while (inAttEditor)
    {
      System.out.println("Donnez un nom à l'attaque du monstre");
      attMstr = Input.string();

      System.out.println("Combien de dés devra-t'il jeter?");
      speAttMstr += Input.entier();
      speAttMstr += "d";
      System.out.println("Quelle sera la valeur de son/ses dé(s)?");
      speAttMstr += Input.entier();

      System.out.println("----- Caractéristiques de l'attaque " + attMstr + " -----");
      System.out.println(speAttMstr);
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

    return new String[]{attMstr, speAttMstr};
  }

  private static void editMonstreCaracteristiques(Monstre mstrEditer)
  {
    Map<Caracteristique, Integer> caraMstr = mstrEditer.getMapCaracteristiques();

    // set les caractéristiques du monstre
    boolean inCaraEditor = true;
    while (inCaraEditor)
    {
      // pour avoir chaque valeur des caractéristiques dans une clé
      List<Caracteristique> keys = new ArrayList<>(caraMstr.keySet());
      System.out.println("Choisir les caractéristiques du monstre : ");

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
      else
      {
        System.out.print("Entrez une nouvelle valeur pour cette caracteristique: ");
        userInt2 = jeu.utils.Input.entier();
        caraMstr.replace(keys.get(userInt1-1), userInt2);
      }
    }
    return;
  }
}
