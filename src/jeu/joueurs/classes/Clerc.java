package jeu.joueurs.classes;

/**
 * Clerc
 */
public class Clerc implements Classe {

  private String m_nom = "Clerc";

  public String getNom() {
    return m_nom;
  }

  private int m_pv = 16;

  public String presentation() {
    return "Clerc : \n\t- " + m_pv
        + "\n\t- équipement :\n\t\t- masse d'arme\n\t\t- armure d'écaille\n\t\t- arbalète légère";
  }

}
