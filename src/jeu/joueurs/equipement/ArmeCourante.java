
package jeu.joueurs.equipement;

public class ArmeCourante implements Arme {
  String nom;
  String degats = "0d0";
  int portee = 0;

  public ArmeCourante(String nom, String d, int p) {
    this.nom = nom;
    this.degats = d;
    this.portee = p;
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

  @Override
  public String toString() {
    return this.nom + ", degats: " + this.degats + ", portee :" + this.portee;
  }
}
