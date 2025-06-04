package jeu.maitre;

import jeu.Entite;
import jeu.utils.Position;

public class Tile extends Entite
{
  private Position m_position;
  private String m_chose = " . ";

  public Tile(Position pos)
  {
    this.m_position = pos;
  }

  public void setChose(String chose)
  {
    this.m_chose = chose;
  }

  public Position getPosition()
  {
    return this.m_position;
  }

  public String getAlias()
  {
    return this.m_chose;
  }


  public String getNom()
  {
    return "";
  }
}
