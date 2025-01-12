package Laborator4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    // Enumuri refactorizate
    public enum StareEchipament {
        achizitionat, expus, vandut;
    }

    public enum ModTiparire {
        color, alb_negru;
    }

    public enum FormatCopiere {
        A3, A4;
    }

    public enum SistemOperare {
        windows, linux;
    }

    // Clasa abstracta pentru echipamente
    public abstract static class Echipament implements Serializable {
        protected String denumire;
        protected int nrInv;
        protected double pret;
        protected String zonaMag;
        protected StareEchipament stare;
        protected String tip;

        public Echipament(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare, String tip) {
            this.denumire = denumire;
            this.nrInv = nrInv;
            this.pret = pret;
            this.zonaMag = zonaMag;
            this.stare = stare;
            this.tip = tip;
        }

        @Override
        public String toString() {
            return String.format("Echipament: %s, Nr. Inventar: %d, Preț: %.2f, Zona: %s, Stare: %s",
                    denumire, nrInv, pret, zonaMag, stare);
        }

        public abstract void afiseazaDetalii();
    }

    // Imprimanta
    public static class Imprimanta extends Echipament {
        private int ppm;
        private String dpi;
        private int pCar;
        private ModTiparire modTiparire;

        public Imprimanta(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                          int ppm, String dpi, int pCar, ModTiparire modTiparire) {
            super(denumire, nrInv, pret, zonaMag, stare, "imprimanta");
            this.ppm = ppm;
            this.dpi = dpi;
            this.pCar = pCar;
            this.modTiparire = modTiparire;
        }

        @Override
        public void afiseazaDetalii() {
            System.out.println(this);
            System.out.println(String.format("PPM: %d, DPI: %s, Pagini/Cartuș: %d, Mod Tipărire: %s",
                    ppm, dpi, pCar, modTiparire));
        }
    }

    // Copiator
    public static class Copiator extends Echipament {
        private int pTon;
        private FormatCopiere formatCopiere;

        public Copiator(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                        int pTon, FormatCopiere formatCopiere) {
            super(denumire, nrInv, pret, zonaMag, stare, "copiator");
            this.pTon = pTon;
            this.formatCopiere = formatCopiere;
        }

        @Override
        public void afiseazaDetalii() {
            System.out.println(this);
            System.out.println(String.format("Pagini/Toner: %d, Format Copiere: %s", pTon, formatCopiere));
        }
    }

    // Sistem de calcul
    public static class SistemCalcul extends Echipament {
        private String tipMon;
        private double vitProc;
        private int cHdd;
        private SistemOperare sistemOperare;

        public SistemCalcul(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                            String tipMon, double vitProc, int cHdd, SistemOperare sistemOperare) {
            super(denumire, nrInv, pret, zonaMag, stare, "sistem de calcul");
            this.tipMon = tipMon;
            this.vitProc = vitProc;
            this.cHdd = cHdd;
            this.sistemOperare = sistemOperare;
        }

        @Override
        public void afiseazaDetalii() {
            System.out.println(this);
            System.out.println(String.format("Tip Monitor: %s, Viteză Procesor: %.2f GHz, Capacitate HDD: %d GB, Sistem Operare: %s",
                    tipMon, vitProc, cHdd, sistemOperare));
        }
    }

    // Funcții de serializare și deserializare
    static void scrie(Object o, String fis) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fis))) {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Object citeste(String fis) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fis))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Main
    public static void main(String[] args) {
        List<Echipament> echipamente = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Citire date echipamente din fisier
        try (Scanner sc = new Scanner(new File("src/Laborator4/echipamente.txt"))) {
            while (sc.hasNext()) {
                String[] data = sc.nextLine().split(";");
                String denumire = data[0];
                int nrInv = Integer.parseInt(data[1]);
                double pret = Double.parseDouble(data[2]);
                String zonaMag = data[3];
                StareEchipament stare = StareEchipament.valueOf(data[4]);
                String tip = data[5];

                switch (tip) {
                    case "imprimanta":
                        echipamente.add(new Imprimanta(denumire, nrInv, pret, zonaMag, stare,
                                Integer.parseInt(data[6]), data[7], Integer.parseInt(data[8]),
                                ModTiparire.valueOf(data[9])));
                        break;
                    case "copiator":
                        echipamente.add(new Copiator(denumire, nrInv, pret, zonaMag, stare,
                                Integer.parseInt(data[6]), FormatCopiere.valueOf(data[7])));
                        break;
                    case "sistem de calcul":
                        echipamente.add(new SistemCalcul(denumire, nrInv, pret, zonaMag, stare, data[6],
                                Double.parseDouble(data[7]), Integer.parseInt(data[8]),
                                SistemOperare.valueOf(data[9])));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Meniu principal

        while (true) {
            System.out.println("\nMeniu:");
            System.out.println("1. Afișarea tuturor echipamentelor");
            System.out.println("2. Afișarea imprimantelor");
            System.out.println("3. Afișarea copiatoarelor");
            System.out.println("4. Afișarea sistemelor de calcul");
            System.out.println("5. Modificarea stării unui echipament");
            System.out.println("6. Setarea modului de tipărire pentru imprimante");
            System.out.println("7. Setarea formatului de copiere pentru copiatoare");
            System.out.println("8. Instalarea sistemului de operare pentru sisteme de calcul");
            System.out.println("9. Afișarea echipamentelor vândute");
            System.out.println("10. Serializarea colecției de echipamente");
            System.out.println("0. Ieșire");

            System.out.print("Introduceti optiunea dorita: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    echipamente.forEach(Echipament::afiseazaDetalii);
                    break;
                case 2:
                    for(Echipament echipament : echipamente){
                        if(echipament.tip.equals("imprimanta"))
                            echipament.afiseazaDetalii();
                    }
                    break;
                case 3:
                    for(Echipament echipament : echipamente){
                        if(echipament.tip.equals("copiator"))
                            echipament.afiseazaDetalii();
                    }
                    break;
                case 4:
                    for(Echipament echipament : echipamente){
                        if(echipament.tip.equals("sistem de calcul"))
                            echipament.afiseazaDetalii();
                    }
                    break;
                case 5:
                    System.out.print("Introduceti denumirea echipamentului: ");
                    String denumire_e = scanner.nextLine();
                    System.out.print("Introduceti noua stare (1-Achizitionat, 2-Expus, 3-Vandut): ");
                    int stareNoua_e = scanner.nextInt();
                    StareEchipament stareEchipament = StareEchipament.values()[stareNoua_e - 1];
                    echipamente.stream().filter(e -> e.denumire.equals(denumire_e))
                            .forEach(e -> e.stare = stareEchipament);
                    break;
                case 6:
                    System.out.print("Introduceti denumirea imprimantei: ");
                    String denumire_i = scanner.nextLine();
                    System.out.print("Introduceti noul mod de scriere : 1-color 2-alb_negru: ");
                    int stareNoua_i = scanner.nextInt();
                    ModTiparire modTiparire = ModTiparire.values()[stareNoua_i - 1];
                    echipamente.stream()
                            .filter(e -> e instanceof Imprimanta && e.denumire.equals(denumire_i))
                            .forEach(e -> ((Imprimanta) e).modTiparire = modTiparire);
                    break;
                case 7:
                    System.out.print("Introduceti denumirea copiatorului: ");
                    String denumire_c = scanner.nextLine();
                    System.out.print("Introduceti noul format de copiere 1-A3 2-A4: ");
                    int stareNoua_c = scanner.nextInt();
                    FormatCopiere formatCopiere = FormatCopiere.values()[stareNoua_c - 1];
                    echipamente.stream()
                            .filter(e -> e instanceof Copiator && e.denumire.equals(denumire_c))
                            .forEach(e -> ((Copiator) e).formatCopiere = formatCopiere);
                    break;
                case 8:
                    System.out.print("Introduceti denumirea sistemului de calcul: ");
                    String denumire_s = scanner.nextLine();
                    System.out.print("Ce sistem de operare? 1-Windows 2-Linux: ");
                    int stareNoua_s = scanner.nextInt();
                    SistemOperare sistemOperare = SistemOperare.values()[stareNoua_s - 1];
                    echipamente.stream()
                            .filter(e -> e instanceof SistemCalcul && e.denumire.equals(denumire_s))
                            .forEach(e -> ((SistemCalcul) e).sistemOperare = sistemOperare);
                    break;
                case 9:
                    echipamente.stream().filter(e -> e.stare == StareEchipament.vandut).forEach(Echipament::afiseazaDetalii);
                    break;
                default:
                    System.out.println("Opțiune invalidă!");
                    break;
            }
        }
    }
}

