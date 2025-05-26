package jeu.joueurs.equipement;

public class Arme implements Equipement {
  protected String nom;
  protected String degats = "0d0";
  protected int portee = 0;
  protected int bonus = 0;

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
  }

  @Override
  public String toString() {
    return this.nom + ", degats: " + this.degats + ", portee :" + this.portee;
  }

}
