package Laborator1.pb1;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("lungime=");
        int a=scanner.nextInt();
        System.out.println("latime=");
        int b=scanner.nextInt();
        int perimetru=2*(a+b);
        int arie=a*b;
        System.out.println("perimetru="+perimetru);
        System.out.println("arie="+arie);
        scanner.close();
    }
}
