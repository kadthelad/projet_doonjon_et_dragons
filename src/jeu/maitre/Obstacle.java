package jeu.maitre;

import jeu.Entite;

public class Obstacle extends Entite
{

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

  @Override
  public void changePV(int pv)
  {

  }

  @Override
  public String getAlias()
  {
    return "[x]";
  }
}
