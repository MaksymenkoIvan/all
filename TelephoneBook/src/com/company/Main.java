package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        System.out.println("1. Добавить");
        System.out.println("2. Изменить");
        Scanner scanner = new Scanner(System.in);

        Map<Integer, Action> menu = new HashMap<>();
        menu.put(1, new AddName());
        menu.put(2, new EditName());
        int choice = scanner.nextInt();
        menu.get(choice).execute();
    }
}
