package com.company.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    private File file = new File("10m.txt");
    private ArrayList<Long> list = new ArrayList<>();
    public ArrayList<Long> readFile(){
        try {
            FileReader fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()){
                list.add(Long.valueOf(scanner.nextLine()));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Long> getList(){
        return list;
    }
}
