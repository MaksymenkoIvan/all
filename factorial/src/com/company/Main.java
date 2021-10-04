package com.company;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        int sum = 0;
        BigInteger factorial = BigInteger.valueOf(1); //Express BigInteger and set it to 1
	    int num = 100; //Express integer and set it to 1
        for (long i = 1; i <= num; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i)); //Looking for factorial using a loop FOR
        }
        String stringSum = String.valueOf(factorial); //Convert BigInteger to String
        char [] numsChar = stringSum.toCharArray(); //Convert String to Char Array
        for (int i = 0; i < numsChar.length; i++) {
            sum += (numsChar[i] - '0'); //Covert Char to Int and sum all elements of Char Array using a loop FOR
        }
        System.out.println(sum); //Print the value of sum
    }
}
