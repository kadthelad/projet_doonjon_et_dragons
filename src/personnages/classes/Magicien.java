package personnages.classes;

import java.util.Arrays;

import personnages.equipement.*;
import personnages.sorts.ArmeMagique;
import personnages.sorts.BoogieWoogie;
import personnages.sorts.Guerison;

public class Magicien extends Classe {

  public Magicien() {
    nom = "Magicien.ne";
    pv = 12;
    listeArmes = Arrays.asList(
        Catalogue.getArmeParNom("Bâton"),
        Catalogue.getArmeParNom("Fronde"));
    // listeArmures = Arrays.asList();

    listeSorts = Arrays.asList(
      new Guerison(),
      new BoogieWoogie(),
      new ArmeMagique());
  }
}
