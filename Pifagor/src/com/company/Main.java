package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double katet1;
        double katet2;
        double gipotenyza;
        System.out.println("Write 1 if u need to calculate gipotenuyzy, 2 if u need to calc katet");
        int a = scanner.nextInt();
        if (a == 1){
            System.out.println("Enter 1-st katet");
            katet1 = scanner.nextDouble();
            System.out.println("Enter 2-nd katet");
            katet2 = scanner.nextDouble();
            gipotenyza = Math.sqrt((katet1*katet1)+(katet2*katet2));
            System.out.println("gipotenyza = " + gipotenyza);
        }else if (a == 2){
            System.out.println("Enter gipotenyzy");
            gipotenyza = scanner.nextDouble();
            System.out.println("Enter katet");
            katet1 = scanner.nextDouble();
            katet2 = Math.sqrt((gipotenyza*gipotenyza)-(katet1*katet1));
            System.out.println("2-nd katet = " + katet2);
        }else {
            System.out.println("You wrote incorrect number");
        }
    }
}
