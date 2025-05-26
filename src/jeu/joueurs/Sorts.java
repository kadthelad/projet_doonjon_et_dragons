package jeu.joueurs;

import jeu.joueurs.equipement.Arme;
import jeu.utils.Des;

public class Sorts {
  public static class Guerison implements Sort {
    public String getNom() {
      return "Guérison";
    }

    public void lancer(Personnage lanceur, Personnage cible) {
      int soin = Des.lancer(1, 10);
      cible.changePV(soin);
      System.out.println(cible.getNom() + " récupère " + soin + " PV !");
    }
  }

  public static class BoogieWoogie implements Sort {
    public String getNom() {
      return "Boogie Woogie";
    }

    public void lancer(Personnage lanceur, Personnage cible1, Personnage cible2) {
      var posA = cible1.getPosition();
      var posB = cible2.getPosition();
      cible1.TP(posB.getX(), posB.getY());
      cible2.TP(posA.getX(), posA.getY());
      System.out.println("Positions échangées entre " + cible1.getNom() + " et " + cible2.getNom());
    }
  }

  public static class ArmeMagique implements Sort {
    public String getNom() {
      return "Arme Magique";
    }

    public void lancer(Personnage lanceur, Arme arme) {
      System.out.println("L'arme " + arme.getNom() + " devient magique ! (+1 attaque, +1 dégâts)");
    }
  }
}
