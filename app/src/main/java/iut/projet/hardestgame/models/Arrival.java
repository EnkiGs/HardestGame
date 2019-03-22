package iut.projet.hardestgame.models;

import android.graphics.Bitmap;

public class Arrival extends Box {
    private boolean lock = false;
    private Bitmap[] bitmaps;
    public Arrival(float x, float y, float side, Bitmap b, Bitmap notB) {
        super(x,y,side,side,b);
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
