package personnages.sorts;

import jeu.Jeu;
import entite.Personnage;
import utils.*;

public class Guerison implements Sort {
  public String getNom() {
    return "Guérison";
  }

  public void lancer() {

    Output.print("Quel.le personnage souhaitez-vous soigner");
    int n = 0;

    for (int i = 1; i <= Jeu.getNombrePersonnages(); i++) {
      Output.print("[" + i + "] " + jeu.Jeu.getPersonnage(i - 1).getNom());
    }

    Personnage p = Jeu.getPersonnage(Input.entier(0, Jeu.getNombreMonstres()));
    int soin = Des.lancer(1, 10);
    p.changePV(soin);

    Output.println(p.getNom() + " récupère " + soin + " PV !");
  }
}
