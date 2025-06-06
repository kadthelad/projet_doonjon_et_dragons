package jeu.utils;

import java.util.Scanner;

public class Input {

  private static Scanner scanner = new Scanner(System.in);

  public static int entier() {
    // on vérifie que l'utilisateur soumet bien un entier et pas autre chose qui
    // pourrait faire crash le programme
    while (!scanner.hasNextInt()) {
      System.out.print("Merci d'entrer un entier valide : ");
      scanner.next();
    }
    int entier = scanner.nextInt();
    scanner.nextLine();
    return entier;
  }

  public static int entier(int min, int max) {
    int n;
    do {
      n = entier();
      if (n < min || n > max)
        Output.println("Veuillez entrer un nombre entre " + min + " et " + max + ".");
    } while (n < min || n > max);
    return n;
  }

  public static int entier(int min, int max, String prompt) {
    int n;
    do {
      Output.print(prompt);
      n = entier();
      if (n < min || n > max)
        Output.println("Veuillez entrer un nombre entre " + min + " et " + max + ".");
    } while (n < min || n > max);
    return n;
  }

  public static boolean confirmer(String prompt) {
    Output.println(prompt + " [0] Oui [1] Non");
    int choix = entier(0, 1, "Votre choix : ");
    return choix == 0;
  }

  public static String string() {
    return scanner.nextLine();
  }

  public static String string(String prompt) {
    Output.print(prompt);
    return scanner.nextLine();
  }
}
