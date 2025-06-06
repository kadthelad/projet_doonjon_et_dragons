package jeu.joueurs.sorts;

import jeu.Jeu;
import jeu.joueurs.Personnage;
import jeu.joueurs.equipement.Arme;
import jeu.utils.Output;
import jeu.utils.Input;
import java.util.ArrayList;
import java.util.List;

public class ArmeMagique implements Sort {
  public String getNom() {
    return "Arme Magique";
  }

  public void lancer() {
    while (true) {
      Output.print("De quel Personnage souhaitez-vous améliorer une arme.");
      for (int i = 1; i <= Jeu.getNombrePersonnages(); i++) {
        Output.print("[" + i + "] " + Jeu.getPersonnage(i - 1).getNom());
      }

      Personnage p = Jeu.getPersonnage(Input.entier(1, Jeu.getNombrePersonnages()));

      Output.println("[0] Choisir un autre personnage.");
      for (int i = 1; i < p.getNombreArme(); i++) {
        Output.println("[" + i + "] " + p.getArme(i).toString());
      }

      int n = Input.entier(0, p.getNombreArme(), "Quelle arme souhaitez-vous améliorer ?");
      if (n == 0) {
        return;
      } else {
        p.getArme(n);
      }
    }
  }
}
