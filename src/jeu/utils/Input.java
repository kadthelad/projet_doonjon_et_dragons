package jeu.utils;

import java.util.Scanner;

public class Input {

  private static Scanner scanner = new Scanner(System.in);

  public static int entier() {
    // on verifie que l'utilisateur soumet bien un entier et pas autre chose qui
    // pourrait faire crash le programme
    while (!scanner.hasNextInt()) {
      System.out.print("Merci d'entrer un entier valide : ");
      scanner.next();
    }
    return scanner.nextInt();
  }

  public static String string() {
    return scanner.nextLine();
  }
}
