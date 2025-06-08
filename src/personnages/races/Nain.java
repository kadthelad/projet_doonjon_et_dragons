package personnages.races;

import entite.Caracteristique;
import java.util.HashMap;

public class Nain extends Race {
  public Nain() {
    nom = "Nain.e";
    bonusCaracteristiques = new HashMap<>();
    bonusCaracteristiques.put(Caracteristique.FORCE, 6);
  }
}
