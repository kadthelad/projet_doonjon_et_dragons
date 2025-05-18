package jeu.joueurs;

import jeu.*;
import jeu.joueurs.races.*;
import jeu.joueurs.classes.*;
import jeu.joueurs.equipement.*;
import jeu.utils.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Personnage {

  private String nom;
  private Race race;
  private Classe classe;

  private Map<Caracteristique, Integer> caracteristiques = new HashMap<>();
  {
    caracteristiques.put(Caracteristique.FORCE, 0);
    caracteristiques.put(Caracteristique.DEXTERITE, 0);
    caracteristiques.put(Caracteristique.VITESSE, 0);
    caracteristiques.put(Caracteristique.INITIATIVE, 0);
    caracteristiques.put(Caracteristique.PV, 0);
  }

  private List<Equipement> equipement;
  private Equipement armeePortee;
  private Equipement armurePortee;

  public Personnage(String nom, Race race, Classe classe) {
    this.nom = nom;
    this.race = race;
    this.classe = classe;
    this.caracteristiques.put(Caracteristique.PV, classe.getPV());

    for (Map.Entry<Caracteristique, Integer> entry : caracteristiques.entrySet()) {
      Caracteristique key = entry.getKey();
      int value = Des.lancer(4, 4) + race.bonus(key);
      entry.setValue(value);
    }

    if (race instanceof Humain) {
      this.changePV(Humain.getBonus());
    }

    this.equipement = classe.getListeEquipement();
  }

  public void changePV(int pv) {
    this.changeCaracteristique(Caracteristique.PV, pv);
  }

  public void getPV() {
    this.getCaracteristique(Caracteristique.PV);
  }

  public void changeCaracteristique(Caracteristique c, int v) {
    int previous = this.caracteristiques.get(c);
    this.caracteristiques.put(c, previous + v);
  }

  public int getCaracteristique(Caracteristique c) {
    return caracteristiques.get(c);
  }

  // si aucune caracteristique n'est spécifié on print toutes les caractéristiques
  public String getCaracteristique() {
    String c = "";
    for (Map.Entry<Caracteristique, Integer> entry : caracteristiques.entrySet()) {
      c += "	- " + entry.getKey() + " : " + entry.getValue().toString() + "\n";
    }
    return c;
  }

  public void addEquipement(Equipement e) {
    this.equipement.add(e);
  }

  public void changeArmeePortee(Equipement armeePortee) {
    this.armeePortee = armeePortee;
  }

  public void changeArmurePortee(Equipement armurePortee) {
    this.armurePortee = armurePortee;
  }

  public String getNom() {
    return nom;
  }

  public Race getRace() {
    return race;
  }

  public Classe getClasse() {
    return classe;
  }

  @Override
  public String toString() {

    return this.getNom() + ", le ou la " + this.getClasse().getNom() + " "
        + this.getRace().getNom()
        + "\nCaractéristiques :\n" + this.getCaracteristique();
  }
}
