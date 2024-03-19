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
    @FXML private ImageView ivArrow;

    private Label[][] list = new Label[10][15];
    private char[][] clist = new char[10][15];
    AnimationTimer timer = null;

    int dbopen = 0;
    int dbclose = 0;
    long step = 0;
    int fok = 90;
    int igaz = 0;
    int nem = 0;

    public void initialize(){
        for(int s=0; s<10; s++) for(int o=0; o<15; o++){
            int ss=s, oo=o;
            list[s][o] = new Label("");
            clist[s][o] = 'X';
            list[s][o].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("null.png"))));
            list[s][o].setTranslateX(10+o*64);
            list[s][o].setTranslateY(10+s*64);
            list[s][o].setOnMouseEntered(e -> list[ss][oo].setStyle("-fx-background-color: lightgreen"));
            list[s][o].setOnMouseExited(e -> list[ss][oo].setStyle("-fx-background-color: white"));
            list[s][o].setOnMouseClicked(e -> click(ss,oo));
            pnPane.getChildren().add(list[s][o]);
            if(clist[s][o] == 'X'){
                igaz = 1;
            }else if(clist[s][o] == 'O'){
                igaz = 0;
            }
        }
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(step < now){
                    fok+=90;
                    step = 250000000;
                    ivArrow.setRotate(fok);
                }
            }
        };
        timer.start();
        add();
        db();
    }

    private void db(){
        lbClosedb.setText(dbclose + " db");
        lbOpendb.setText(dbopen + " db");
    }

    private void add(){
        for(int o=0; o<15; o++){
            int s = 9;
            int rand = (int)(Math.random()*10);
            while(rand != 0){
                list[s][o].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("box.png"))));
                clist[s][o] = 'O';
                s--; rand--; dbclose++;
            }
        }
    }

    @FXML private void randomClick(){
        for(int s=0; s<10; s++) for(int o=0; o<15; o++){
            dbopen=0; dbclose=0;
            list[s][o].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("null.png"))));
            clist[s][o] = 'X';
        }
        add();
        db();
    }

    private void click(int ss, int oo){
        dbclose--; dbopen++;
            if(igaz == 0){
                list[ss][oo].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("null.png"))));
            }else if(igaz == 1){
                list[ss][oo].setGraphic(new ImageView(new Image(getClass().getResourceAsStream("boxopen.png"))));
            }
        db();
    }


}