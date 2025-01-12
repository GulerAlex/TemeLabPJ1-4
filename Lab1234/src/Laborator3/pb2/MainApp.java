package Laborator3.pb2;

import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;

public class MainApp {
    static class Produs {
        private String numeProdus;
        private int cantitateProdus;
        private float pretProdus;
        private LocalDate dataExpirare;
        public static float incasari = 0;

        public Produs(String numeProdus, int cantitateProdus, float pretProdus, LocalDate dataExpirare) {
            this.numeProdus = numeProdus;
            this.cantitateProdus = cantitateProdus;
            this.pretProdus = pretProdus;
            this.dataExpirare = dataExpirare;
        }

        public String getNumeProdus() {
            return numeProdus;
        }

        public int getCantitateProdus() {
            return cantitateProdus;
        }

        public float getPretProdus() {
            return pretProdus;
        }

        public LocalDate getDataExpirare() {
            return dataExpirare;
        }

        public void setCantitate(int cantitateProdus) {
            this.cantitateProdus = cantitateProdus;
        }

        public void setIncasari(float incasari) {
            this.incasari += incasari;
        }
    }

    public static void main(String[] args) throws IOException {
        String numeProdus;
        int cantitateProdus;
        float pretProdus;
        LocalDate dataExpirare;
        ArrayList<Produs> produse = new ArrayList<Produs>();

        // Citire produse din fisier
        try (Scanner sc = new Scanner(new File("src/Laborator3/pb2/produse.csv"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                numeProdus = data[0];
                pretProdus = Float.parseFloat(data[1]);
                cantitateProdus = Integer.parseInt(data[2]);
                dataExpirare = LocalDate.parse(data[3]);
                produse.add(new Produs(numeProdus, cantitateProdus, pretProdus, dataExpirare));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Meniu
        System.out.println("Meniul de forma:");
        System.out.println("0.Exit");
        System.out.println("1.Afisare produse ");
        System.out.println("2.Afisare produse expirate ");
        System.out.println("3.Vanzare produs ");
        System.out.println("4.Afisare produse cu pret minim ");
        System.out.println("5.Salvare produse cu o cantitate mai mica decat una introdusa, in fisier");
        System.out.print("Introduceti optiunea dorita: ");

        Scanner sc = new Scanner(System.in);
        int opt = sc.nextInt();

        do {
            switch (opt) {
                default:
                    System.out.println("Optiunea invalida!");
                    break;

                case 0:
                    exit(0);

                case 1:
                    for (Produs prod : produse) {
                        System.out.println(prod.getNumeProdus() + " " + prod.getCantitateProdus() + " " +
                                prod.getPretProdus() + " " + prod.getDataExpirare());
                    }
                    break;

                case 2:
                    for (Produs prod : produse)
                        if (prod.getDataExpirare().isBefore(LocalDate.now())) {
                            System.out.println(prod.getNumeProdus() + " " + prod.getCantitateProdus() + " " +
                                    prod.getPretProdus() + " " + prod.getDataExpirare());
                        }
                    break;

                case 3:
                    boolean ok = true;
                    String nume_vandut;
                    do {
                        System.out.print("Nume produs vandut: ");
                        nume_vandut = sc.next();
                        for (Produs prod : produse)
                            if (prod.getNumeProdus().equals(nume_vandut)) {
                                ok = false;
                            }
                        if (ok) System.out.println("Nu s-a gasit produsul! Reincercati!");
                    } while (ok);

                    System.out.print("Cantitatea dorita: ");
                    int cant_vanduta = sc.nextInt();
                    for (Produs prod : produse) {
                        if (prod.getNumeProdus().equals(nume_vandut))
                            if (prod.getCantitateProdus() >= cant_vanduta) {
                                prod.setIncasari(prod.getPretProdus() * cant_vanduta);
                                prod.setCantitate(prod.getCantitateProdus() - cant_vanduta);
                                if (prod.getCantitateProdus() == 0) {
                                    produse.remove(prod);
                                    break;
                                }
                            } else {
                                System.out.println("Cantitatea dorita este prea mare!");
                            }
                    }
                    System.out.println("Incasari totale: " + Produs.incasari);
                    break;

                case 4:
                    float minim = Float.MAX_VALUE;
                    for (Produs prod : produse)
                        if (prod.getPretProdus() < minim)
                            minim = prod.getPretProdus();
                    for (Produs prod : produse)
                        if (prod.getPretProdus() == minim)
                            System.out.println(prod.getNumeProdus() + " " + prod.getCantitateProdus() + " " +
                                    prod.getPretProdus() + " " + prod.getDataExpirare());
                    break;

                case 5:
                    try {
                        FileWriter fw = new FileWriter("src/Laborator3/pb2/produse_salvate.txt");
                        System.out.print("Introduceti cantitatea dorita: ");
                        int cant_dorita = sc.nextInt();
                        for (Produs prod : produse)
                            if (prod.getCantitateProdus() < cant_dorita) {
                                fw.write(prod.getNumeProdus() + " " + prod.getCantitateProdus() + " " + prod.getPretProdus() + " "
                                        + prod.getDataExpirare() + "\n");
                            }
                        fw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
            System.out.print("Introduceti optiunea dorita: ");
            opt = sc.nextInt();
        } while (true);
    }
}

