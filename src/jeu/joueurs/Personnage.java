package jeu.joueurs;

import jeu.*;
import jeu.utils.*;
import jeu.joueurs.races.*;
import jeu.joueurs.classes.*;
import jeu.joueurs.equipement.*;
import jeu.joueurs.sorts.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Personnage extends Creature {

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
    caracteristiques.put(Caracteristique.PV_MAX, 0);
  }

  private List<Arme> listeArmes;
  private List<Armure> listeArmures;
  // private List<Sort> listeSorts;
  private Arme armeEquipee;
  private Armure armureEquipee;

  public Personnage(String nom, Race race, Classe classe) {
    this.nom = nom;
    this.race = race;
    this.classe = classe;
    this.caracteristiques.put(Caracteristique.PV, classe.getPV());

    for (Map.Entry<Caracteristique, Integer> entry : caracteristiques.entrySet()) {
      Caracteristique key = entry.getKey();
      int value;
      if (key == Caracteristique.PV || key == Caracteristique.PV_MAX) {
        value = race.bonus(key) + entry.getValue();
        entry.setValue(value);
      } else {
        value = Des.lancer(4, 4) + race.bonus(key);
        entry.setValue(value);
      }
    }
    this.listeArmes = classe.getListeArmes();
    this.listeArmures = classe.getListeArmures();
    // this.listeSorts = classe.getListeSorts();
  }

  public void changePV(int pv) {
    this.changeCaracteristique(Caracteristique.PV, pv);

    int pvMax = this.caracteristiques.get(Caracteristique.PV_MAX);
    if (this.caracteristiques.get(Caracteristique.PV) > pvMax) {
      this.caracteristiques.put(Caracteristique.PV, pvMax);
    }
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

  // si aucune caractéristique n'est spécifié on print toutes les caractéristiques
  public String getCaracteristique() {
    String c = "";
    for (Map.Entry<Caracteristique, Integer> entry : caracteristiques.entrySet()) {
      c += "	- " + entry.getKey() + " : " + entry.getValue().toString() + "\n";
    }
    return c;
  }

 //  public List<Sort> getListeSorts() {
	// return listeSorts;
 //  }

  public void addArme(Arme a) {
    this.listeArmes.add(a);
  }

  public void addArmure(Armure a) {
    this.listeArmures.add(a);
  }

  public List<Arme> getListeArmes() {
    return this.listeArmes;
  }

  public List<Armure> getListeAmures() {
    return this.listeArmures;
  }

  public void changeArmeeEquipee(Arme arme) {
    this.armeEquipee = arme;
    for (Map<Caracteristique, Integer> entry : this.armeEquipee.get
  }

  public void changeArmureEquipee(Armure armure) {
    this.armureEquipee = armure;
  }

  public String getNom() {
    return nom;
  }

  public String getAlias()
  {
    return nom.substring(0, 2).toUpperCase();
  }

  public Race getRace() {
    return race;
  }

  public Classe getClasse() {
    return classe;
  }

  public int getCA() {
    return this.armureEquipee.getClasseArmure();
  }

  public boolean attaque(Creature cible) {
    int d = distance(cible.getPosition());
    int portee = this.armeEquipee.getPortee();
    if (d > portee) {
      Output.print("La cible est trop loin (portée : " + portee + " < distance :" + d + ")");
      return false;
    }
    int jet = Des.lancer() + this.armeEquipee.getBonus()
        + this.getCaracteristique(this.armeEquipee.getCaracteristique());

    if (jet < cible.getCA()) {
      Output.print("Votre attaque ne touche pas ( jet + caracteristique : " + jet + " < classe d'amure de la cible :"
          + cible.getCA() + ")");
      return false;
    }

    int degats = Des.lancer(this.armeEquipee.getDegats()) + this.armeEquipee.getBonus();
    cible.changePV(degats * -1);

    Output.print("Vous avez infligé " + degats + " degats à " + cible.getNom());

    return true;
  }

  @Override
  public String toString() {

    return this.getNom() + ", le ou la " + this.getClasse().getNom() + " "
        + this.getRace().getNom()
        + "\nCaractéristiques :\n" + this.getCaracteristique();
  }
}
