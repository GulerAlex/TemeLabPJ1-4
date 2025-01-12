package Laborator1.pb2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        int minim=Integer.MAX_VALUE;
        int maxim=Integer.MIN_VALUE;
        int suma=0;
        int contor=0;
        try(Scanner scanner=new Scanner(new File("src/Laborator1/pb2/in.txt"))) {
            while(scanner.hasNextInt()) {
                int nr=scanner.nextInt();
                suma+=nr;
                if(minim>nr) minim=nr;
                if(maxim<nr) maxim=nr;
                contor++;
            }
        }
        catch(FileNotFoundException e) {throw new RuntimeException(e);}
        try{
            FileWriter writer=new FileWriter("src/Laborator1/pb2/out.txt");
            writer.write("Suma: "+suma+"\n");
            writer.write("Valoarea minima: "+minim+"\n");
            writer.write("Valoarea maxima: "+maxim+"\n");
            writer.write("Media: "+(float)suma/contor+"\n");
            writer.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}