package iut.projet.hardestgame.models;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

public class Box extends Collisionable {
    private int width;
    private int height;
    private boolean collisionUp = false;
    private boolean collisionDown = false;
    private boolean collisionLeft = false;
    private boolean collisionRight = false;


    public Box(Point p, int width, int height, Bitmap b){
        point = p;
        this.width = width;
        this.height = height;
        this.bitmap = b;
        rectangle = new Rect(getPoint().x,getPoint().y,getPoint().x+width,getPoint().y+height);
    }

    @Override
    public void setPoint(Point point) {
        super.setPoint(point);
        rectangle.set(point.x,point.y,point.x+width,point.y+height);
    }
    public boolean checkCollisions(Box other) {
        return Rect.intersects(rectangle, other.getRectangle());
    }

    /*public int[] checkCollisions(Box other) {
        int oldX = getPoint().x;
        int oldY = getPoint().y;
        boolean test = false;
        setPoint(new Point(newPos[0],oldY));
        if(Rect.intersects(rectangle,other.getRectangle())){
            test = true;
            newPos[0] = oldX;
        }
*//*
        checkCollisionsX(other);
        if (isCollisionLeft() || isCollisionRight()) {
            test = true;
            x = oldX;
            newPos[0] = oldX;
        }*//*
        setPoint(new Point(oldX,oldY));
        //x = oldX;
        //y = newPos[1];
        setPoint(new Point(oldX,newPos[1]));
        if(Rect.intersects(rectangle,other.getRectangle())){
            test = true;
            newPos[1] = oldY;
        }*//*
        checkCollisionsY(other);
        if (isCollisionDown() || isCollisionUp()) {
            test = true;
            y = oldY;
            newPos[1] = oldY;
        }
        y = oldY;*//*
        setPoint(new Point(oldX,oldY));

        if (test)
            return newPos;
        else
            return null;
    }*/
    /*
    public void checkCollisionsX(Box other){
        collisionLeft = false;
        collisionRight = false;
        if((other.x >= x + width)      // trop à droite
                || (other.x + other.width <= x) // trop à gauche
                || (other.y >= y + height) // trop en bas
                || (other.y + other.height <= y))  // trop en haut
*//*            return false;
        else
            return true;*//*
            return;
        if(other.x < x + width && other.x > x) {
            collisionRight = true;
        }
        if(other.x + other.width > x && other.x + other.width < x + width){
            collisionLeft = true;
        }

    }

    public void checkCollisionsY(Box other){
        collisionUp = false;
        collisionDown = false;
        if((other.x >= x + width)      // trop à droite
                || (other.x + other.width <= x) // trop à gauche
                || (other.y >= y + height) // trop en bas
                || (other.y + other.height <= y))  // trop en haut
*//*            return false;
        else
            return true;*//*
            return;
        if(other.y < y + height && other.y > y) {
            collisionDown = true;
        }
        if(other.y + other.height > y && other.y + other.height < y + height){
            collisionUp = true;
        }

    }*/
/*
    public boolean collisionPointBox(float PointX,float PointY)
    {
        if (PointX >= x
                && PointX < x + width
                && PointY >= y
                && PointY < y + height)
            return true;
        else
            return false;
    }*/

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isCollisionUp() {
        return collisionUp;
    }

    public boolean isCollisionDown() {
        return collisionDown;
    }

    public boolean isCollisionLeft() {
        return collisionLeft;
    }

    public boolean isCollisionRight() {
        return collisionRight;
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rect rectangle) {
        this.rectangle = rectangle;
    }
}
