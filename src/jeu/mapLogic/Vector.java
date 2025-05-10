package mapLogic;

public class Vector {
    // La classe Vector contiendra 2 axes de coordonnées: l'axe X et l'axe Y.
    // Nous pourrons utiliser les vecteurs pour assigner une position à des personnages ou à des monstres
    // cela nous permettra de faire des calculs dans le jeu (vérifier la présence d'un obstacle, par exemple)

    // Dans notre jeu, on aura besoin seulement de coordonnées X et Y en entiers, pas besoin de nombres à virgule.
    private int x;
    private int y;

    public Vector(int posx, int posy)
    {
        ///  CONSTRUCTEUR DE LA CLASSE Vector ///
        this.x = posx;
        this.y = posy;
    }


    // Je sais pas si cette méthode est utile, je la met en commentaire pour l'instant
    // Elle n'apparaîtra pas dans le UML !
    /*
    public Vector getVector()
    {
        /// Methode permettant de récuperer la position (Vector) ///
        return new Vector(this.x, this.y);
    }
    */

    public int getPosX()
    {
        /// Methode permettant de récuperer la position x ///
        return this.x;
    }

    public int getPosY()
    {
        ///  Methode permettant de récuperer la position y ///
        return this.y;
    }
}
