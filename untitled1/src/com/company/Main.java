package com.company;

import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 число");
        int a = scanner.nextInt();
        System.out.println("Введите 1 число");
        int b = scanner.nextInt();
        System.out.println("Сумма равна: " + sum(a, b));
    }

    public static int sum(int a, int b) {
        return a + b;
    }
}
