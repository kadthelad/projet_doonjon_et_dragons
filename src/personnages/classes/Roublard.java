package personnages.classes;

import java.util.Arrays;

import personnages.equipement.*;

public class Roublard extends Classe {

  public Roublard() {
    nom = "Roublard.e";
    pv = 16;
    listeArmes = Arrays.asList(
        Catalogue.getArmeParNom("Rapière"),
        Catalogue.getArmeParNom("Arc Court"));
  }
}
