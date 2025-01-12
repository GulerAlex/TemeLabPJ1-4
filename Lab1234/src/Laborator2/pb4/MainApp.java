package Laborator2.pb4;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    // Clasa Persoana
    static class Persoana {
        private String nume;
        private String cnp;

        // Constructor
        public Persoana(String nume, String cnp) {
            this.nume = nume;
            this.cnp = cnp;
        }

        // Gettere și settere
        public String getNume() {
            return nume;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public String getCnp() {
            return cnp;
        }

        public void setCnp(String cnp) {
            this.cnp = cnp;
        }

        // Metodă pentru calcularea vârstei
        public long getVarsta() {
            int zi = Integer.parseInt(cnp.substring(5, 7));
            int luna = Integer.parseInt(cnp.substring(3, 5));
            int an = Integer.parseInt(cnp.substring(1, 3));
            int sex = Integer.parseInt(cnp.substring(0, 1));

            if (sex == 1 || sex == 2) {
                an += 1900; // Anul nașterii pentru bărbați și femei
            } else {
                an += 2000; // Anul nașterii pentru altă categorie
            }

            LocalDate dataNasterii = LocalDate.of(an, luna, zi);
            return ChronoUnit.YEARS.between(dataNasterii, LocalDate.now()); // Calcularea vârstei
        }
    }

    // Metodă pentru validarea CNP-ului
    static boolean validareCnp(String cnp) {
        if (cnp.length() != 13) {
            return false; // Verifică lungimea CNP-ului
        }

        // Verifică dacă CNP-ul conține doar cifre
        for (int i = 0; i < cnp.length(); i++) {
            if (!Character.isDigit(cnp.charAt(i))) {
                return false;
            }
        }

        // Verifică dacă primul caracter este valid
        char primulChar = cnp.charAt(0);
        if (primulChar == '0' || primulChar == '3' || primulChar == '4' || primulChar == '7' || primulChar == '8' || primulChar == '9') {
            return false;
        }

        // Verifică cifra de control
        int suma = 0;
        String cifreControl = "279146358279"; // Secvența pentru calculul sumei
        for (int i = 0; i < 12; i++) {
            suma += (Character.getNumericValue(cnp.charAt(i)) * Character.getNumericValue(cifreControl.charAt(i)));
        }

        int ultimaCifra = Character.getNumericValue(cnp.charAt(12));
        return ultimaCifra == (suma % 11 == 10 ? 1 : suma % 11); // Verifică dacă cifra de control este validă
    }

    public static void main(String[] args) {
        List<Persoana> persoane = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.print("Numarul de persoane: ");
        int n = sc.nextInt();
        String nume, cnp;
        sc.nextLine(); // Consumă newline-ul rămas după citirea unui întreg

        // Introducerea persoanelor
        for (int i = 0; i < n; i++) {
            System.out.print("Nume/Prenume: ");
            nume = sc.nextLine();
            do {
                System.out.print("CNP: ");
                cnp = sc.nextLine();
            } while (!validareCnp(cnp)); // Continuă până la un CNP valid
            persoane.add(new Persoana(nume, cnp));
        }

        // Afișarea detaliilor persoanelor
        for (Persoana persoana : persoane) {
            System.out.print(persoana.getNume() + " ");
            System.out.print(persoana.getCnp() + " ");
            System.out.println(persoana.getVarsta()); // Afișează vârsta calculată
        }

        sc.close(); // Închide scanner-ul
    }
}

