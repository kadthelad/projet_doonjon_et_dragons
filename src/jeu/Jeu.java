package jeu;

import jdk.jshell.execution.JdiDefaultExecutionControl;
import jeu.joueurs.*;
import jeu.joueurs.races.*;
import jeu.joueurs.classes.*;
import jeu.joueurs.equipements.*;
import jeu.utils.*;

import jeu.maitre.*;

import javax.sql.rowset.serial.SQLInputImpl;
import java.awt.print.Paper;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Jeu {

  // Liste des personnages, pour pouvoir facilement les utiliser partout
  private static ArrayList<Personnage> listePersonnages = new ArrayList<>();

  public static ArrayList<Personnage> getListePersonnages() {
    return listePersonnages;
  }

  // liste pour facilement énumerer races/classes lors de la création des persos
  private static ArrayList<Race> listeRaces = new ArrayList<>(
      Arrays.asList(new Nain()));
  // on ne peut pas appeler de methodes en dehors d'un bloc, donc pas de liste.add
  // directement dans la classe Main, donc Arrays.asList()

  private static ArrayList<Classe> listeClasses = new ArrayList<Classe>(
      Arrays.asList(new Clerc(), new Guerrier(), new Magicien(), new Roublard()));

  static private ArrayList<String> listeCaracteristiques = new ArrayList<>(
      Arrays.asList("force", "dexterite", "vitesse", "initiative"));

  static public ArrayList<String> getListeCaracteristiques() {
    return listeCaracteristiques;
  }

  // Liste des cartes (il faudra probablement naviguer sur plusieurs cartes dans l'étape 2, donc je prévois le coup)
  private static Dictionary<String, Carte> dictCartes = new Hashtable<String, Carte>() {};

  // Liste des races de monstre
  private static ArrayList<String> listRacesMonstre = new ArrayList<String>() {};

  // Liste des monstres crées
  private static ArrayList<Monstre> listMonstres = new ArrayList<Monstre>() {};

  // Fonction principale, qui appelle les differentes methodes, doit rester le
  // plus simple possible
  public static void main(String args[]) {
    //creationPersonnages(combienJoueurs());
    //creationCarte();
    //ajoutCarte();
    creationMonstres();
  }

  public static int combienJoueurs() {
    System.out.println("Sans compter le maitre du jeu, combien de joueurs y aura-t-il ?");
    int n = Input.entier();
    while (n < 0) {
      System.out.print("Merci d'entrer un nombre entier positif : ");
      n = Input.entier();
    }
    return n;
  }

  public static int ajoutCarte()
  {
    Carte cartecree = creationCarte();
    dictCartes.put(cartecree.getName(), cartecree);
    return 1;
  }
  public static int creationPersonnages(int nombreJoueurs) {

    // pour chaque joueur...
    for (int i = 0; i < nombreJoueurs; i++) {
      String nom = demanderNomStr("Joueur " + (i + 1) + ", nommez votre personnage");


      System.out.println(nom + ", entrer le numéro associé à la race de votre choix :");
      for (int j = 0; j < listeRaces.size(); j++) {
        // on affiche les informations de chaque race
        System.out.print(j + " : " + listeRaces.get(j).presentation());
      }
      int race = Input.entier();

      System.out.println(nom + ", entrer le numéro associé à la classe de votre choix :");
      for (int j = 0; j < listeClasses.size(); j++) {
        // informations pour les classes
        System.out.print(j + " : " + listeClasses.get(j).presentation() + "\n");
      }
      int classe = Input.entier();

      // on crée le personnage en fonction des parametre choisis
      listePersonnages.add(new Personnage(nom, listeRaces.get(race), listeClasses.get(classe)));
      Personnage p = listePersonnages.get(i);
      System.out.println(p.toString());
    }

    return 1;
  }

  public static int creationMonstres()
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
      userInput = Input.entier();
      switch (userInput)
      {
        case 0:
          listMonstres.add(ajoutMonstre());
          break;
        case 1:
          supprMonstre();
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
    String nomMstr;
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
    nomMstr = demanderNomStr("Nommez votre monstre (ce sera le nom de sa race)");

    // créer une attaque
    boolean inAttEditor = true;
    while (inAttEditor)
    {
      attMstr = demanderNomStr("Nommez le nom de son attaque");

      System.out.println("Combien de dégâts fera votre attaque?");
      speAttMstr[0] = Input.entier();
      System.out.println("Combien de dés devra-t'il jeter?");
      speAttMstr[1] = Input.entier();
      System.out.println("Quelle sera la valeur de son/ses dé(s)?");
      speAttMstr[2] = Input.entier();

      System.out.println("----- Caractéristiques de l'attaque " + attMstr + " -----");
      System.out.println(speAttMstr[0] + " dégàt(s), " + speAttMstr[1]+"d"+speAttMstr[2]);
      System.out.println("--------------\nVoulez vous garder cette attaque?\n[0] Oui, continuer\n[1] Non, refaire");
      int userInt = Input.entier();
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
      int userInt1 = Input.entier();
      int userInt2;
      if (userInt1 < 0 || userInt1>caraMstr.size())
      {
        inCaraEditor = false;
      }
      else if (userInt1 == 0)
      {
        System.out.print("Entrez une nouvelle valeur pour cette caracteristique: ");
        userInt2 = Input.entier();
        pdv = userInt2;
      }
      else
      {
        System.out.print("Entrez une nouvelle valeur pour cette caracteristique: ");
        userInt2 = Input.entier();
        caraMstr.replace(keys.get(userInt1-1), userInt2);
      }
    }

    System.out.println("Le monstre " + nomMstr + " est ajouté dans la liste des monstres!");
    return new Monstre(nomMstr, attMstr, speAttMstr, pdv, caraMstr);
  }

  private static void supprMonstre()
  {
    boolean inDeleting = true;

    while (inDeleting)
    {
      if (listMonstres.isEmpty())
      {
        System.out.println("[!] Il n'y a pas de monstres crée!");
        return;
      }
      // supprimer un monstre
      System.out.println("-- Quel monstre voulez-vous supprimer? --");
      for (int i = 0; i < listMonstres.size(); i++)
      {
        System.out.println("[" + (i) + "] - " + listMonstres.get(i).getEspece());
      }
      System.out.println("[" + listMonstres.size() + "] - Revenir à l'éditeur de monstre");
      int userInt = Input.entier();
      if (userInt == listMonstres.size() + 1)
      {
        return;
      }
      else if (userInt <= listMonstres.size())
      {
        System.out.println("[!] Monstre " + listMonstres.get(userInt).getEspece() + " supprimé!");
        listMonstres.remove(userInt);
      }
      else
      {
        System.out.println("Entrez une commande existante!");
      }
    }
  }

  public static Carte creationCarte()
  {
    int userInput;
    String userStrInput;

    // Création de la carte //
    System.out.println("| =-=-=-=-= | EDITEUR DE CARTE | =-=-=-=-= |");
    System.out.println("Quelle sera la taille de la carte?");
    int mapTaille = Input.entier();
    Carte carteCree = new Carte(mapTaille);
    boolean inMapEditor = true;

    while (inMapEditor)
    {
      carteCree.afficherCarte();
      System.out.println("----------------------\nChoisissez une action:\n[0] Ajouter un obstacle\n[1] Supprimer un obstacle\n[2] Continuer");
      userInput = Input.entier();
      switch (userInput)
      {
        case 0:
          ajoutObstacle(carteCree);
          break;

        case 1:
          supprObstacle(carteCree);
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
                  carteCree.setName("Carte sans nom" + dictCartes.size());
                }
                else
                {
                  carteCree.setName(userStrInput);
                }
                confirmExit = false;
                inMapEditor = false;
                break;

              case 1:
                confirmExit = false;
                break;

              case 2:
                carteCree.afficherCarte();
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
    return carteCree;
  }

  private static void ajoutObstacle(Carte carteCree)
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

          if (posx <= carteCree.getTaille() && posy <= carteCree.getTaille()) {
            if (!carteCree.obstacleAt(new Position(posx, posy))) {
              carteCree.addObstacle(new Position(posx, posy));
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

  private static void supprObstacle(Carte carteCree)
  {
    String userStrInput;
    String[] result;

    if (!carteCree.getObstacles().isEmpty()) {
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
            if (posx <= carteCree.getTaille() && posy <= carteCree.getTaille()) {
              if (carteCree.obstacleAt(new Position(posx, posy))) {
                carteCree.removeObstacle(new Position(posx, posy));
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


  private static String demanderNomStr(String raison)
  {
    Pattern pattern = Pattern.compile("([a-z]+([ -][a-z]+)?)+", Pattern.CASE_INSENSITIVE);
    System.out.println(raison);
    String nom = Input.string();
    Matcher matcher = pattern.matcher(nom);
    while (!matcher.matches()) {
      System.out.println("Votre nom ne doit contenir que des lettres et eventuellement un \" \"" +
              " ou un \"-\" au milieu.");
      nom = Input.string();
      matcher = pattern.matcher(nom);
    }

    return nom;
  }
}