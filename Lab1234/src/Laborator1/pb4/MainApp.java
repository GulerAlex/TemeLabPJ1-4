package Laborator1.pb4;

import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        Random random = new Random();

        int a = random.nextInt(31);
        while(a == 0) a = random.nextInt(31);
        int b = random.nextInt(31);
        while(b == 0) b = random.nextInt(31);

        System.out.println("Numerele generate random: " + a + " " + b);

        while(a != b){
            if(a > b) a = a - b;
            else b = b- a;
        }

        System.out.println(" CMDC al celor doua numere: " + a);
    }
}