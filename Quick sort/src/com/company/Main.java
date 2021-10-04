package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int a [] = {1,32,-3,11,-42,111,-634,12,999,-777};
        quickSort(a, 0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
    public static void quickSort(int[] a, int leftBorder, int rightBorder){
        int leftMark = leftBorder;
        int rightMark = rightBorder;
        int temp = a[(leftMark+rightMark)/2];
        do{
            while (a[leftMark] < temp){
                leftMark++;
            }
            while (a[rightMark] > temp){
                rightMark--;
            }
            if(leftMark <= rightMark){
                if(leftMark < rightMark){
                    swap(a,leftMark,rightMark);
                }
                leftMark++;
                rightMark--;
            }
        }while (leftMark <= rightMark);
        if(leftMark < rightBorder){
            quickSort(a,leftMark,rightBorder);
        }
        if(leftBorder < rightMark){
            quickSort(a,leftBorder,rightMark);
        }
    }
    public static void swap(int[] array, int a, int b){
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
