package joueurs.classes;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Guerrier
 */
public class Guerrier implements Classe {

  private int m_pv = 20;

  private ArrayList<Equipement> = new ArrayList<Equipement>(
    Arrays.asList();
  );

  public String presentation() {
    // BAAAAD, replace with iteration hover list
    return "Guerrier : \n\t- " + m_pv
        + " pv\n\t- équipement :\n\t\t- cotte de maille\n\t\t- épée longue\n\t\t- arbalète légère";
  }
}
