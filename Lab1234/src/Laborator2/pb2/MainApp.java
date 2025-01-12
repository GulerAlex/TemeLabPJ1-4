package Laborator2.pb2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainApp {

    static class Vers {
        private String vers;

        public Vers(String vers) {
            this.vers = vers;
        }

        int nr_cuv() {
            int k = 0;
            for (int i = 0; i < vers.length(); i++) {
                if (vers.charAt(i) == ' ') {
                    k++;
                }
            }
            return k + 1;
        }

        int nr_voc() {
            int k = 0;
            String vocale = "aeiouAEIOU";
            for (int i = 0; i < vers.length(); i++) {
                if (vocale.indexOf(vers.charAt(i)) != -1) {
                    k++;
                }
            }
            return k;
        }

        boolean grupare(String grup) {
            return vers.substring(vers.length() - 2).equals(grup);
        }

        float majuscule() {
            Random rand = new Random();
            return rand.nextFloat();
        }
    }

    public static void main(String[] args) {
        List<Vers> versuri = new ArrayList<Vers>();
        try (Scanner scan = new Scanner(new File("src/Laborator2/pb2/cantec_in.txt"))) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine().toString();
                Vers vers_adaugat = new Vers(line);
                versuri.add(vers_adaugat);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            FileWriter writer = new FileWriter("src/Laborator2/pb2/cantec_out.txt");
            for (Vers vers : versuri) {
                if (vers.majuscule() < 0.1) {
                    writer.write(vers.vers.toUpperCase());
                } else {
                    writer.write(vers.vers);
                }

                if (vers.grupare("re")) {
                    writer.write("*");
                }

                writer.write(" " + vers.nr_cuv() + " " + vers.nr_voc() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

