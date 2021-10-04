package com.company;

public class Main {

    public static void main(String[] args) {
        int numbers[] = { 15, 11, 1, 54, 43, 123, 12 };
        int n = numbers.length;
        int temp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }
}
