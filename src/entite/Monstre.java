package entite;

import jeu.Carte;
import jeu.Jeu;
import utils.Des;
import utils.Input;
import utils.Output;
import utils.Position;

import java.util.*;

public class Monstre extends Creature {
  private String espece = "";
  private int numero = 0; // si il existe plusieurs monstres de la meme espèce
  private static int nbOrc = 0;
  private static int nbDragon = 0;
  private static int nbArrachnide = 0;

  private String nomAttaque = "";
  private String des = "";

  private Position position = new Position(-1, -1);

  private String alias;

  {
    caracteristiques.put(Caracteristique.CLASSE_ARMURE, 0);
  }

  public Monstre(String esp, String nAtt, String att, Map<Caracteristique, Integer> mapCara, String alias, int numero) {
    this.espece = esp;
    this.nomAttaque = nAtt;
    this.des = att;
    this.caracteristiques = mapCara;
    this.alias = alias;
    this.numero = numero;
  }

  public Monstre copie() {
    Monstre m = new Monstre(this.espece, this.nomAttaque, this.des, this.caracteristiques, this.alias, this.numero);
    this.numero++;
    return m;
  }

  public static Monstre random() {
    Random rnd = new Random();
    Monstre m = orc();
    switch (rnd.nextInt(3)) {
      case 0 -> m = orc();
      case 1 -> m = dragon();
      case 2 -> m = arrachnide();
    }
    return m;
  }

  public static Entite create() {
    ArrayList<Monstre> listMonstres = Jeu.getListeMonstres();
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
        System.out.println("Vous avez déjà ce(s) monstre(s):");
        for (int i=0; i<listMonstres.size(); i++)
        {
          System.out.println(listMonstres.get(i).getEspece());
        }
      }


      System.out.println("----------------------\nChoisissez une action:\n[0] Ajouter un monstre\n[1] Supprimer un monstre\n[2] Modifier un monstre\n[3] Choisir le monstre et continuer la création de carte");
      userInput = Input.entier();
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
          int nombreMonstres = Jeu.getNombreMonstres();
          Output.println("Voici la liste des monstres : ");
          for (int i = 1; i <= nombreMonstres; i++) {
            Output.println("[" + i + "] " + Jeu.getMonstre(i - 1).getNom());
          }
          Output.println("[0] Monstre au hasard");
          int n = Input.entier(0, nombreMonstres, "Quel monstre souhaitez-vous placer ?");
          Entite e = (n != 0) ? Jeu.getMonstre(n - 1).copie() : Monstre.random();
          return e;
        default:
          Output.printValid();
          break;
      }
    }
    return Monstre.random();
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
      int userInt = utils.Input.entier();
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
      int userInt = utils.Input.entier();
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
      int userInt1 = utils.Input.entier();
      int userInt2;
      if (userInt1 < 0 || userInt1>caraMstr.size())
      {
        inCaraEditor = false;
      }
      else
      {
        System.out.print("Entrez une nouvelle valeur pour cette caracteristique: ");
        userInt2 = utils.Input.entier();
        caraMstr.replace(keys.get(userInt1-1), userInt2);
      }
    }
  }

  public static Monstre orc() {
    Map<Caracteristique, Integer> mapCara = new HashMap<>();
    mapCara.put(Caracteristique.PV, 10);
    mapCara.put(Caracteristique.FORCE, 5);
    mapCara.put(Caracteristique.INITIATIVE, 2);
    mapCara.put(Caracteristique.CLASSE_ARMURE, 13);
    mapCara.put(Caracteristique.VITESSE, 3);
    mapCara.put(Caracteristique.DEXTERITE, 0);
    int num = nbOrc;
    nbOrc++;
    String alias = (num < 10) ? "or" : "o";
    return new Monstre("Orc", "Yolo", "1d8", mapCara, alias, num);
  }

  public static Monstre dragon() {
    Map<Caracteristique, Integer> mapCara = new HashMap<>();
    mapCara.put(Caracteristique.PV, 10);
    mapCara.put(Caracteristique.FORCE, 0);
    mapCara.put(Caracteristique.INITIATIVE, 0);
    mapCara.put(Caracteristique.CLASSE_ARMURE, 10);
    mapCara.put(Caracteristique.VITESSE, 0);
    mapCara.put(Caracteristique.DEXTERITE, 20);
    int num = nbDragon;
    nbDragon++;
    String alias = (num < 10) ? "dr" : "d";
    return new Monstre("Dragon", "Crache-Flamme", "3d6", mapCara, alias, num);
  }

  public static Monstre arrachnide() {
    Map<Caracteristique, Integer> mapCara = new HashMap<>();
    mapCara.put(Caracteristique.PV, 5);
    mapCara.put(Caracteristique.FORCE, 15);
    mapCara.put(Caracteristique.INITIATIVE, 20);
    mapCara.put(Caracteristique.CLASSE_ARMURE, 0);
    mapCara.put(Caracteristique.VITESSE, 15);
    mapCara.put(Caracteristique.DEXTERITE, 0);
    int num = nbArrachnide;
    nbArrachnide++;
    String alias = (num < 10) ? "ar" : "a";
    return new Monstre("Arrachnide", "ksss", "2d6", mapCara, alias, num);
  }

  public Monstre(String nom)
  {
    switch (nom)
    {
      case "Orc":
        createOrc();
        break;
      case "Dragon":
        createDragon();
        break;
      case "Araignée":
        createAraignee();
        break;
      default:
        Output.println("[!!!] Il n'y a pas de monstre préfait à ce nom! Ce monstre n'aura aucune valeur définie!");
        break;
    }
  }

  private void createOrc()
  {
    this.espece = "Orc";
    this.nomAttaque = "Coup de pied";
    this.des = "2d3";
    this.alias = "DC";

    this.setCaracteristique(Caracteristique.PV_MAX, 20);
    this.setCaracteristique(Caracteristique.PV, 20);
    this.setCaracteristique(Caracteristique.FORCE, 3);
    this.setCaracteristique(Caracteristique.INITIATIVE, 2);
    this.setCaracteristique(Caracteristique.CLASSE_ARMURE, 10);
    this.setCaracteristique(Caracteristique.VITESSE, 3);
    this.setCaracteristique(Caracteristique.DEXTERITE, 4);
  }

  private void createDragon()
  {
    this.espece = "Dragon";
    this.nomAttaque = "Boule de feu";
    this.des = "3d4";
    this.alias = "dg";

    this.setCaracteristique(Caracteristique.PV_MAX, 15);
    this.setCaracteristique(Caracteristique.PV, 15);
    this.setCaracteristique(Caracteristique.FORCE, 2);
    this.setCaracteristique(Caracteristique.INITIATIVE, 4);
    this.setCaracteristique(Caracteristique.CLASSE_ARMURE, 7);
    this.setCaracteristique(Caracteristique.VITESSE, 5);
    this.setCaracteristique(Caracteristique.DEXTERITE, 6);
  }

  private void createAraignee()
  {
    this.espece = "Araignée";
    this.nomAttaque = "Morsure";
    this.des = "1d6";
    this.alias = "DC";

    this.setCaracteristique(Caracteristique.PV_MAX, 10);
    this.setCaracteristique(Caracteristique.PV, 10);
    this.setCaracteristique(Caracteristique.FORCE, 5);
    this.setCaracteristique(Caracteristique.INITIATIVE, 8);
    this.setCaracteristique(Caracteristique.CLASSE_ARMURE, 2);
    this.setCaracteristique(Caracteristique.VITESSE, 5);
    this.setCaracteristique(Caracteristique.DEXTERITE, 7);
  }

  public String getEspece() {
    /// Avoir l'espèce d'un Monstre ///
    return this.espece;
  }

  public String getNomAttaque() {
    return this.nomAttaque;
  }

  public String getAttaque() {
    return this.des;
  }

  public Map<Caracteristique, Integer> getMapCaracteristiques() {
    return this.caracteristiques;
  }

  public void setEspece(String esp) {
    this.espece = esp;
  }

  public void setAlias(String icn) {
    this.alias = icn;
  }

  public void setNomAtt(String nomAtt) {
    this.nomAttaque = nomAtt;
  }

  public void setSpeAtt(String speAtt) {
    this.des = speAtt;
  }

  public Position getPosition() {
    return this.position;
  }


  @Override
  public boolean attaque(Creature cible) {
    int d = distance(cible.getPosition());
    if (d > 1) {
      Output.print("La cible est trop loin (portée : " + 1 + " < distance :" + d + ")");
      return false;
    }
    int jet = Des.lancer(this.des);

    if (jet < cible.getCA()) {
      Output.print("Votre des ne touche pas ( jet + caracteristique : " + jet + " < classe d'amure de la cible :"
              + cible.getCA() + ")");
      return false;
    }

    cible.changePV((jet+this.getCaracteristique(Caracteristique.FORCE)) * -1);

    Output.print("Le monstre à attaqué avec  " + this.nomAttaque + " et à infligé " + jet + " degats à " + cible.getNom() + "!");
    return true;
  }

  public int getCA() {
    return this.caracteristiques.get(Caracteristique.CLASSE_ARMURE);
  }

  public String getNom() {
    return this.espece + this.numero;
  }

  @Override
  /// String override ///
  public String toString() {
    return "Monstre : " + this.espece +
        " (" + getAlias() + ")" +
        "\nAttaque : " + des +
        "\n___Caractéristiques___\n" +
        this.caracteristiques.get(Caracteristique.PV) + " PV\n" +
        this.caracteristiques.get(Caracteristique.FORCE) + " Force\n" +
        this.caracteristiques.get(Caracteristique.DEXTERITE) + " Déxterité\n" +
        this.caracteristiques.get(Caracteristique.VITESSE) + " Vitesse\n" +
        this.caracteristiques.get(Caracteristique.INITIATIVE) + " Initiative\n" +
        this.caracteristiques.get(Caracteristique.CLASSE_ARMURE) + " Classe d'Armure\n";
  }

  public void changePV(int pv) {
    this.caracteristiques.replace(Caracteristique.PV, this.caracteristiques.get(Caracteristique.PV) + pv);
  }

  @Override
  public String getAlias() {
    return this.alias + this.numero;
  }

  public void setCaracteristique(Caracteristique c, int v) {
    this.caracteristiques.put(c, v);
  }

  public int getCaracteristique(Caracteristique c) {
    return this.caracteristiques.get(c);
  }

  public void changeCaracteristiqueMap(Map<Caracteristique, Integer> map) {
    this.caracteristiques = map;
  }

  public boolean equipperArme()
  {
    Output.println("Cette créature ne peut pas équipper d'arme !");
    return false;
  }

  public boolean equipperArmure()
  {
    Output.println("Cette créature ne peut pas équipper d'armure !");
    return false;
  }

  @Override
  public boolean ramasser()
  {
    Output.println("Cette créature ne peut pas équipper d'armure !");
    return false;
  }
}
