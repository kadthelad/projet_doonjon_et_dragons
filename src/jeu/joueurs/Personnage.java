package jeu.joueurs;

import jeu.joueurs.races.*;
import jeu.joueurs.classes.*;
import jeu.joueurs.equipement.*;
import jeu.utils.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Personnage {

  private String m_nom;
  private Race m_race;
  private Classe m_classe;

  private int m_pv = 0;

  private Map<String, Integer> caracteristiques = new HashMap<>();
  {
    caracteristiques.put("force", 0);
    caracteristiques.put("dexterite", 0);
    caracteristiques.put("vitesse", 0);
    caracteristiques.put("initiative", 0);
  }

  private ArrayList<Equipement> m_equipement;
  private Equipement m_armeePortee;
  private Equipement m_armurePortee;

  public Personnage(String nom, Race race, Classe classe) {
    this.m_nom = nom;
    this.m_race = race;
    this.m_classe = classe;
    this.m_pv = classe.getPV();

    for (Map.Entry<String, Integer> entry : caracteristiques.entrySet()) {
      String key = entry.getKey();
      int value = Des.lancer(4, 4) + race.bonus(key);
      entry.setValue(value);
    }

    if (race instanceof Humain) {
      this.changePV(Humain.getBonus());
    }
  }

  public void changePV(int pv) {
    this.m_pv += pv;
  }

  public void changeCaracteristique(String c, int v) {
    int value = this.caracteristiques.get(c);
    this.caracteristiques.put(c, value + v);
  }

  public int getCaracteristique(String c) {
    return c == "pv" ? this.m_pv : caracteristiques.get(c);
  }

  // si aucune caracteristique n'est spécifié on print toutes les caractéristiques
  public String getCaracteristique() {
    String c = "";
    c += "  - pv : " + this.m_pv + "\n";
    for (Map.Entry<String, Integer> entry : caracteristiques.entrySet()) {
      c += "	- " + entry.getKey() + " : " + entry.getValue().toString() + "\n";
    }
    return c;
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
    return this.caracteristiques.get("pv");
  }

  @Override
  public String toString() {

    return "Votre Personnage est " + this.getNom() + ", le ou la " + this.getClasse().getNom() + " "
        + this.getRace().getNom()
        + "\nCaractéristiques :\n" + this.getCaracteristique();
  }

}
