package iut.projet.hardestgame.models;

import android.graphics.Bitmap;

public class Player extends Box {
    public Player(float x, float y, float height, float width, Bitmap b){
        super(x,y,width,height,b);
    }

    public void move(float x, float y){
        setX(x);
        setY(y);
    }
}
