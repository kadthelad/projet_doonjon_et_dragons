package personnages.sorts;

import entite.Personnage;
import utils.Output;
import utils.Position;

public class BoogieWoogie implements Sort {
  public String getNom() {
    return "Boogie Woogie";
  }

  public void lancer() {}
  public void lancer(Personnage lanceur, Personnage cible1, Personnage cible2) {
      Position posA = cible1.getPosition();
      Position posB = cible2.getPosition();
      Position tmp = new Position(-1, -1);

      cible1.TP(tmp);
      cible2.TP(posA);
      cible1.TP(posB);
      Output.print("Les positions de " + cible1.getNom() + " et " + cible2.getNom() + " ont étées échangées par " + lanceur.getNom());
    }
}
