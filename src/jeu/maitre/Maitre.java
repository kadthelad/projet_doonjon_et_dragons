package jeu.maitre;

import java.util.ArrayList;

public class Maitre
{
    private ArrayList<Monstre> m_monstres = new ArrayList<Monstre>() {}; // Cet attribut comportera une liste de tous les
                                  // monstres que le maitre peut controller

    public Maitre()
    {

    }

    public ArrayList<Monstre> getM_monstres()
    {
        return this.m_monstres;
    }

    @Override
    ///  String Override ///
    public String toString() {
        String txt = "Monstres contrôlés par le MDJ : ";
        for (Monstre mstr : m_monstres)
        {
            txt += mstr.getEspece() + " ";
        }
        return txt;
    }
}