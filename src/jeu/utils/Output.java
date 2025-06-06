package jeu.utils;

import jeu.utils.Position;

public class Output {
  public static void print(String ligne) {
    System.out.print(ligne);
  }

  public static void println(String ligne) {
    System.out.println(ligne);
  }

  public static void printValid() {
    System.out.println("Veuillez entrer une entrez une commande valide!");
  }

  public static void clear() {
    try {
      System.out.print("\033\143");
    } catch (Exception e) {
    }
  }

  public static Position askPosition()
  {
    Output.print("X = ");
    int userX = Input.entier();
    Output.print("Y = ");
    int userY = Input.entier();

    return new Position(userX, userY);
  }
}
