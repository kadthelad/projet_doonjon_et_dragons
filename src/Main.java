import java.util.Scanner;

public class Main {

  private static Scanner sc = new Scanner(System.in);

  private static int nombreJoueurs = 0;

  public static void main(String args[]) {
    System.out.println("Bienvenue dans DOOnjon et Dragons");
    nombreJoueurs = combienJoueurs();
  }

  public static int combienJoueurs() {
    System.out.println("Sans compter le maitre du jeu, combien de joueurs y aura-t-il ?");
    int n = sc.nextInt();
    return n;
  }
}
