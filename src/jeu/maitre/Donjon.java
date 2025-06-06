package jeu.maitre;

import jeu.Caracteristique;
import jeu.Creature;
import jeu.Entite;
import jeu.Jeu;
import jeu.joueurs.equipement.Arme;
import jeu.joueurs.equipement.Catalogue;
import jeu.joueurs.Personnage;
import jeu.joueurs.equipement.Equipement;
import jeu.maitre.maitreJeu.Maitre;
import jeu.utils.Des;
import jeu.utils.Input;
import jeu.utils.Output;
import jeu.utils.Position;
import jeu.maitre.Carte;

import java.util.ArrayList;
import java.util.jar.JarOutputStream;

public class Donjon {
  // La classe Donjon va gérer le donjon en cours
  // Elle passera les tours et gérera les opérations (combat, déplacement, autres actions...)

  private Maitre mj = new Maitre();

  private Carte carte = Carte.menuCarte();
  private ArrayList<Personnage> personnages;
  private ArrayList<Monstre> monstres;

  private int tour = 0;
  private ArrayList<Creature> listeOrdreJeu = new ArrayList<Creature>() {};

  public Donjon(ArrayList<Personnage> listPersonnages, ArrayList<Monstre> listMonstres) {
    /// CONSTRUCTEUR DE LA CLASSE Donjon ///
    this.personnages = listPersonnages;
    this.monstres = listMonstres;
  }

  public void lancerJeu() {
    /// Méthode pour créer le jeu ///
    // Lors de l'appel de la méthode, le maitre du jeu devra créer la carte,
    /// l'emplacement des obstacles, des
    // personnages et des equipement
    boolean jeu = true;
    ArrayList<Creature> listEveryone = new ArrayList<Creature>() {};
    listEveryone.addAll(this.personnages);
    listEveryone.addAll(this.monstres);

    while (jeu) {
      Output.print("[Tour : " + this.tour + "]\n");

      // au demarrage du dojon tour, faire l'ordre de jeu
      if (this.tour == 0)
      {
        demarrageDonjon(listEveryone);
      }

      for (Creature crea : listEveryone)
      {
        actionCreature(crea);
      }


      this.tour++;
    }
  }

  private void demarrageDonjon(ArrayList<Creature> listEveryone)
  {
    // Setup l'ordre de jeu
    Output.print("Tous les joueurs et les monstres lancent un 1d20 et ajoutent sa valeur dans leur initiative!\n");
    for (Creature crea : listEveryone)
    {
      int deVal = Des.lancer();
      int val = crea.getCaracteristique(Caracteristique.INITIATIVE);
      Output.print(crea.getNom() + " (Initiative = " + val + ") aura une addition de + " + deVal + " points d'initiative!\n");
      crea.setCaracteristique(Caracteristique.INITIATIVE, val + deVal);
      ajouterDansListeOrdre(crea);
    }
    Output.print("Voici l'ordre de jeu : \n");
    for (int i=0; i<this.listeOrdreJeu.size(); i++)
    {
      Output.print(i + " : " + this.listeOrdreJeu.get(i).getNom());
    }

    // demander aux joueurs si ils veulent equipper une arme/armure
    for (Personnage joueur : this.personnages)
    {
      joueur.equipperArme();
      joueur.equipperArmure();
    }
  }

  private void ajouterDansListeOrdre(Creature creatureAjoutee)
  {
    for (Creature crea : this.listeOrdreJeu)
    {
     if (crea.getCaracteristique(Caracteristique.INITIATIVE) > creatureAjoutee.getCaracteristique(Caracteristique.INITIATIVE))
     {
       this.listeOrdreJeu.add(this.listeOrdreJeu.indexOf(crea), creatureAjoutee);
       return;
     }
    }
  }


  private boolean deplacer(Creature crea)
  {
    Output.println("Où voulez-vous déplacer " + crea.getNom() + "? ==== Vitesse = " + crea.getCaracteristique(Caracteristique.VITESSE));
    if (crea.move(Output.askPosition()))
    {
      Output.println(crea.getNom() + " bouge avec succès!s");
      return true;
    }
    else
    {
      Output.println("[!] Faites attention :\n" +
              "- Vous pouvez n'avancer que de " + crea.getCaracteristique(Caracteristique.VITESSE) + "cases!\n" +
              "- Soyez sur que vous êtes bien dans les bordures de la carte!\n" +
              "- Vous ne pouvez pas avancer dans une case qui a un obstacle ou une créature!");
      return false;
    }
  }

  private boolean attaquer(Creature crea)
  {
    Output.println(" === Attaquer ===\n" +
            "Qui voulez-vous attaquer?");

    int nbCibles = this.listeOrdreJeu.size();

    for (int i=0; i<nbCibles; i++)
    {
      Output.println("[" + i + "] " + this.listeOrdreJeu.get(i).getNom());
    }
    int userInt = Input.entier();

    if (userInt < nbCibles)
    {
      crea.attaque(this.listeOrdreJeu.get(userInt));
      return true;
    }
    else
    {
      Output.println("Vous n'attaquez personne.");
      return false;
    }
  }

  private void actionCreature(Creature crea)
  {
    int pointsAction = 3;

    carte.afficherCarte();
    Output.print(
            "========================\n" +
            "|Au tour de " + crea.getNom() + " :\n" +
            "|->[0] Equipper un objet\n" +
            "|->[1] Se déplacer\n" +
            "|->[2] Attaquer\n" +
            "|->[3] Ramasser un objet\n" +
            "|->[4] Passer son tour\n" +
            "========================\n"
    );
    int userVal = Input.entier();

    switch (userVal)
    {
      case 0: // equipper objet
        crea.equipperArme();
        crea.equipperArmure();
        mj.commenter();
        break;
      case 1: // se deplacer
        deplacer(crea);
        mj.commenter();
        break;
      case 2: // attaquer
        attaquer(crea);
        mj.commenter();
        break;
      case 3: // ramasser objet
        mj.commenter();
        break;
      case 4: // passer tour
        break;
      default:
        Output.printValid();
    }
  }
}
