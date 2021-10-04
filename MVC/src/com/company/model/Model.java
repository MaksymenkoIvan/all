package com.company.model;

import com.company.controller.Reader;

import java.util.ArrayList;
import java.util.Collections;

public class Model {
    private long min;
    private long max;
    private Reader reader;

    public Model(Reader reader){
        this.reader = reader;
    }

    public ArrayList<Long> sort(){
        Collections.sort(reader.readFile());
        return reader.getList();
    }

    public void minMax(){
        min = Collections.min(reader.readFile());
    }
}
