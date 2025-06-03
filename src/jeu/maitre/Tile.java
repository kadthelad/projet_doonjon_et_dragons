package jeu.maitre;

import jeu.Entite;
import jeu.utils.Position;

public class Tile extends Entite
{
  private String m_chose = " . ";

  public Tile(Position pos)
  {
    this.position = pos;
  }

  public void setChose(String chose)
  {
    this.m_chose = chose;
  }

  public Position getPosition()
  {
    return this.position;
  }

  @Override
  public boolean attaque(Entite cible)
  {
    return false;
  }

  @Override
  public int getCA()
  {
    return 0;
  }

  @Override
  public String getNom()
  {
    return "";
  }

  public String getAlias()
  {
    return this.m_chose;
  }

  @Override
  public void changePV(int pv)
  {

  }
}
