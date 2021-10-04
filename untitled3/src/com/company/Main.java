package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int numbers[] = { 15, 11, 1, 54, 43, 123, 12 };
        int n = numbers.length;
        int temp;
        for (int i = 0; i < n - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                temp = numbers[i];
                numbers[i] = numbers[i + 1];
                numbers[i + 1] = temp;
            }
        }
        System.out.println(Arrays.toString(numbers));
    }
}