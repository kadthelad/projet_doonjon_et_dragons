package entite;

import java.util.HashMap;
import java.util.Map;

import personnages.equipement.Catalogue;
import personnages.equipement.Equipement;
import utils.Output;
import utils.Position;

public abstract class Creature extends Entite
{

  protected Map<Caracteristique, Integer> caracteristiques = new HashMap<>();
  {
    caracteristiques.put(Caracteristique.FORCE, 0);
    caracteristiques.put(Caracteristique.DEXTERITE, 0);
    caracteristiques.put(Caracteristique.VITESSE, 0);
    caracteristiques.put(Caracteristique.INITIATIVE, 0);
    caracteristiques.put(Caracteristique.PV, 0);
  }

  Entite surEquipement = null;

  public boolean move(Position p) {
    jeu.Carte carte = jeu.Donjon.getCarte();
    Entite e = carte.getEntite(p);
    if (!carte.inCarte(p))
      return false;
    if (this.distance(p) > this.caracteristiques.get(Caracteristique.VITESSE)/3)
      return false;
    if ((!e.isEmpty()) && (Catalogue.getArmeParNom(e.getNom()) != null || Catalogue.getArmureParNom(e.getNom()) != null))
    {
      if (this.leaveEquipement() != null)
      {
        Output.println("[DEBUG] leaving equipement : this.surequipement = " + this.leaveEquipement().getNom() + " at " + this.position.toString());
        carte.ajouteEntite(this.leaveEquipement(), this.position);
      }
      this.stepOnEquipement(e);
      Output.println("[DEBUG] stepping on equipement : this.surequipement = " + this.leaveEquipement().getNom());
    }
    else if (!e.isEmpty())
    {
      return false;
    }
    else
    {
      // dans le cas ou deux objets sont cote a cote, ceci empeche que l'un disparaisse
      if (this.leaveEquipement() != null)
      {
        Output.println("[DEBUG] leaving equipement : this.surequipement = " + this.leaveEquipement().getNom() + " at " + this.position.toString());
        carte.ajouteEntite(this.leaveEquipement(), this.position);
      }
    }


    carte.ajouteEntite(this, p); // gère tout (retrait ancien + placement nouveau)
    return true;
  }

  public void stepOnEquipement(Entite eqpt)
  {
    this.surEquipement = eqpt;
  }

  public Entite leaveEquipement()
  {
    return this.surEquipement;
  }

  public abstract int getCaracteristique(Caracteristique c);

  public abstract void setCaracteristique(Caracteristique c, int v);

  public abstract boolean attaque(Creature cible);

  public abstract boolean equipperArme();

  public abstract boolean equipperArmure();

  public abstract boolean ramasser(jeu.Carte carte);

  public abstract int getCA();

  public abstract void changePV(int pv);
}
