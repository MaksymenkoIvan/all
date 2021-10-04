package com.company;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int n = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            for (int j = 1; ;j *= 2) {
                n += (str.charAt(i) - '0') * j;
            }
        }
        System.out.println( str +  " - " + n);
    }
}
