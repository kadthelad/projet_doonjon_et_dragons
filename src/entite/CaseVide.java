package entite;

public class CaseVide extends Entite {
  
  public String getAlias() {
    return " . ";
  }

  public String getNom() {
    return "Case vide";
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
