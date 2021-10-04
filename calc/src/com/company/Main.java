package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добавить: 1, Отнять: 2");
        try {
            int a = scanner.nextInt();
            if (a == 1){
                System.out.println("Введите 1ое число");
                double num1 = scanner.nextDouble();
                System.out.println("Введите 2ое число");
                double num2 = scanner.nextDouble();
                System.out.println("Произведение цифр равна: " + sum(num1, num2));
            }else if (a == 2){
                System.out.println("Введите 1ое число");
                double num1 = scanner.nextDouble();
                System.out.println("Введите 2ое число");
                double num2 = scanner.nextDouble();
                System.out.println("Вычитание цифр равно: " + min(num1, num2));
            }else {
                System.out.println("Вы ввели хуйню а не 1 или 2");
            }
        }catch (InputMismatchException e){
            e.printStackTrace();
        }


    }

    public static double sum(double num1, double num){
        double sum = num + num1;
        return sum;
    }
    public static double min(double num1, double num){
        double min = num1 - num;
        return min;
    }
}
