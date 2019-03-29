package iut.projet.hardestgame.models;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

public class Player extends Box {
    public Player(Point p, int height, int width, Bitmap b){
        super(p,width,height,b);
    }

    public void move(Point p){
        setPoint(p);
    }

    public boolean checkArrival(Arrival a){
        Point pA = a.getPoint();
        Point p = getPoint();
        return pA.x + a.getWidth() > p.x + getWidth() && pA.x < p.x && pA.y + a.getHeight() > p.y + getHeight() && pA.y < p.y;
    }

    public int checkCollisions(Tile other, Point oldPos) {
        int rep = 0;
        Point p = getPoint();
        setPoint(new Point(point.x,oldPos.y));
        if(Rect.intersects(rectangle,other.getRectangle())){
            rep=1;
        }
        setPoint(new Point(oldPos.x,p.y));
        if(Rect.intersects(rectangle,other.getRectangle())){
            if(rep == 1)
                rep = 3;
            else
                rep = 2;
        }
        setPoint(new Point(p.x,p.y));
        return rep;
    }
}
