package maitreJeu;

import utils.Input;
import utils.Output;

public class Maitre
{
    /*
    private ArrayList<Monstre> m_monstres = new ArrayList<Monstre>() {}; // Cet attribut comportera une liste de tous les
                                  // monstres que le maitre peut controller
    */

    public Maitre()
    {

    }

    public void commenter()
    {
        Output.println("[Maitre du resources.Jeu] Ajouter un commentaire?\n" +
                "[0] oui" +
                "[1] non");
        int userInt = Input.entier();
        if (userInt == 0)
        {
            Output.println("Votre commentaire : ");
            String userStr = Input.string();
            Output.print("-Commentaire- : " + userStr);
        }
        return;
    }

    /*
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
    }*/
}