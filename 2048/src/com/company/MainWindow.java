package com.company;

public class MainWindow{
    public static void main(String[] args) {
        initFields();
        createInitialCells();

        while(!endOfGame){

            input();
            logic();

            graphicsModule.draw(gameField);
        }

        graphicsModule.destroy();

    }

}
