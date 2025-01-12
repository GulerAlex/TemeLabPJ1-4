package Laborator1.pb3;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("n=");
        int n = scanner.nextInt();
        int k = 0;
        System.out.print("1");
        for(int i=2; i<=n/2; i++){
            if(n % i == 0){
                k++;
                System.out.print(i + " ");
            }
        }
        System.out.println(n);
        if(k == 0) System.out.print("Numar prim");
        scanner.close();
    }
}
