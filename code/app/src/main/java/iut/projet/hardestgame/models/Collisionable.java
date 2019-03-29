package iut.projet.hardestgame.models;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

public abstract class Collisionable {
    /*
        protected float x = 0;
        protected float y = 0;*/
    protected Point point;
    public static int TILE_SIZE = 68;
    protected Bitmap bitmap;
    protected Rect rectangle;

/*    protected boolean ProjectionOnSegment(float Cx,float Cy,float Ax,float Ay,float Bx,float By)
    {
        float ACx = Cx-Ax;
        float ACy = Cy-Ay;
        float ABx = Bx-Ax;
        float ABy = By-Ay;
        float BCx = Cx-Bx;
        float BCy = Cy-By;
        float s1 = (ACx*ABx) + (ACy*ABy);
        float s2 = (BCx*ABx) + (BCy*ABy);
        if (s1*s2>0)
            return false;
        return true;
    }*/

    public Bitmap getBitmap() {
        return bitmap;
    }

/*    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }*/


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }


    public Rect getRectangle() {
        return rectangle;
    }
}

