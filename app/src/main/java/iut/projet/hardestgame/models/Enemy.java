package iut.projet.hardestgame.models;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Enemy extends Box {
    private int sens = 1;
    private int orientation;    // 0 : horizontal / 1 : vertical
    public Enemy(Point p, int width, int height, Bitmap b, int o) {
        super(p, width, height, b);
        orientation = o;
    }

    public void move(Point point){
        setPoint(point);
    }

    public int getSens() {
        return sens;
    }

    public void invertSens() {
        this.sens *= -1;
    }

    public int getOrientation() {
        return orientation;
    }
}
