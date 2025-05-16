package jeu.joueurs.equipements;

import java.util.HashMap;
import java.util.Map;

public class ArmureLourde implements Armure {
  int classeArmure;

  public void setBonusCaracteristiques(Map<String, Integer> bonusCaracteristiques) {
    this.bonusCaracteristiques = bonusCaracteristiques;
  }

  String nom;

  private Map<String, Integer> bonusCaracteristiques = new HashMap<>() {
    {
      put("vitesse", -4);
    }
  };

  public ArmureLourde(String nom, int CA) {
    this.nom = nom;
    this.classeArmure = CA;
  }

  public int getClasseArmure() {
    return classeArmure;
  }

  public void setClasseArmure(int classeArmure) {
    this.classeArmure = classeArmure;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public Map<String, Integer> getBonusCaracteristiques() {
    return bonusCaracteristiques;
  }

  public void setBonusCaracteristiques(String key, Integer value) {
    this.bonusCaracteristiques.put(key, value);
  }

  @Override
  public String toString() {
    String pres = this.nom + ", Classe d'Armure: " + this.classeArmure;
    for (Map.Entry<String, Integer> entry : bonusCaracteristiques.entrySet()) {
      pres += ", ";
    }
    return pres;
  }

}
