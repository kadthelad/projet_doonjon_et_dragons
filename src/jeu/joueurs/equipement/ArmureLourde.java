package jeu.joueurs.equipement;

import jeu.Caracteristique;
import java.util.HashMap;
import java.util.Map;

public class ArmureLourde extends Armure {
  private Map<Caracteristique, Integer> caracteristiquesBonus = new HashMap<>() {
    {
      put(Caracteristique.VITESSE, -4);
    }
  };

  public ArmureLourde(String nom, int CA) {
    this.nom = nom;
    this.classeArmure = CA;
  }

  public Map<Caracteristique, Integer> getBonusCaracteristiques(Caracteristique c) {
    return caracteristiquesBonus;
  }

  public void setBonusCaracteristiques(Caracteristique key, Integer value) {
    this.caracteristiquesBonus.put(key, value);
  }

  public ArmureLourde copie() {
    return new ArmureLourde(this.nom, this.classeArmure);
  }

  @Override
  public String toString() {
    String pres = this.nom + ", Classe d'Armure: " + this.classeArmure;
    for (Map.Entry<Caracteristique, Integer> entry : caracteristiquesBonus.entrySet()) {
      pres += ", " + entry.getKey().toString() + " : " + entry.getValue().toString();
    }
    return pres;
  }

}
