package jeu.joueurs.classes;

import java.util.Arrays;

import jeu.joueurs.equipement.*;

public class Roublard extends Classe {

    public Roublard() {
        nom = "Roublard.e";
        pv = 16;
        listeEquipement = Arrays.asList(
            new ArmeDeGuerre("Rapiere", "1d8", 1),
            new ArmeDistance("Arc Court", "1d6", 16)
        );
    }
}
