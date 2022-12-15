package com.example.gameoflife_v_2;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.Random;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Affine;

public class GOLView {
    private int width;
    private int height;
    private Button startBtn = new Button("Start");
    private Button pauseBtn = new Button("Pause");
    private Button stopBtn = new Button("Stop");
    private Button nextBtn = new Button("Next Iteration");
    private Slider speedSld = new Slider(10, 110, 60);
    private String[] patternDescriptors = {"Glider", "Exploder", "10CellRow", "Glider Gun", "SpaceShip", "Tumbler"};
    private ComboBox patternsCB = new ComboBox(FXCollections.observableArrayList(patternDescriptors));
    private Label speedLabel = new Label("60");
    private Affine affine;

    //constructor
    public GOLView(int w, int h){
        this.width = w;
        this.height = h;
        this.affine = new Affine();
        this.affine.appendScale( 400 / this.width, 400 / this.height);

        //setting slider Properties
        speedSld.setBlockIncrement(10);
        speedSld.setMajorTickUnit(25);
        speedSld.setMinorTickCount(0);
        speedSld.setShowTickMarks(true);
        speed[Sld.setShowTickLabels(true);
        speedSld.setSnapToTicks(true);

    }

    //create Hbox and add all nodes
    public HBox getHbox(){
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(15,5,5,5));
        hBox.getChildren().addAll(startBtn, pauseBtn,stopBtn,nextBtn, speedSld, patternsCB);
        return hBox;
    }

    public Button getStartBtn(){return startBtn;}
    public Button getPauseBtn(){return pauseBtn;}
    public Button getStopBtn(){return stopBtn;}
    public Button getNextBtn(){return nextBtn;}
    public ComboBox getPatternsCB(){return patternsCB;}
    public Slider getSpeedSld(){return speedSld;}
    public Label getSpeedLabel(){return speedLabel;}
    //render view
    public void render(GraphicsContext gc, char[][] world){
        System.out.println("view::render");
        gc.setTransform(affine);
        Random random = new Random();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, 450, 450);
        gc.setFill(Color.rgb(random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255),1));

        for(int i = 0; i < world.length; i++){
            for(int j = 0; j < world.length; j++){
                if(world[i][j] == '*'){
                    gc.fillRect(j, i, 1, 1);
                }
            }
        }
        gc.setStroke(Color.SALMON);
        gc.setLineWidth(.05f);
        for (int x = 0; x < this.width; x++) {
            gc.strokeLine(x, 0, x, this.width);
        }
        for (int y = 0; y < this.height; y++) {
            gc.strokeLine(0, y, this.height, y);
        }

    }

}
