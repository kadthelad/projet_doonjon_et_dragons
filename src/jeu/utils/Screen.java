package jeu.utils;

public class Screen {
  public static void clear() {
    try {
    System.out.print("\033\143");
      
    } catch (Exception e) {
    }
  }
}
