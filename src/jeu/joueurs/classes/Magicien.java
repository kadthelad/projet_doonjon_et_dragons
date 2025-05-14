package jeu.joueurs.classes;

/**
 * Magicien
 */
public class Magicien implements Classe {

  private int m_pv = 12;
  private String m_nom = "Magicien";

  public String getNom() {
    return m_nom;
  }

  public String presentation() {
    return "Magicien : \n\t- " + m_pv
        + "\n\t- équipement :\n\t\t- baton\n\t\t- fronde";
  }
}
