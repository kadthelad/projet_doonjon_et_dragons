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

  private ArrayList<Equipement> m_equipement;
  private Equipement m_armeePortee;
  private Equipement m_armurePortee;

  public Personnage(String nom, Race race, Classe classe) {
    this.m_nom = nom;
    this.m_race = race;
    this.m_classe = classe;
  }
}
