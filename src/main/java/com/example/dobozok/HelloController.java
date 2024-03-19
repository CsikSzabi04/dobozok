package com.example.dobozok;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML private Label lbOpendb;
    @FXML private Label lbClosedb;
    @FXML private Pane pnPane;
    @FXML private Button btRandom;

    private Label[][] list = new Label[10][15];
    AnimationTimer timer = null;
    int fok = 90;

    public void initialize(){
        for(int s=0; s<10; s++) for(int o=0; o<15; o++){
            int ss=s, oo=o;
            list[s][o] = new Label("");
            list[s][o].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("null.png"))));
            list[s][o].setTranslateX(10+o*64);
            list[s][o].setTranslateY(10+s*64);
            list[s][o].setOnMouseEntered(e -> list[ss][oo].setStyle("-fx-background-color: lightgreen"));
            list[s][o].setOnMouseExited(e -> list[ss][oo].setStyle("-fx-background-color: white"));
            pnPane.getChildren().add(list[s][o]);
        }
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
        timer.start();
    }

}