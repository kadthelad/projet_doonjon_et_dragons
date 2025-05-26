package jeu.joueurs.equipement;

import jeu.Caracteristique;
import java.util.HashMap;
import java.util.Map;

public class ArmureLourde extends Armure {
  private Map<Caracteristique, Integer> bonusCaracteristiques = new HashMap<>() {
    {
      put(Caracteristique.VITESSE, -4);
    }
  };

  public ArmureLourde(String nom, int CA) {
    this.nom = nom;
    this.classeArmure = CA;
  }

  public Map<Caracteristique, Integer> getBonusCaracteristiques(Caracteristique c) {
    return bonusCaracteristiques;
  }

  public void setBonusCaracteristiques(Caracteristique key, Integer value) {
    this.bonusCaracteristiques.put(key, value);
  }

  @Override
  public String toString() {
    String pres = this.nom + ", Classe d'Armure: " + this.classeArmure;
    for (Map.Entry<Caracteristique, Integer> entry : bonusCaracteristiques.entrySet()) {
      pres += ", " + entry.getKey().toString() + " : " + entry.getValue().toString();
    }
    return pres;
  }

}
