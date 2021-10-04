package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File("file.myfile");
        File file1 = new File("10m.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("Hello world");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
