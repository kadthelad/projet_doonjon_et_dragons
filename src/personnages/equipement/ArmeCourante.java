package personnages.equipement;

public class ArmeCourante extends Arme {
  public ArmeCourante(String nom, String degats, int portee) {
    super(nom, degats, portee);
  }

  public ArmeCourante copie() {
    return new ArmeCourante(this.nom, this.degats, this.portee);
  }
}
