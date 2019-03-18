package iut.projet.hardestgame.models;

import android.graphics.Bitmap;

public class EnemyH extends Box {
    private int sens = 1;
    public EnemyH(float x, float y, float width, float height, Bitmap b) {
        super(x, y, width, height, b);
    }

    public void move(float x, float y){
        setX(x);
        setY(y);
    }

    public int getSens() {
        return sens;
    }

    public void invertSens() {
        this.sens *= -1;
    }
}
