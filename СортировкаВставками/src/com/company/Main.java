package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int [] array = {54,1123,65,34,-1,66,-6,8,0,11};
        for (int left = 0; left < array.length; left++) {
            int value = array[left];
            int i = left-1;
            for (; i >= 0; i--) {
                if (value < array[i]){
                    array[i+1] = array[i];
                }else {
                    break;
                }
            }
            array[i+1] = value;
        }
        System.out.println(Arrays.toString(array));
    }
    public static void swap(int[] array, int a, int b){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
