package maitre;

class Maitre
{
    private Monstre[] m_monstres;

    public Maitre(Monstre[] monstresList)
    {
        this.m_monstres = monstresList;
    }

    @Override
    public String toString() {
        String txt = "Le maitre du jeu controle ces monstres : ";
        for (Monstre mstr : m_monstres)
        {
            txt += mstr.m_espece + " ";
        }
        return txt;
    }
}