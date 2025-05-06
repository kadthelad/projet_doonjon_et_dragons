package maitre;

class Monstre
{
    private String m_espece;
    private int m_numero;
    private int[] m_attaque;
    //private  Vector m_position;

    private int m_ptVie;
    private int m_force;
    private int m_dexterite;
    private int m_classeArmure;
    private int m_initiative;

    public Monstre(String esp, int nb, int[] att, int pdv, int frc, int dxt, int clssarm, int initve)
    {
        this.m_espece = esp;
        this.m_numero = nb;
        this.m_ptVie = pdv;
        this.m_force = frc;
        this.m_dexterite = dxt;
        this.m_classeArmure = clssarm;
        this.m_initiative = initve;
    }

    @Override
    public String toString() {
        return "Monstre d'éspèce : " + m_espece +
                "\nNuméro : " + m_numero +
                "\nAttaque : " + m_attaque[0] + "dégàt(s), " + m_attaque[1]+"d"+m_attaque[2] +
                "\n_Caractéristiques_\n" + m_ptVie + " HP\n" +
                "\n" + m_force + " Force" +
                "\n" + m_dexterite + " Déxterité" +
                "\n" + m_classeArmure + " Armure" +
                "\n" + m_initiative + " Initiative";
    }
}