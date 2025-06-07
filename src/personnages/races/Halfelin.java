package personnages.races;

import entite.Caracteristique;
import java.util.HashMap;

public class Halfelin extends Race {
  public Halfelin() {
    nom = "Halfelin";
    bonusCaracteristiques = new HashMap<>();
    bonusCaracteristiques.put(Caracteristique.DEXTERITE, 4);
    bonusCaracteristiques.put(Caracteristique.VITESSE, 2);
  }
}
