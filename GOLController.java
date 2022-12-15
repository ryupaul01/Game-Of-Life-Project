package com.example.gameoflife_v_2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class GOLController extends Application {
    protected GOLModel golModel;
    protected GraphicsContext gc;
    protected Canvas canvas;
    protected Timeline timeline;

    protected int canvasWidth;
    protected int canvasHeight;
    protected BorderPane root;
    protected int frames;
    protected GOLView golView;

    @Override
    public void start(Stage stage) throws IOException {
        //set up for graphics
        this.canvasHeight = 400;
        this.canvasWidth = 400;
        this.canvas = new Canvas(this.canvasHeight, this.canvasWidth);
        gc = canvas.getGraphicsContext2D();

        //setup Timeline
        this.setup();
        //create the borderPane and show the JFX scene
        root = new BorderPane(canvas);

        root.setTop(golView.getHbox());

        Scene scene = new Scene(root, this.canvasHeight + 150, this.canvasWidth + 150);
        stage.setTitle("GAME OF LIFE");
        stage.setScene(scene);
        stage.show();
    }
        //set up and initialize
        private void setup() throws FileNotFoundException {
            //set GOLModel to 10 x 10 for debugging
            golModel = new GOLModel(50, 50);
            golView = new GOLView(50, 50);

            //create a listener for the next btn
            golView.getNextBtn().setOnAction(actionEvent -> {
                golModel.rules();
                golView.render(gc, golModel.getWorld());
            });

            //create listener for each item in combobox
            golView.getPatternsCB().setOnAction(actionEvent ->{
                try {
                    int selectedIndex = golView.getPatternsCB().getSelectionModel().getSelectedIndex();
                    Object selectedItem = golView.getPatternsCB().getSelectionModel().getSelectedItem();
                    if( 0 == selectedIndex) {
                        golModel.putGlider();
                    }else if(1 == selectedIndex){
                        golModel.putExploder();
                    } else if(2 == selectedIndex){
                        golModel.put10CellRow();
                    } else if(3 == selectedIndex){
                        golModel.putGliderGun();
                    } else if(4 == selectedIndex){
                        golModel.putSpaceship();
                    } else if(5 == selectedIndex){
                        golModel.putTumbler();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            frames = (int)golView.getSpeedSld().getValue();
            timeline = new Timeline();
            setUpTimeLine();
        }

        //identifies the render method that will be repeatedly invoked
        private void setUpTimeLine(){

            //set Frame Rate
            KeyFrame frame = new KeyFrame(Duration.millis(1000 / 20), actionEvent -> {
                golView.render(gc, golModel.getWorld());
                golModel.rules();
                });
            //creating a listener for the slider gui


            timeline.getKeyFrames().add(frame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

            //creates a listener for the start button
            golView.getStartBtn().setOnAction(actionEvent -> {
                timeline.play();});

            //creates a listener for the pause button
            golView.getPauseBtn().setOnAction(e -> timeline.pause());

            //creates a listener for the stop button
            golView.getStopBtn().setOnAction(actionEvent -> {
                timeline.stop();
                golModel.clearBoard();
            });
        }

    public static void main(String[] args) {
        launch();
    }
}