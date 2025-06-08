package utils;

import java.util.Scanner;

public class Input {

  private static Scanner scanner = new Scanner(System.in);

  public static int entier() {
    // on vérifie que l'utilisateur soumet bien un entier et pas autre chose qui
    // pourrait faire crash le programme
    while (!scanner.hasNextInt()) {
      System.out.print("Merci d'entrer une proposition valide : ");
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

  public static int entierChar(String prompt) {
    String input;
    do {
        input = string(prompt);
    } while (input.isEmpty() ||
        (input.charAt(0) < 65 ||
        input.charAt(0) > 90));
    return input.charAt(0) - 65;
  }

  public static int entierChar(int max, String prompt) {
    String input;
    do {
        input = string(prompt);
    } while ( input.isEmpty() || 
        (input.charAt(0) < 65 ||
        input.charAt(0) > 65 + max));
    return input.charAt(0) - 65;
  }

  public static boolean confirmer(String prompt) {
    Output.println(prompt + " [1] Oui [2] Non");
    int choix = entier(1, 2, "Votre choix : ");
    return choix == 1;
  }

  public static String string() {
    return scanner.nextLine();
  }

  public static String string(String prompt) {
    Output.print(prompt);
    return scanner.nextLine();
  }
}
