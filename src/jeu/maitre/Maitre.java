package maitre;

class Maitre
{
    private Monstre[] m_monstres; // Cet attribut comportera une liste de tous les
                                  // monstres que le maitre peut controller

    public Maitre(Monstre[] monstresList)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Maitre ///
        this.m_monstres = monstresList;
    }

    @Override
    ///  String Override ///
    public String toString() {
        String txt = "Le maitre du jeu controle ces monstres : ";
        for (Monstre mstr : m_monstres)
        {
            txt += mstr.getEspece() + " ";
        }
        return txt;
    }
}