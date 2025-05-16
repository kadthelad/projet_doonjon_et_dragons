package jeu.maitre;

import jeu.utils.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Monstre
{
    private String m_espece = "";
    private int m_numero = 0; // si il existe plusieurs monstres de la meme espece

    private String m_nomAttaque = "";
    private int[] m_attaque = {0, 0, 0};    // m_attaque aura 3 valeurs, ex. : [0, 1, 2]
                                // -> 0 représente les dégâts de l'attaque
                                // -> 1 représente le nombre de dés lancés
                                // -> 2 représente le nombre de faces des dés
                                // dans l'exemple, cette attaque fait 0 dégâts, et c'est un 1d2.

    private Position m_position = new Position(0, 0);

    private int m_ptVie;

    private Map<String, Integer> mapCaracteristiques = new HashMap<>();
    {
        mapCaracteristiques.put("force", 0);
        mapCaracteristiques.put("dexterite", 0);
        mapCaracteristiques.put("vitesse", 0);
        mapCaracteristiques.put("initiative", 0);
        mapCaracteristiques.put("classeArmure", 0);
    }

    public Monstre(String esp, String nAtt, int[] att, int pdv, Map<String, Integer> mapCara)
    {
        /// CONSTRUCTEUR DE LA CLASSE Monstre ///
        this.m_espece = esp;
        this.m_nomAttaque = nAtt;
        this.m_attaque = att;
        this.m_ptVie = pdv;

        this.mapCaracteristiques = mapCara;
    }

    public String getEspece()
    {
        /// Avoir l'espece d'un Monstre ///
        return this.m_espece;
    }

    public void setEspece(String esp)
    {
        this.m_espece = esp;
    }

    public Position getPosition()
    {
        return this.m_position;
    }

    @Override
    /// String override ///
    public String toString() {
        return "Monstre d'espece : " + this.m_espece +
                "\nAttaque : " + this.m_attaque[0] + "dégàt(s), " + this.m_attaque[1]+"d"+this.m_attaque[2] +
                "\n_Caractéristiques_\n" + this.m_ptVie + " HP\n" +
                this.mapCaracteristiques.get("force") + " Force" +
                "\n" + this.mapCaracteristiques.get("dexterite") + " Déxterité" +
                "\n" + this.mapCaracteristiques.get("vitesse") + " Vitesse" +
                "\n" + this.mapCaracteristiques.get("classeArmure") + " Armure" +
                "\n" + this.mapCaracteristiques.get("initiative") + " Initiative";
    }

    public void changePV(int pv) {
        this.m_ptVie += pv;
    }

    public void changeCaracteristique(String c, int v) {
        this.mapCaracteristiques.put(c, v);
    }

    public int getCaracteristique(String c) {
        return this.mapCaracteristiques.get(c);
    }
}