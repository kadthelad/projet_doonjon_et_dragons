package jeu.joueurs;

import jeu.joueurs.races.*;
import jeu.joueurs.classes.*;
import jeu.joueurs.equipements.*;
import jeu.utils.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Personnage {

  private String m_nom;
  private Race m_race;
  private Classe m_classe;

  private int m_pv;

  private Map<String, Integer> mapCaracteristiques = new HashMap<>();
  {
    mapCaracteristiques.put("force", 0);
    mapCaracteristiques.put("dexterite", 0);
    mapCaracteristiques.put("vitesse", 0);
    mapCaracteristiques.put("initiative", 0);
  }

  private ArrayList<Equipement> m_equipement;
  private Equipement m_armeePortee;
  private Equipement m_armurePortee;

  public Personnage(String nom, Race race, Classe classe) {
    this.m_nom = nom;
    this.m_race = race;
    this.m_classe = classe;

    // OLD VERSION, TO BE REMOVED
    this.m_force = Des.lancer(4, 4) + 3 + race.bonus("force");
    this.m_dexterite = Des.lancer(4, 4) + 3 + race.bonus("dexterite");
    this.m_vitesse = Des.lancer(4, 4) + 3 + race.bonus("vitesse");
    this.m_initiative = Des.lancer(4, 4) + 3 + race.bonus("initiative");

    // nouvelle version
    for (Map.entry(k, v) : mapCaracteristiques.entrySet()) {

    }
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

  public String getNom() {
    return m_nom;
  }

  public Race getRace() {
    return m_race;
  }

  public Classe getClasse() {
    return m_classe;
  }

  public int getPV() {
    return m_pv;
  }

  public int getForce() {
    return m_force;
  }

  public int getVitesse() {
    return m_vitesse;
  }

  public int getInitiative() {
    return m_initiative;
  }

  public int getDexterite() {
    return m_dexterite;
  }

}
