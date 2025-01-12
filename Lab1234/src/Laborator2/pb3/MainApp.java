package Laborator2.pb3;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        // Crearea unui obiect Scanner pentru citirea datelor de la tastatură
        Scanner scanner = new Scanner(System.in);

        // Citirea șirului original și conversia acestuia într-un StringBuilder
        System.out.print("Introduceți șirul original: ");
        StringBuilder originalString = new StringBuilder(scanner.nextLine());

        // Citirea șirului care va fi inserat
        System.out.print("Introduceți șirul care va fi inserat: ");
        String insertString = scanner.nextLine();

        // Citirea poziției la care va fi inserat șirul
        System.out.print("Introduceți poziția de inserare: ");
        int insertPosition = scanner.nextInt();
        scanner.nextLine(); // Consumăm linia rămasă după citirea unui int

        // Inserarea șirului la poziția dată
        originalString.insert(insertPosition, insertString);
        System.out.println("Șirul după inserare: " + originalString);

        // Citirea poziției și lungimii porțiunii de șters
        System.out.print("Introduceți poziția de început pentru ștergere: ");
        int deletePosition = scanner.nextInt();

        System.out.print("Introduceți numărul de caractere de șters: ");
        int deleteLength = scanner.nextInt();

        // Ștergerea porțiunii din șir
        originalString.delete(deletePosition, deletePosition + deleteLength);
        System.out.println("Șirul după ștergere: " + originalString);

        // Închidem scanner-ul
        scanner.close();
    }
}

