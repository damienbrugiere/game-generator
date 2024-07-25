import map.Map;
import map.MapGenerator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Map map = MapGenerator.builder()
                .generateV2();
        map.displayMap();
        System.out.println("Appuyez sur Entrée pour continuer...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Attend que l'utilisateur appuie sur Entrée
        System.out.println("Continuer...");
        scanner.close();
    }
}
