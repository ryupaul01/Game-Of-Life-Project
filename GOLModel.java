package com.example.gameoflife_v_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GOLModel {
    protected int rows;
    protected int cols;
    protected char[][] world;

    public GOLModel(int r, int c) throws FileNotFoundException {
        this.rows = r;
        this.cols = c;
        world = new char [rows][cols];

        //putBlinker();

    }

    //checks how many cells are alive around a single cell
    private int NumOfAlive(int row, int col){
        int count = 0;

        count += isCellAlive((row - 1),(col - 1));
        count += isCellAlive((row - 1), (col));
        count += isCellAlive((row - 1), (col + 1));

        count += isCellAlive((row), (col - 1));
        count += isCellAlive((row), (col + 1));

        count += isCellAlive((row+1), (col -1));
        count += isCellAlive((row + 1), (col));
        count += isCellAlive((row + 1), (col + 1));
        return count;
    }

    //checks is cell is alive. 1 == alive and 0 == dead
    //Also checks for edges and corners
    private int isCellAlive(int row, int col){
        if(row < 0 || row >= world.length){
            return 0;
        }
        if(col < 0 || col >= world.length){
            return 0;
        }
        if(world[row][col] == '*'){
            return 1;
        }else
            return 0;
    }

    public void rules(){
        char[][] UpdatedWorld = new char[this.rows][this.cols];
        for(int row = 0; row < world.length; row++){
            for(int col = 0; col < world.length; col++){
                int aliveNeighbors = NumOfAlive(row,col);
                if(isCellAlive(row,col) == 1){
                    if(aliveNeighbors < 2 || aliveNeighbors > 3){
                        UpdatedWorld[row][col] = ' ';
                    }else if( aliveNeighbors == 2 || aliveNeighbors == 3){
                        UpdatedWorld[row][col] = '*';
                    }

                }else{
                    if(aliveNeighbors == 3){
                        UpdatedWorld[row][col] = '*';
                    }
                }
            }
        }
        this.world = UpdatedWorld;
    }

    public void clearBoard(){
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world.length; col++) {
                world[row][col] = ' ';
            }
        }
    }
    public void putGlider() throws FileNotFoundException {

        File gliderFile = new File("/Users/ryupa/OneDrive/Documents/School_Shit/SPRING_2022/IS_413/patterns/files/Glider.pat");
        Scanner scan = new Scanner(gliderFile);
        int rowCounter = 0;

        while(scan.hasNextLine()){

            String glider = scan.nextLine();

            for (int i = 0; i < glider.length(); i++) {
                    if (glider.charAt(i) == '*') {
                        world[rowCounter][i] = '*';
                    } else {
                        world[rowCounter][i] = ' ';
                    }
                System.out.println("glider at position" + (i) + ": " + glider.charAt(i));
            }
            rowCounter++;
        }
    }

    public void put10CellRow() throws FileNotFoundException{
        File tenCellRowFile = new File("/Users/ryupa/OneDrive/Documents/School_Shit/SPRING_2022/IS_413/patterns/files/10CellRow.pat");
        Scanner scan = new Scanner(tenCellRowFile);
        int rowCounter = 0;
        while(scan.hasNextLine()){
            String tenCellRow = scan.nextLine();
            for (int col = 0; col < tenCellRow.length(); col++) {
                if(tenCellRow.charAt(col) == '*') {
                    world[rowCounter][col] = '*';
                }else{
                    world[rowCounter][col] = ' ';
                }
            }
            rowCounter++;
        }
    }

    public void putExploder() throws FileNotFoundException{
        File ExploderFile = new File("/Users/ryupa/OneDrive/Documents/School_Shit/SPRING_2022/IS_413/patterns/files/Exploder.pat");
        Scanner scan = new Scanner(ExploderFile);
        int rowCounter = 0;
        while(scan.hasNextLine()){
            String Exploder = scan.nextLine();
            for (int col = 0; col < Exploder.length(); col++) {
                if(Exploder.charAt(col) == '*') {
                    world[rowCounter][col] = '*';
                }else{
                    world[rowCounter][col] = ' ';
                }
            }
            rowCounter++;
        }
    }

    public void putGliderGun() throws FileNotFoundException{
        File GliderGunFile = new File("/Users/ryupa/OneDrive/Documents/School_Shit/SPRING_2022/IS_413/patterns/files/GliderGun.pat");
        Scanner scan = new Scanner(GliderGunFile);
        int rowCounter = 0;
        while(scan.hasNextLine()){
            String GliderGun = scan.nextLine();
            for (int col = 0; col < GliderGun.length(); col++) {
                if(GliderGun.charAt(col) == '*') {
                    world[rowCounter][col] = '*';
                }else{
                    world[rowCounter][col] = ' ';
                }
            }
            rowCounter++;
        }
    }

    public void putSpaceship() throws FileNotFoundException{
        File SpaceshipFile = new File("/Users/ryupa/OneDrive/Documents/School_Shit/SPRING_2022/IS_413/patterns/files/Spaceship.pat");
        Scanner scan = new Scanner(SpaceshipFile);
        int rowCounter = 0;
        while(scan.hasNextLine()){
            String Spaceship = scan.nextLine();
            for (int col = 0; col < Spaceship.length(); col++) {
                if(Spaceship.charAt(col) == '*') {
                    world[rowCounter][col] = '*';
                }else{
                    world[rowCounter][col] = ' ';
                }
            }
            rowCounter++;
        }
    }

    public void putTumbler() throws FileNotFoundException{
        File TumblerFile = new File("/Users/ryupa/OneDrive/Documents/School_Shit/SPRING_2022/IS_413/patterns/files/Tumbler.pat");
        Scanner scan = new Scanner(TumblerFile);
        int rowCounter = 0;
        while(scan.hasNextLine()){
            String Tumbler = scan.nextLine();
            for (int col = 0; col < Tumbler.length(); col++) {
                if(Tumbler.charAt(col) == '*') {
                    world[rowCounter][col] = '*';
                }else{
                    world[rowCounter][col] = ' ';
                }
            }
            rowCounter++;
        }
    }
    //return the world
    public char[][] getWorld() {
        return world;
    }
}
