package iut.projet.hardestgame.models;

import android.graphics.Bitmap;

public class Circle extends Collisionable {
    private float radius;
    private float centerX;
    private float centerY;

    public Circle(float x, float y, float radius, Bitmap b){
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        this.bitmap = b;
        calculateXY();
    }

    public boolean checkCollisions(Circle other){
        float d2 = (x-other.x)*(x-other.x) + (y-other.y)*(y-other.y);
        if (d2 > (radius + other.radius)*(radius + other.radius))
            return false;
        else
            return true;
    }

    public boolean checkCollisions(Box other){
        Box boxCercle = new Box(x-radius, y-radius, radius*2, radius*2, null);
        if (!other.checkCollisions(boxCercle))
            return false;   // premier test
        if (collisionPointCircle(other.x,other.y)
                || collisionPointCircle(other.x,other.y+other.getHeight())
                || collisionPointCircle(other.x+other.getWidth(),other.y)
                || collisionPointCircle(other.x+other.getWidth(),other.y+other.getHeight()))
            return true;   // deuxieme test
        if (other.collisionPointBox(x,y))
            return true;   // troisieme test
        boolean projvertical = ProjectionOnSegment(x,y,other.x,other.y,other.x,other.y+other.getHeight());
        boolean projhorizontal = ProjectionOnSegment(x,y,other.x,other.y,other.x+other.getWidth(),other.y);
        if (projvertical || projhorizontal)
            return true;   // cas E
        return false;  // cas B
    }

    public boolean collisionPointCircle(float x,float y)
    {
        float d2 = (x-this.x)*(x-this.x) + (y-this.y)*(y-this.y);
        if (d2>radius*radius)
            return false;
        else
            return true;
    }

    public void calculateXY(){
        this.x = this.centerX-radius;
        this.y = this.centerY-radius;
    }


    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        calculateXY();
    }

    public float getCenterX() {
        return centerX;
    }

    public void setCenterX(float centerX){
        this.centerX = centerX;
        calculateXY();
    }

    public float getCenterY() {
        return centerY;
    }

    public void setCenterY(float centerY){
        this.centerY = centerY;
        calculateXY();
    }
}
