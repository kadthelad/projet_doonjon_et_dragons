package jeu.joueurs.equipement;

import java.util.HashMap;
import java.util.Map;

import jeu.Caracteristique;
import jeu.utils.Output;

import jeu.Entite;

/**
 * Arme
 */
public abstract class Arme extends Equipement {
  protected String degats = "0d0";
  protected int portee = 0;
  protected int bonus = 0;

  protected Caracteristique caracteristique = Caracteristique.FORCE;

/**
 * @param nom
 * @param degats
 * @param portee
 */
public Arme(String nom, String degats, int portee) {
    this.nom = nom;
    this.degats = degats;
    this.portee = portee;
  }

  /**
 * @return
 */
public Caracteristique getCaracteristique() {
    return this.caracteristique;
  }

  public void setDegats(String degats) {
    this.degats = degats;
  }

  public void setPortee(int portee) {
    this.portee = portee;
  }

  public String getDegats() {
    return degats;
  }

  public int getPortee() {
    return portee;
  }

  public int getBonus() {
    return this.bonus;
  }

  public void ameliore() {
    this.bonus += 1;
    Output.print("Cette arme est améliorée et a désormais un bonus de " + this.bonus);
  }

  @Override
  public String toString() {
    return this.nom + ", degats: " + this.degats + ", portee :" + this.portee;
  }
}
