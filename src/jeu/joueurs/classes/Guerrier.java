import jeu.joueurs.classes;

import java.util.ArrayList;
import java.util.Arrays;

import jeu.joueurs.equipements.Equipement;
import jeu.joueurs.equipements.armes.*;

/**
 * Guerrier
 */
public class Guerrier implements Classe {

  private int m_pv = 20;
  private String m_nom = "Guerrier";

  private ArrayList<Equipement> listeEquipement = new ArrayList<Equipement>(
      Arrays.asList(new Baton()));

  // presentation de la classe, pv et armes
  public String presentation() {
    String pres = m_nom += "- pv : " + m_pv + "\n";
    for (int i = 0; i < listeEquipement.size(); i++) {
      pres += "  - " + listeEquipement.get(i).presentation() + "\n";
    }
    return pres;
  }
}
