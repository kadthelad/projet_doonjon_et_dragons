package jeu.joueurs.classes;

import java.util.Arrays;

import jeu.joueurs.equipement.*;

public class Magicien extends Classe {

    public Magicien() {
        nom = "Magicien.ne";
        pv = 12;
        listeEquipement = Arrays.asList(
            new ArmeCourante("Baton", "1d6", 1),
            new ArmeDistance("Fronde", "1d4", 6)
        );
    }
}
