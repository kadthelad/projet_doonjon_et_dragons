package jeu.maitre;

import jeu.Creature;
import jeu.utils.Des;
import jeu.utils.Output;
import jeu.utils.Position;
import jeu.Caracteristique;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Monstre extends Creature {
  private String espece = "";
  private int numero = 0; // si il existe plusieurs monstres de la meme espèce

  private String nomAttaque = "";
  private String des = "";

  private Position position = new Position(0, 0);

  private String alias;

  {
    caracteristiques.put(Caracteristique.CLASSE_ARMURE, 0);
  }

  public Monstre(String esp, String nAtt, String att, Map<Caracteristique, Integer> mapCara, String alias, int numero) {
    this.espece = esp;
    this.nomAttaque = nAtt;
    this.des = att;
    this.caracteristiques = mapCara;
    this.alias = alias;
    this.numero = numero;
  }

  public Monstre copie() {
    Monstre m = new Monstre(this.espece, this.nomAttaque, this.des, this.caracteristiques, this.alias, this.numero);
    this.numero++;
    return m;
  }

  public static Monstre random() {
    Random rnd = new Random();
    Monstre m = orc();
    switch (rnd.nextInt(3)) {
      case 0 -> m = orc();
      case 1 -> m = dragon();
      case 2 -> m = arrachnide();
    }
    return m;
  }

  public static Monstre orc() {
    Map<Caracteristique, Integer> mapCara = new HashMap<>();
    mapCara.put(Caracteristique.PV, 10);
    mapCara.put(Caracteristique.FORCE, 5);
    mapCara.put(Caracteristique.INITIATIVE, 2);
    mapCara.put(Caracteristique.CLASSE_ARMURE, 13);
    mapCara.put(Caracteristique.VITESSE, 3);
    mapCara.put(Caracteristique.DEXTERITE, 0);

    return new Monstre("Orc", "Yolo", "1d8", mapCara, "rc", 0);
  }

  public static Monstre dragon() {
    Map<Caracteristique, Integer> mapCara = new HashMap<>();
    mapCara.put(Caracteristique.PV, 10);
    mapCara.put(Caracteristique.FORCE, 0);
    mapCara.put(Caracteristique.INITIATIVE, 0);
    mapCara.put(Caracteristique.CLASSE_ARMURE, 10);
    mapCara.put(Caracteristique.VITESSE, 0);
    mapCara.put(Caracteristique.DEXTERITE, 20);

    return new Monstre("Dragon", "Crache-Flamme", "3d6", mapCara, "dr", 0);
  }

  public static Monstre arrachnide() {
    Map<Caracteristique, Integer> mapCara = new HashMap<>();
    mapCara.put(Caracteristique.PV, 5);
    mapCara.put(Caracteristique.FORCE, 15);
    mapCara.put(Caracteristique.INITIATIVE, 20);
    mapCara.put(Caracteristique.CLASSE_ARMURE, 0);
    mapCara.put(Caracteristique.VITESSE, 15);
    mapCara.put(Caracteristique.DEXTERITE, 0);

    return new Monstre("Arrachnide", "ksss", "2d6", mapCara, "ar", 0);
  }

  public String getEspece() {
    /// Avoir l'espèce d'un Monstre ///
    return this.espece;
  }

  public String getNomAttaque() {
    return this.nomAttaque;
  }

  public String getAttaque() {
    return this.des;
  }

  public Map<Caracteristique, Integer> getMapCaracteristiques() {
    return this.caracteristiques;
  }

  public void setEspece(String esp) {
    this.espece = esp;
  }

  public void setAlias(String icn) {
    this.alias = icn;
  }

  public void setNomAtt(String nomAtt) {
    this.nomAttaque = nomAtt;
  }

  public void setSpeAtt(String speAtt) {
    this.des = speAtt;
  }

  public Position getPosition() {
    return this.position;
  }

  @Override
  public boolean attaque(Creature cible) {
    int d = distance(cible.getPosition());
    if (d > 1) {
      Output.print("La cible est trop loin (portée : " + 1 + " < distance :" + d + ")");
      return false;
    }
    int jet = Des.lancer(this.des);

    if (jet < cible.getCA()) {
      Output.print("Votre attaque ne touche pas ( jet + caracteristique : " + jet + " < classe d'amure de la cible :"
              + cible.getCA() + ")");
      return false;
    }

    cible.changePV(jet * -1);

    Output.print("Vous avez infligé " + jet + " degats à " + cible.getNom());

    return true;
  }

  public int getCA() {
    return this.caracteristiques.get(Caracteristique.CLASSE_ARMURE);
  }

  public String getNom() {
    return this.espece + this.numero;
  }

  @Override
  /// String override ///
  public String toString() {
    return "Monstre : " + this.espece +
        " (" + getAlias() + ")" +
        "\nAttaque : " + des +
        "\n___Caractéristiques___\n" +
        this.caracteristiques.get(Caracteristique.PV) + " PV\n" +
        this.caracteristiques.get(Caracteristique.FORCE) + " Force\n" +
        this.caracteristiques.get(Caracteristique.DEXTERITE) + " Déxterité\n" +
        this.caracteristiques.get(Caracteristique.VITESSE) + " Vitesse\n" +
        this.caracteristiques.get(Caracteristique.INITIATIVE) + " Initiative\n" +
        this.caracteristiques.get(Caracteristique.CLASSE_ARMURE) + " Classe d'Armure\n";
  }

  public void changePV(int pv) {
    this.caracteristiques.replace(Caracteristique.PV, this.caracteristiques.get(Caracteristique.PV) + pv);
  }

  @Override
  public String getAlias() {
    return this.alias + this.numero;
  }

  public void setCaracteristique(Caracteristique c, int v) {
    this.caracteristiques.put(c, v);
  }

  public int getCaracteristique(Caracteristique c) {
    return this.caracteristiques.get(c);
  }

  public void changeCaracteristiqueMap(Map<Caracteristique, Integer> map) {
    this.caracteristiques = map;
  }

  public boolean equipperArme()
  {
    Output.println("Cette créature ne peut pas équipper d'arme !");
    return false;
  }

  public boolean equipperArmure()
  {
    Output.println("Cette créature ne peut pas équipper d'armure !");
    return false;
  }

  public boolean ramasser(Carte carte)
  {
    Output.println("Cette créature ne peut pas équipper d'armure !");
    return false;
  }
}
