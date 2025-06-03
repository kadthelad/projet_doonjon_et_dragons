package jeu.joueurs.sorts;

import jeu.Jeu;
import jeu.joueurs.Personnage;
import jeu.utils.*;

public class Guerison implements Sort {
  public String getNom() {
    return "Guérison";
  }

  public void lancer() {

    Output.print("Quel.le personnage souhaitez-vous soigner");
    int n = 0;
    
    for (Personnage p : Jeu.getListePersonnages()) {
      Output.print("[" + n + "] " + p.getNom());
      n++;
    }

    int input = -1;
    do {
      input = Input.entier();
    } while (input < n && input >= 0);

    Personnage p = Jeu.getListePersonnages().get(n);
    int soin = Des.lancer(1, 10);
    p.changePV(soin);

    System.out.println(p.getNom() + " récupère " + soin + " PV !");
  }
}
