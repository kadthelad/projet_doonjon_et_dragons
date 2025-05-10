package joueurs.classes;

/**
 * Roublard
 */
public class Roublard implements Classe {

  private int m_pv = 16;

  public String presentation() {
    return "Roublard : \n\t- " + m_pv
        + "\n\t- équipement :\n\t\t- rapière\n\t\t- arc court";
  }
}
