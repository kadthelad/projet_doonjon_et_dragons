package jeu.utils;

import java.util.Scanner;

public class Input {

  private static Scanner sc = new Scanner(System.in);

  public static int entier() {
    int n = sc.nextInt();
    sc.nextLine();
    return n;
  }

  public static String string() {
    return sc.nextLine();
  }
}
