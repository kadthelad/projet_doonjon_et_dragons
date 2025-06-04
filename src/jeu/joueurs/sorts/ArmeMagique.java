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
    Output.print("De quel Personnage souhaitez-vous améliorer une arme.");
    int n = 0;
    for (Personnage p : Jeu.getListePersonnages()) {
      Output.print("[" + n + "] " + p.getNom());
      n++;
    }

    Personnage p = Jeu.getListePersonnages().get(Input.entier());
    List<Arme> listeArme = new ArrayList<>();
    for (Arme a : p.getListeArmes()) {
      if (a instanceof Arme) {
        listeArme.add((Arme) a);
      }
    }

    for (int i = 0; i < listeArme.size(); i++) {
      Output.print("[" + i + "] " + listeArme.get(i));
    }
    p.getListeArmes().get(Input.entier()).ameliore();
  }
}
