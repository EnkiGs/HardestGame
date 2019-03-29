package iut.projet.hardestgame.models;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Tile extends Box {
    public Tile(Point p, int side, Bitmap b) {
        super(p,side,side,b);
    }
}
