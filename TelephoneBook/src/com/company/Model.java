package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class Model {
    private LinkedList<String> strings = new LinkedList<>();
    private FileIO fileIO = new FileIO();

    public void add(){
        String word[] = fileIO.read().split(",");
        for (String a: word) {
            strings.add(a);
        }
        System.out.println("Enter your name:");
        Scanner scanner = new Scanner(System.in);
        strings.add(scanner.nextLine());
        StringBuilder s =  new StringBuilder();
        for (String name : strings) {
            s.append(name).append(",");
        }
        fileIO.write(s.toString());
    }
    public void edit(){
        String word[] = fileIO.read().split(",");
        for (String a: word) {
            strings.add(a);
        }
        for (int i = 0; i < strings.size(); i++){
            System.out.println(i+1 + ". " + strings.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int number = scanner.nextInt();
        if(strings.size() >= 0 && number <= strings.size()){
            System.out.println("Enter new name: ");
            String name = scanner.nextLine();
            name = scanner.nextLine();
            strings.set(number-1,name);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : strings) {
            stringBuilder.append(name).append(",");
        }
        fileIO.write(stringBuilder.toString());

    }


}
