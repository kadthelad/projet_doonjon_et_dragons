package jeu.joueurs.equipements;

public class ArmeDeGuerre implements Arme {
  String nom;
  String degats = "0d0";
  int portee = 0;

  public ArmeDeGuerre(String nom, String d, int p) {
    this.nom = nom;
    this.degats = d;
    this.portee = p;
  }

  public String getNom() {
    return nom;
  }

  public String getDegats() {
    return degats;
  }

  public void setDegats(String degats) {
    this.degats = degats;
  }

  public int getPortee() {
    return portee;
  }

  public void setPortee(int portee) {
    this.portee = portee;
  }

  @Override
  public String toString() {
    return this.nom + ", degats: " + this.degats + ", portee :" + this.portee + "\n";
  }
}
