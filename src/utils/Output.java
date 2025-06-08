package utils;

public class Output {
  public static void print(String ligne) {
    System.out.print(ligne);
  }

  public static void println(String ligne) {
    System.out.println(ligne);
  }

  public static void println() {
    System.out.println();
  }

  public static void printValid() {
    System.out.println("Veuillez entrer une entrez une commande valide!");
  }

  public static void clear() {
    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + "\033\143");
  }
}
