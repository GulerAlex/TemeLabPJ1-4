package Laborator2.pb1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        String[] judete;

        try (Scanner fileScanner = new Scanner(new File("src/Laborator2/Laborator3.pb1/judete_in.txt"))) {
            if (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                judete = line.split(",\s*");
            } else {
                System.err.println("Fisierul este gol.");
                return;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Eroare la citirea fisierului: " + e.getMessage());
            return;
        }

        Arrays.sort(judete);

        System.out.println("Judete ordonate:");
        for (String judet : judete) {
            System.out.println(judet);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti judetul cautat: ");
        String judetCautat = scanner.nextLine().trim();

        int pozitie = Arrays.binarySearch(judete, judetCautat);

        if (pozitie >= 0) {
            System.out.println("Judetul \"" + judetCautat + "\" se afla pe pozitia " + (pozitie + 1) + " in vectorul ordonat.");
        } else {
            System.out.println("Judetul \"" + judetCautat + "\" nu a fost gasit in lista.");
        }

        scanner.close();
    }
}
