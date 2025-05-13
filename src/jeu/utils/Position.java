package jeu.utils;

public class Position {
    private int x;
    private int y;

    public Position(int posx, int posy)
    {
        ///  Constructeur de classe ///
        this.x = posx;
        this.y = posy;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    @Override
    public String toString() {
        return "("+this.x+", "+this.y+")";
    }
}
