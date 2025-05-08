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

    this.m_force = race.bonus("force");
  }

  public void changePV(int pv) {
    this.m_pv += pv;
  }

  public void changeForce(int force) {
    this.m_force = +force;
  }

  public void changeDexterite(int dexterite) {
    this.m_dexterite += dexterite;
  }

  public void changeVitesse(int vitesse) {
    this.m_vitesse += vitesse;
  }

  public void changeInitiative(int initiative) {
    this.m_initiative += initiative;
  }

  public void changeEquipement(ArrayList<Equipement> equipement) {
    this.m_equipement = equipement;
  }

  public void changeArmeePortee(Equipement armeePortee) {
    this.m_armeePortee = armeePortee;
  }

  public void changeArmurePortee(Equipement armurePortee) {
    this.m_armurePortee = armurePortee;
  }

}
