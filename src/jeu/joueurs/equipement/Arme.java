package jeu.joueurs.equipement;

import jeu.Caracteristique;
import jeu.utils.Output;

public class Arme implements Equipement {
  protected String nom;
  protected String degats = "0d0";
  protected int portee = 0;
  protected int bonus = 0;

  protected Caracteristique caracteristique = Caracteristique.FORCE;

  public Caracteristique getCaracteristique() {
    return caracteristique;
  }


  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setDegats(String degats) {
    this.degats = degats;
  }

  public void setPortee(int portee) {
    this.portee = portee;
  }

  public String getNom() {
    return nom;
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
