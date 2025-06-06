package jeu.maitre;

import jeu.Creature;
import jeu.utils.Des;
import jeu.utils.Output;
import jeu.utils.Position;
import jeu.Caracteristique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Monstre extends Creature {
  private String m_espece = "";
  private int m_numero = 0; // si il existe plusieurs monstres de la meme espece

  private String m_nomAttaque = "";
  private String m_attaque = "";

  private Position m_position = new Position(0, 0);

  private String m_icon;

  {
    caracteristiques.put(Caracteristique.CLASSE_ARMURE, 0);
  }

  public Monstre(String esp, String nAtt, String att, Map<Caracteristique, Integer> mapCara, String icon) {
    /// CONSTRUCTEUR DE LA CLASSE Monstre ///
    this.m_espece = esp;
    this.m_nomAttaque = nAtt;
    this.m_attaque = att;
    this.caracteristiques = mapCara;
    this.m_icon = icon;
  }

  public Monstre(Monstre mstr) {
    /// CONSTRUCTEUR DE DUPLICATION ///
    this.m_espece = mstr.getEspece();
    this.m_nomAttaque = mstr.getNomAttaque();
    this.m_attaque = mstr.getAttaque();
    this.caracteristiques = mstr.getMapCaracteristiques();
  }

  public String getEspece() {
    /// Avoir l'espèce d'un Monstre ///
    return this.m_espece;
  }

  public String getNomAttaque() {
    return this.m_nomAttaque;
  }

  public String getAttaque() {
    return this.m_attaque;
  }

  public Map<Caracteristique, Integer> getMapCaracteristiques() {
    return this.caracteristiques;
  }

  public void setEspece(String esp) {
    this.m_espece = esp;
  }

  public void setIcon(String icn) {
    this.m_icon = icn;
  }

  public void setNomAtt(String nomAtt) {
    this.m_nomAttaque = nomAtt;
  }

  public void setSpeAtt(String speAtt) {
    this.m_attaque = speAtt;
  }

  public Position getPosition() {
    return this.m_position;
  }

  @Override
  public boolean attaque(Creature cible) {
    int d = distance(cible.getPosition());
    if (d > 1) {
      Output.print("La cible est trop loin (portée : " + 1 + " < distance :" + d + ")");
      return false;
    }
    int jet = Des.lancer(this.m_attaque);

    if (jet < cible.getCA()) {
      Output.print("Votre attaque ne touche pas ( jet + caracteristique : " + jet + " < classe d'amure de la cible :"
              + cible.getCA() + ")");
      return false;
    }

    cible.changePV(jet * -1);

    Output.print("Vous avez infligé " + jet + " degats à " + cible.getNom());

    return true;
  }

  public int getCA() {
    return this.caracteristiques.get(Caracteristique.CLASSE_ARMURE);
  }

  public String getNom() {
    return this.m_espece + this.m_numero;
  }

  @Override
  /// String override ///
  public String toString() {
    return "Monstre : " + this.m_espece +
        " (" + getAlias() + ")" +
        "\nAttaque : " + m_attaque +
        "\n___Caractéristiques___\n" +
        this.caracteristiques.get(Caracteristique.PV) + " PV\n" +
        this.caracteristiques.get(Caracteristique.FORCE) + " Force\n" +
        this.caracteristiques.get(Caracteristique.DEXTERITE) + " Déxterité\n" +
        this.caracteristiques.get(Caracteristique.VITESSE) + " Vitesse\n" +
        this.caracteristiques.get(Caracteristique.INITIATIVE) + " Initiative\n" +
        this.caracteristiques.get(Caracteristique.CLASSE_ARMURE) + " Classe d'Armure\n";
  }

  public void changePV(int pv) {
    this.caracteristiques.replace(Caracteristique.PV, this.caracteristiques.get(Caracteristique.PV) + pv);
  }

  @Override
  public String getAlias() {
    return this.m_icon + this.m_numero;
  }

  public void setCaracteristique(Caracteristique c, int v) {
    this.caracteristiques.put(c, v);
  }

  public int getCaracteristique(Caracteristique c) {
    return this.caracteristiques.get(c);
  }

  public void changeCaracteristiqueMap(Map<Caracteristique, Integer> map) {
    this.caracteristiques = map;
  }

  public boolean equipperArme()
  {
    Output.println("Cette créature ne peut pas équipper d'arme !");
    return false;
  }

  public boolean equipperArmure()
  {
    Output.println("Cette créature ne peut pas équipper d'armure !");
    return false;
  }
}
