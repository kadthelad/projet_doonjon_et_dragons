package personnages.sorts;

import jeu.Jeu;
import entite.Personnage;
import utils.Output;
import utils.Input;

public class ArmeMagique implements Sort {
  public String getNom() {
    return "Arme Magique";
  }

  public void lancer() {
    while (true) {
      Output.print("De quel Personnage souhaitez-vous améliorer une arme.");
      for (int i = 1; i <= jeu.Jeu.getNombrePersonnages(); i++) {
        Output.print("[" + i + "] " + jeu.Jeu.getPersonnage(i - 1).getNom());
      }

      Personnage p = jeu.Jeu.getPersonnage(Input.entier(1, Jeu.getNombrePersonnages()));

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
