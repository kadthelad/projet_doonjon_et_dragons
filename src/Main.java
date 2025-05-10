import joueurs.*;
import joueurs.races.*;
import joueurs.classes.*;
import joueurs.equipements.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

  // declare des listes pour facilement keep track des persos, races et classes
  private static ArrayList<Personnage> listePersonnages;
  private static ArrayList<Race> listeRaces = new ArrayList<>(
      Arrays.asList(new Nain()));
  private static ArrayList<Classe> listeClasses = new ArrayList<Classe>(
      // on ne peut pas appeler de methodes en dehors d'un bloc, donc pas de liste.add
      // directement dans la classe Main
      Arrays.asList(new Clerc(), new Guerrier(), new Magicien(), new Roublard()));

  private static Scanner sc = new Scanner(System.in);

  public static void main(String args[]) {
    System.out.println("Bienvenue dans DOOnjon et Dragons");
    creationPersonnages(combienJoueurs());

  }

  public static int combienJoueurs() {
    System.out.println("Sans compter le maitre du jeu, combien de joueurs y aura-t-il ?");
    int n = sc.nextInt();
    sc.nextLine();
    return n;
  }

  public static int creationPersonnages(int nombreJoueurs) {

    for (int i = 0; i < nombreJoueurs; i++) {
      System.out.println("Joueurs " + (i + 1) + ", nommez votre personnage");
      String nom = sc.nextLine();

      System.out.println(nom + ", entrer le numéro associé à la race de votre choix :");
      for (int j = 0; j < listeRaces.size(); j++) {
        // print le nom de la race et ses charactéristiques spécifiques
        System.out.print(j + " : " + listeRaces.get(j).presentation());
      }
      int race = sc.nextInt();

      System.out.println(nom + ", entrer le numéro associé à la classe de votre choix :");
      for (int j = 0; j < listeClasses.size(); j++) {
        // print le nom de la classe et ses charactéristiques spécifiques
        System.out.print(j + " : " + listeClasses.get(j).presentation() + "\n");
      }
      int classe = sc.nextInt();

      listePersonnages.add(new Personnage(nom, listeRaces.get(race), listeClasses.get(classe)));
    }

    return 1;
  }

  public static ArrayList<Personnage> getListePersonnages() {
    return listePersonnages;
  }
}
