package jeu;

import entite.*;
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
    if (Input.confirmer("[commenter] Ajouter un commentaire?"))
    {
      Output.println("Votre commentaire : ");
      Output.print("-Commentaire- : " + Input.string());
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
    for (Monstre mstr : carte.getListeMonstresEnJeu())
    {
      int deVal = Des.lancer();
      int val = mstr.getCaracteristique(Caracteristique.INITIATIVE);
      mstr.setCaracteristique(Caracteristique.INITIATIVE, val + deVal);
      ajouterDansListeOrdre(mstr);
    }
    for (Personnage pers : Jeu.getListePersonnages())
    {
      int deVal = Des.lancer();
      int val = pers.getCaracteristique(Caracteristique.INITIATIVE);
      Output.print(pers.getNom() + " (Initiative = " + val + ") aura une addition de + " + deVal + " points d'initiative!\n");
      pers.setCaracteristique(Caracteristique.INITIATIVE, val + deVal);
      ajouterDansListeOrdre(pers);
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


    if (crea.isMonstre())
    {
      int nbCibles = Jeu.getNombrePersonnages();

      for (int i=0; i<nbCibles; i++)
      {
        Output.println("[" + i + "] " + Jeu.getListePersonnages().get(i).getNom());
      }
      int userInt = Input.entier();

      if (userInt < nbCibles)
      {
        crea.attaque(Jeu.getListePersonnages().get(userInt));
        return true;
      }
      else
      {
        Output.println(crea.getNom() + " n'attaque personne.");
        return false;
      }
    }
    else
    {
      int nbCibles = carte.getListeMonstresEnJeu().size();

      for (int i=0; i<nbCibles; i++)
      {
        Output.println("[" + i + "] " + carte.getListeMonstresEnJeu().get(i).getNom());
      }
      int userInt = Input.entier();

      if (userInt < nbCibles)
      {
        crea.attaque(carte.getListeMonstresEnJeu().get(userInt));
        return true;
      }
      else
      {
        Output.println(crea.getNom() + " n'attaque personne.");
        return false;
      }
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
                      "|->[1] Equipper un objet\n" +
                      "|->[2] Se déplacer (" + crea.getCaracteristique(Caracteristique.VITESSE) / 3 + " cases)\n" +
                      "|->[3] Attaquer\n" +
                      "|->[4] Ramasser un objet\n" +
                      "|->[5] Voir statistiques\n" +
                      "|->[0] Passer son tour\n" +
                      "========================\n"
      );
      int choix = Input.entier(0, 5, "Choix : ");
      switch (choix)
      {
        case 1 ->  // equipper objet
        {
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
          Input.entreePourPasser();
        }
        case 2 -> // se deplacer
        {
          if (deplacer(crea))
          {
            pointsAction--;
            commenter();
          }
          Input.entreePourPasser();
        }
        case 3 -> // attaquer
        {
          if (attaquer(crea))
          {
            pointsAction--;
            commenter();
          }
          Input.entreePourPasser();
        }
        case 4 -> // ramasser
        {
          if (crea.ramasser())
          {
            pointsAction--;
            commenter();
          }
          Input.entreePourPasser();
        }
        case 5 ->
        {
          Output.println(crea.toString());
          Input.entreePourPasser();
        }
        case 0 -> pointsAction = 0;
      }
    }
  }
}
