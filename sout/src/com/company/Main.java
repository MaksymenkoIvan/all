package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите возраст: ");
        int age = scanner.nextInt();
        System.out.println("Введите имя: ");
	    String name = scanner.next();
        System.out.println("I am " + age + " y.o. My name is " + name + ".");
    }
}
