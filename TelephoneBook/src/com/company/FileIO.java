package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
    private File file = new File("phonebook.file");
    private StringBuilder out = new StringBuilder();

    public String read(){
        try {
            FileReader fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()){
                out.append(scanner.nextLine());
            }
            return out.toString();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public void write(String string){
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(string);
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
