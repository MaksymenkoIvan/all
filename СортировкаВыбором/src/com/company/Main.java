package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = {54, 1123, 65, 34, -1, 66, -6, 8, 0, 11};
//        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));
        for (int left = 0; left < array.length; left++) {
            int min = left;
            for (int i = left; i < array.length; i++) {
                if (array[i] < array[min]){
                    min = i;
                }
            }swap(array,left,min);
        }
        System.out.println(Arrays.toString(array));
    }
    public static void swap(int[] array, int a, int b){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
