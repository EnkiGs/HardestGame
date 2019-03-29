package iut.projet.hardestgame.models;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Arrival extends Box {
    private boolean lock = false;
    private Bitmap[] bitmaps;
    public Arrival(Point p, int side, Bitmap b, Bitmap notB) {
        super(p,side,side,b);
        bitmaps = new Bitmap[]{b,notB};
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
        if(lock)
            bitmap = bitmaps[1];
        else
            bitmap = bitmaps[0];
    }
}
