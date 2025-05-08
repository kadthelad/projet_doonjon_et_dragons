package utils;

import java.util.Random;

public class Des {
  public int lancer(int nombreDeDes, int nombreDeFaces) {
    Random r = new Random();

    int resultat = 0;
    for (int i = 0; i < nombreDeDes; i++) {
      resultat += r.nextInt(nombreDeFaces);
    }
    return resultat;
  }
}
