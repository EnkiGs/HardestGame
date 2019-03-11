package iut.projet.hardestgame.models;

import android.graphics.Bitmap;

public class Player extends Box {
    public Player(float x, float y, float height, float width, Bitmap b){
        super(x,y,width,height,b);
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean checkArrival(Arrival a){
        return a.x + a.getWidth() > x + getWidth() && a.x < x && a.y + a.getHeight() > y + getHeight() && a.y < y;
    }
}
