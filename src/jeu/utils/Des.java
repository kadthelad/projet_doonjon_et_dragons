package jeu.utils;

import java.util.Random;

public class Des {
  static Random r = new Random();

  public static int lancer(int nombreDeDes, int nombreDeFaces) {
    int resultat = 0;
    for (int i = 0; i < nombreDeDes; i++) {
      resultat += r.nextInt(nombreDeFaces);
    }
    return resultat;
  }

  public static int lancer() {
    return lancer(1, 20);
  }
}
