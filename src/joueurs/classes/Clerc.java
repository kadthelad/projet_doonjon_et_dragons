package joueurs.classes;

/**
 * Clerc
 */
public class Clerc implements Classe {

  private int m_pv = 16;

  public String presentation() {
    return "Clerc : \n\t- " + m_pv
        + "\n\t- équipement :\n\t\t- masse d'arme\n\t\t- armure d'écaille\n\t\t- arbalète légère";
  }
}
