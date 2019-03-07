package iut.projet.hardestgame.models;

import android.graphics.Bitmap;
import android.graphics.Color;

public class Tile extends Box {

    private int color;

    public Tile(float x, float y, float side, Bitmap b) {
        super(x,y,side,side,b);
        color = Color.rgb(50,50,255);
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
