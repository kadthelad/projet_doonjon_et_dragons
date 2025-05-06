package joueurs;

import joueurs.races.*;
import joueurs.classes.*;
import joueurs.equipements.*;
import java.util.ArrayList;

public class Personnage {

  private String m_nom;
  private Race m_race;
  private Classe m_classe;

  private int m_pv;
  private int m_force;
  private int m_dexterite;
  private int m_vitesse;
  private int m_initiative;

  private ArrayList<Equipement> equipement;
  private Equipement armeePortee;
  private Equipement armurePortee;

  public Personnage() {

  }
}
