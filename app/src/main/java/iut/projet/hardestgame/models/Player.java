package iut.projet.hardestgame.models;

import android.graphics.Bitmap;

public class Player extends Circle {
    public Player(float x, float y, float radius, Bitmap b){
        super(x,y,radius,b);
    }

    public void move(float x, float y){
        this.setCenterX(x);
        this.setCenterY(y);
    }
}
