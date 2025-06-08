package utils;

import java.util.Random;

public class Des {
  static Random r = new Random();

  public static int lancer(int nombreDeDes, int nombreDeFaces) {
    int resultat = 0;
    for (int i = 0; i < nombreDeDes; i++) {
      resultat += r.nextInt(nombreDeFaces + 1) + 1;
    }
    return resultat;
  }

  public static int lancer() {
    return lancer(1, 20);
  }

  public static int lancer(String degats) {
    String arr[] = degats.split("d");
    int nombreDeDes, nombreDeFaces;
    try {
      nombreDeDes = Integer.parseInt(arr[0]);
      nombreDeFaces = Integer.parseInt(arr[1]);

    } catch (Exception e) {
      System.out.println("Merci d'entrer un format de lancer valide : '2d4' pour 2 des de 4 faces.");
      return -1;
    }
    return lancer(nombreDeDes, nombreDeFaces);
  }
}
