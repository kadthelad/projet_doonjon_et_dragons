package jeu.joueurs.classes;

import java.util.Arrays;

import jeu.joueurs.equipement.*;

public class Guerrier extends Classe {

    public Guerrier() {
        nom = "Guerrier.re";
        pv = 20;
        listeEquipement = Arrays.asList(
            new ArmeDeGuerre("Epee Longue", "1d8", 1),
            new ArmureLourde("Cotte de Mailles", 11),
            new ArmeDistance("Arbalete Legere", "1d8", 16)
        );
    }
}
