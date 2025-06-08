package entite;

import utils.*;
import personnages.races.*;
import personnages.classes.*;
import personnages.equipement.*;
import personnages.sorts.Sort;

import java.util.List;
import java.util.Map;

public class Personnage extends Creature {

  private String nom;
  private Race race;
  private Classe classe;

  private List<Arme> listeArmes;
  private List<Armure> listeArmures;
  private List<Sort> listeSorts;
  private Arme armeEquipee;
  private Armure armureEquipee;

  {
    this.caracteristiques.put(Caracteristique.PV_MAX, 0);
  }

  public Personnage(String nom, Race race, Classe classe) {
    this.nom = nom;
    this.race = race;
    this.classe = classe;

    for (Map.Entry<Caracteristique, Integer> entry : caracteristiques.entrySet()) {
      Caracteristique key = entry.getKey();
      int value = 0;
      if (key == Caracteristique.PV || key == Caracteristique.PV_MAX) {
        value = race.bonus(key) + Des.lancer(4, 4) + 3;
        caracteristiques.put(key, value);
      } else {
        value = Des.lancer(4, 4) + race.bonus(key);
        caracteristiques.put(key, value);
      }
    }
    this.listeArmes = classe.getListeArmes();
    this.listeArmures = classe.getListeArmures();
    this.listeSorts = classe.getListeSorts();
  }

  public boolean changePV(int modifPV) {
    this.setCaracteristique(Caracteristique.PV, modifPV);
    int pvMax = this.caracteristiques.get(Caracteristique.PV_MAX);
    int pv = this.caracteristiques.get(Caracteristique.PV);
    if (pv > pvMax) {
      this.caracteristiques.put(Caracteristique.PV, pvMax);
    } else if (pv < 1) {
      return false;
    }
    return true;
  }

  public void getPV() {
    this.getCaracteristique(Caracteristique.PV);
  }

  public void setCaracteristique(Caracteristique c, int v) {
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
      c += " - " + entry.getKey() + " : " + entry.getValue().toString() + "\n";
    }
    return c;
  }

  public List<Sort> getListeSorts() {
    return listeSorts;
  }

  public void addArme(Arme a) {
    this.listeArmes.add(a);
  }

  public void addArmure(Armure a) {
    this.listeArmures.add(a);
  }

  public List<Arme> getListeArmes() {
    return this.listeArmes;
  }

  public Arme getArme(int index) {
    return this.listeArmes.get(index);
  }

  public int getNombreArme() {
    return this.listeArmes.size();
  }

  public List<Armure> getListeAmures() {
    return this.listeArmures;
  }

  private void bonusEquipement(Equipement e, int sign) {
    if (e == null)
      return;

    for (Map.Entry<Caracteristique, Integer> entry : e.getCaracteristiqueBonus().entrySet()) {
      Caracteristique key = entry.getKey();
      int value = this.caracteristiques.getOrDefault(key, 0);
      this.caracteristiques.put(key, value + sign * entry.getValue());
    }
  }

  public void changeArmeeEquipee(Arme nouvelleArme) {
    // un supprime l'éventuel précédant bonus
    bonusEquipement(this.armeEquipee, -1);
    this.armeEquipee = nouvelleArme;
    // on ajoute le nouveau bonus
    bonusEquipement(this.armeEquipee, 1);
  }

  public void changeArmureEquipee(Armure nouvelleArmure) {
    bonusEquipement(this.armureEquipee, -1);
    this.armureEquipee = nouvelleArmure;
    bonusEquipement(this.armureEquipee, 1);
  }

  public String getNom() {
    return nom;
  }

  public String getAlias() {
    return nom.substring(0, 3).toUpperCase();
  }

  public Race getRace() {
    return race;
  }

  public Classe getClasse() {
    return classe;
  }

  public int getCA() {
    if (this.armureEquipee != null) {
      return this.armureEquipee.getClasseArmure();
    }
    return 0;
  }

  public boolean attaque(Creature cible) {
    int d = distance(cible.getPosition());

    if (this.armeEquipee == null) {
      Output.println("Vous n'avez pas d'armes! vous ne pouvez donc pas attaquer!");
      return false;
    }
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

    Output.println("Vous avez infligé " + degats + " degats à " + cible.getNom());

    return true;
  }

  public boolean equipperArme() {
    int nbArmes = this.getListeArmes().size();

    Output.println("|" + this.getNom() + ", choisissez une arme à équipper :");
    for (int i = 1; i <= nbArmes; i++) {
      Output.println("|->[" + i + "] " + this.getListeArmes().get(i - 1).getNom());
    }
    Output.println("|->[0] Ne pas équipper");
    int userInt = Input.entier();

    if (userInt == 0 || userInt > nbArmes) {
      Output.println(this.getNom() + " n'a pas changé/équippé de nouvelle arme");
      return false;
    } else {
      Output.println(this.getNom() + " à équippé " + this.getListeArmes().get(userInt - 1).getNom());
      this.changeArmeeEquipee(this.getListeArmes().get(userInt - 1));
      return true;
    }
  }

  public boolean equipperArmure() {
    int nbArmures = this.getListeAmures().size();

    Output.println("|" + this.getNom() + ", choisissez une armure à équipper :");
    for (int i = 1; i <= nbArmures; i++) {
      Output.println("|->[" + i + "] " + this.getListeAmures().get(i - 1).getNom());
    }
    Output.println("|->[0] Ne pas équipper");
    int userInt = Input.entier();

    if (userInt == 0 || userInt > nbArmures) {
      Output.println(this.getNom() + " n'a pas changé/équippé de nouvelle armure");
      return false;
    } else {
      Output.println(this.getNom() + " à équippé " + this.getListeAmures().get(userInt - 1).getNom());
      this.changeArmureEquipee(this.getListeAmures().get(userInt - 1));
      return true;
    }
  }

  public boolean ramasser() {
    if (this.leaveEquipement() != null) {
      Arme getArme = Catalogue.getArmeParNom(this.leaveEquipement().getNom());
      Armure getArmure = Catalogue.getArmureParNom(this.leaveEquipement().getNom());
      if (getArmure != null) {
        this.addArmure(getArmure);
        Output.println(this.getNom() + " a ramassé " + this.leaveEquipement().getNom() + ".");
        return true;
      }
      if (getArme != null) {
        this.addArme(getArme);
        Output.println(this.getNom() + " a ramassé " + this.leaveEquipement().getNom() + ".");
        return true;
      }
    }
    Output.println("Il n'y a rien sur la case où vous êtes!");
    return false;
  }

  @Override
  public String toString() {

    String pres = "Personnage : " + this.getNom() + "\n\nRace : " + this.getClasse().getNom() + "\nClasse : "
        + this.getRace().getNom();
    pres += "\nCaractéristiques :\n" + this.getCaracteristique();
    pres += "\nEquipement :\n";
    for (Arme a : listeArmes) {
      pres += " - " + a.toString() + "\n";
    }
    for (Armure a : listeArmures) {
      pres += " - " + a.toString() + "\n";
    }
    return pres;
  }
}
