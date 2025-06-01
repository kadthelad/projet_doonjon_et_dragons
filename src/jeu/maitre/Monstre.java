package jeu.maitre;

import jeu.Entite;
import jeu.utils.Position;
import jeu.Caracteristique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Monstre extends Entite
{
    private String m_espece = "";
    private int m_numero = 0; // si il existe plusieurs monstres de la meme espece

    private String m_nomAttaque = "";
    private String m_attaque = "";

    private Position m_position = new Position(0, 0);

    private Map<Caracteristique, Integer> mapCaracteristiques = new HashMap<>();
    {
        mapCaracteristiques.put(Caracteristique.PV, 0);
        mapCaracteristiques.put(Caracteristique.FORCE, 0);
        mapCaracteristiques.put(Caracteristique.DEXTERITE, 0);
        mapCaracteristiques.put(Caracteristique.VITESSE, 0);
        mapCaracteristiques.put(Caracteristique.INITIATIVE, 0);
        mapCaracteristiques.put(Caracteristique.CLASSE_ARMURE, 0);
    }

    public Monstre(String esp, String nAtt, String att, Map<Caracteristique, Integer> mapCara)
    {
        /// CONSTRUCTEUR DE LA CLASSE Monstre ///
        this.m_espece = esp;
        this.m_nomAttaque = nAtt;
        this.m_attaque = att;
        this.mapCaracteristiques = mapCara;
    }

    public Monstre(Monstre mstr)
    {
        /// CONSTRUCTEUR DE DUPLICATION ///
        this.m_espece = mstr.getEspece();
        this.m_nomAttaque = mstr.getNomAttaque();
        this.m_attaque = mstr.getAttaque();
        this.mapCaracteristiques = mstr.getMapCaracteristiques();
    }

    public String getEspece()
    {
        /// Avoir l'espece d'un Monstre ///
        return this.m_espece;
    }

    public String getNomAttaque()
    {
        return this.m_nomAttaque;
    }

    public String getAttaque()
    {
        return this.m_attaque;
    }

    public Map<Caracteristique, Integer> getMapCaracteristiques()
    {
        return this.mapCaracteristiques;
    }

    public void setEspece(String esp)
    {
        this.m_espece = esp;
    }

    public void setNomAtt(String nomAtt)
    {
        this.m_nomAttaque = nomAtt;
    }

    public void setSpeAtt(String speAtt)
    {
        this.m_attaque = speAtt;
    }

    public Position getPosition()
    {
        return this.m_position;
    }

    public boolean attaque(Entite cible)
    {
        return false;
    }

    public int getCA()
    {
        return this.mapCaracteristiques.get(Caracteristique.CLASSE_ARMURE);
    }

    public String getNom()
    {
        return this.m_espece+this.m_numero;
    }

    @Override
    /// String override ///
    public String toString() {
        return "Monstre : " + this.m_espece +
                "\nAttaque : " + m_attaque +
                "\n___Caractéristiques___\n" +
                this.mapCaracteristiques.get(Caracteristique.PV) + " PV\n" +
                this.mapCaracteristiques.get(Caracteristique.FORCE) + " Force\n" +
                this.mapCaracteristiques.get(Caracteristique.DEXTERITE) + " Déxterité\n" +
                this.mapCaracteristiques.get(Caracteristique.VITESSE) + " Vitesse\n" +
                this.mapCaracteristiques.get(Caracteristique.INITIATIVE) + " Initiative\n" +
                this.mapCaracteristiques.get(Caracteristique.CLASSE_ARMURE) + " Classe d'Armure\n";
    }

    public void changePV(int pv) {
        this.mapCaracteristiques.replace(Caracteristique.PV, this.mapCaracteristiques.get(Caracteristique.PV)+pv);
    }

    public void changeCaracteristique(Caracteristique c, int v) {
        this.mapCaracteristiques.put(c, v);
    }

    public int getCaracteristique(Caracteristique c) {
        return this.mapCaracteristiques.get(c);
    }

    public void changeCaracteristiqueMap(Map<Caracteristique, Integer> map)
    {
        this.mapCaracteristiques = map;
    }
}