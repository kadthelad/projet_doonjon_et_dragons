package jeu.joueurs.equipement;

import java.util.ArrayList;
import java.util.List;

public class Catalogue {
    private static final List<Arme> LISTE_ARMES = new ArrayList<>();
    private static final List<Armure> LISTE_ARMURES = new ArrayList<>();

    static {
        // Armes par défaut
        LISTE_ARMES.add(new ArmeCourante("Bâton", "1d6", 1));
        LISTE_ARMES.add(new ArmeCourante("Masse d'armes", "1d6", 1));
        LISTE_ARMES.add(new ArmeDeGuerre("Épée longue", "1d8", 1));
        LISTE_ARMES.add(new ArmeDeGuerre("Rapière", "1d8", 1));
        LISTE_ARMES.add(new ArmeDeGuerre("Épée à deux mains", "2d6", 1));
        LISTE_ARMES.add(new ArmeDistance("Arbalète légère", "1d8", 16));
        LISTE_ARMES.add(new ArmeDistance("Fronde", "1d4", 6));
        LISTE_ARMES.add(new ArmeDistance("Arc court", "1d6", 16));

        // Armures par défaut
        LISTE_ARMURES.add(new ArmureLegere("Armure d'écailles", 9));
        LISTE_ARMURES.add(new ArmureLegere("Demi-plate", 10));
        LISTE_ARMURES.add(new ArmureLourde("Cotte de mailles", 11));
        LISTE_ARMURES.add(new ArmureLourde("Harnois", 12));
    }

    // Pour récupérer une copie de la liste des armes par défaut
    public static List<Arme> getListeArmes() {
        List<Arme> copie = this.LISTE_ARMES;
        for (Arme a : LISTE_ARMES) copie.add(a.copie());
        return copie;
    }
    // Pour récupérer une copie de la liste des armures par défaut
    public static List<Armure> getListeArmures() {
        List<Armure> copie = new ArrayList<>();
        for (Armure a : LISTE_ARMURES) copie.add(a.copie());
        return copie;
    }

    // Pour obtenir une arme par son nom (toujours une copie)
    public static Arme getArmeParNom(String nom) {
        for (Arme a : LISTE_ARMES) {
            if (a.getNom().equalsIgnoreCase(nom)) return a.copie();
        }
        return null;
    }
    // Pour obtenir une armure par son nom (toujours une copie)
    public static Armure getArmureParNom(String nom) {
        for (Armure a : LISTE_ARMURES) {
            if (a.getNom().equalsIgnoreCase(nom)) return a.copie();
        }
        return null;
    }
}
