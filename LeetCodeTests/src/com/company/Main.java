package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        ArrayList<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            chars.add(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' | s.charAt(i) != ',' | s.charAt(i) != ':'){
                chars.remove();
            }
        }
        System.out.println(chars);
    }
}