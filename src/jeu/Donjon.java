package jeu;

import entite.Caracteristique;
import entite.Creature;
import utils.Des;
import utils.Input;
import utils.Output;

import java.util.ArrayList;

public class Donjon {
  // La classe resources.Donjon va gérer le donjon en cours
  // Elle passera les tours et gérera les opérations (combat, déplacement, autres actions...)

  private static Carte carte;
  public static Carte getCarte() { return carte; }
  public static void setCarte(Carte c) { carte = c; }


  private int tour = 0;
  private ArrayList<Creature> listeOrdreJeu = new ArrayList<Creature>() {};

  public Donjon() {
    carte = Carte.carteAleatoire();
  }

  public void commenter()
  {
    Output.println("[Maitre du Jeu] Ajouter un commentaire?\n" +
            "[0] oui" +
            "[1] non");
    int userInt = Input.entier();
    if (userInt == 0)
    {
      Output.println("Votre commentaire : ");
      String userStr = Input.string();
      Output.print("-Commentaire- : " + userStr);
    }
  }

  // Méthode permettant au maitre du jeu de créer la carte,
  public void lancerJeu() {

    // Création (aléatoire ou manuelle) de la carte
    Carte.menuCarte();

    boolean jeu = true;
    listeOrdreJeu = new ArrayList<>();
    ArrayList<Creature> listEveryone = new ArrayList<>() {};
    listEveryone.addAll(Jeu.getListePersonnages());
    listEveryone.addAll(carte.getListeMonstresEnJeu());

    while (jeu) {
      Output.print("[Tour : " + this.tour + "]\n");

      if (this.tour == 0)
      {
        premierTour(listEveryone);
        tour++;
      }

      for (Creature crea : listeOrdreJeu)
      {
        actionCreature(crea);
      }

      this.tour++;
    }
  }

  private void premierTour(ArrayList<Creature> listEveryone)
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
      Output.println(i + " : " + this.listeOrdreJeu.get(i).getNom());
    }

    // proposer aux joueurs d'équiper une arme et/ou une armure
    for (int i = 0; i < Jeu.getNombrePersonnages(); i++) {
      Jeu.getPersonnage(i).equipperArme();
      Jeu.getPersonnage(i).equipperArmure();
    }
  }

  private void ajouterDansListeOrdre(Creature creatureAjoutee)
  {
    int initiativeAjoutee = creatureAjoutee.getCaracteristique(Caracteristique.INITIATIVE);

    for (int i = 0; i < listeOrdreJeu.size(); i++)
    {
      Creature crea = listeOrdreJeu.get(i);
      int initiativeExistante = crea.getCaracteristique(Caracteristique.INITIATIVE);

      if (initiativeAjoutee > initiativeExistante)
      {
        listeOrdreJeu.add(i, creatureAjoutee);
        return;
      }
    }
    listeOrdreJeu.add(creatureAjoutee);
  }

  private boolean deplacer(Creature crea)
  {
    Output.println("Où voulez-vous déplacer " + crea.getNom() + "? ==== (Vitesse / 3 = " + crea.getCaracteristique(Caracteristique.VITESSE)/3 + ")");
    if (crea.move(Carte.choixCase(carte)))
    {
      Output.println(crea.getNom() + " bouge avec succès!");
      return true;
    }
    else
    {
      Output.println("[!] Faites attention :\n" +
              "- Vous pouvez n'avancer que de " + crea.getCaracteristique(Caracteristique.VITESSE)/3 + " cases!\n" +
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

    while (pointsAction > 0)
    {
      carte.afficherCarte();
      Output.print(
              "======== [Tour : " + this.tour + "] ========\n" +
                      "|Au tour de " + crea.getNom() + " (" + pointsAction + " Points d'Action) :\n" +
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
          if (crea.equipperArme())
          {
            pointsAction--;
            commenter();
          }
          if (crea.equipperArmure())
          {
            pointsAction--;
            commenter();
          }
          break;
        case 1: // se deplacer
          if (deplacer(crea))
          {
            pointsAction--;
            commenter();
          }
          break;
        case 2: // attaquer
          if (attaquer(crea))
          {
            pointsAction--;
            commenter();
          }
          break;
        case 3: // ramasser objet
          if (crea.ramasser())
          {
            pointsAction--;
            commenter();
          }
          break;
        case 4: // passer tour
          pointsAction = 0;
          break;
        default:
          Output.printValid();
      }
    }
  }
}
