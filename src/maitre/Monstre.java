package maitre;

class Monstre
{
    private String m_espece;
    private int m_numero;

    private String m_nomAttaque;
    private int[] m_attaque;
    // m_attaque aura 3 valeurs, ex. : [0, 1, 2]
    // -> 0 représente les dégàts de l'attaque
    // -> 1 représente le nombre de dés lancés
    // -> 2 représente le nombre de faces des dés
    // dans l'exemple, cette attaque fait 0 dégàts, et c'est un 1d2.


    //private  Vector m_position;

    private int m_ptVie;
    private int m_force;
    private int m_dexterite;
    private int m_classeArmure;
    private int m_initiative;

    public Monstre(String esp, int nb, String nAtt,int[] att, int pdv, int frc, int dxt, int clssarm, int initve)
    {
        /// CONSTRUCTEUR DE LA CLASSE Monstre ///
        this.m_espece = esp;
        this.m_numero = nb;
        this.m_nomAttaque = nAtt;
        this.m_attaque = att;
        this.m_ptVie = pdv;
        this.m_force = frc;
        this.m_dexterite = dxt;
        this.m_classeArmure = clssarm;
        this.m_initiative = initve;
    }

    public String get_espece()
    {
        /// Avoir l'espece d'un Monstre ///
        return this.m_espece;
    }

    @Override
    /// String override ///
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