package iut.projet.hardestgame.models;

import android.graphics.Bitmap;

public class Box extends Collisionable {
    private float width;
    private float height;

    private boolean collisionUp = false;
    private boolean collisionDown = false;
    private boolean collisionLeft = false;
    private boolean collisionRight = false;


    public Box(float x, float y, float width, float height, Bitmap b){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bitmap = b;
    }


    public void checkCollisions(Box other, float newX, float newY){
        float oldX = x;
        float oldY = y;
        boolean test1 = false;
        boolean test2 = false;
        boolean test3 = false;
        boolean test4 = false;
        x = newX;
        y = newY;
        checkCollisionsX(other);
        if (isCollisionLeft() || isCollisionRight()) {
            test1 = true;
            x = oldX;
        }
        checkCollisionsY(other);
        if (isCollisionDown() || isCollisionUp()) {
            test2 = true;
            y = oldY;
        }

        if(!test1 && !test2)
            return;

        x = newX;
        y = newY;
        checkCollisionsY(other);
        if (isCollisionDown() || isCollisionUp()) {
            test3 = true;
            y = oldY;
        }
        checkCollisionsX(other);
        if (isCollisionLeft() || isCollisionRight()) {
            test4 = true;
            x = oldX;
        }
        if(test1)
            System.out.println("1");
        if(test2)
            System.out.println("2");
        if(test3)
            System.out.println("3");
        if(test4)
            System.out.println("4");
        System.out.println("New : "+newX);
        System.out.print("Old : "+oldX);
        if(test1 && test2 && test3 && !test4){
            x = newX;
            y = oldY;
        }
        else if(test1 && !test2 && test3 && test4){
            x = oldX;
            y = newY;
        }
        else{
            x = 0;
            y = 0;
        }


    }

    public void checkCollisionsX(Box other){
        collisionLeft = false;
        collisionRight = false;
        if((other.x >= x + width)      // trop à droite
                || (other.x + other.width <= x) // trop à gauche
                || (other.y >= y + height) // trop en bas
                || (other.y + other.height <= y))  // trop en haut
/*            return false;
        else
            return true;*/
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
/*            return false;
        else
            return true;*/
            return;
        if(other.y < y + height && other.y > y) {
            collisionDown = true;
        }
        if(other.y + other.height > y && other.y + other.height < y + height){
            collisionUp = true;
        }

    }
/*
    public boolean checkCollisions(Circle other){
        Box boxCercle = new Box(other.x-other.getRadius(), other.y-other.getRadius(), other.getRadius()*2, other.getRadius()*2, null);
        if (!checkCollisions(boxCercle))
            return false;   // premier test
        if (other.collisionPointCircle(x,y)
                || other.collisionPointCircle(x,y+height)
                || other.collisionPointCircle(x+width,y)
                || other.collisionPointCircle(x+width,y+height))
            return true;   // deuxieme test
        if (collisionPointBox(other.x,other.y))
            return true;   // troisieme test
        boolean projvertical = ProjectionOnSegment(other.x,other.y,x,y,x,y+height);
        boolean projhorizontal = ProjectionOnSegment(other.x,other.y,x,y,x+width,y);
        if (projvertical || projhorizontal)
            return true;   // cas E
        return false;  // cas B
    }*/


    public boolean collisionPointBox(float PointX,float PointY)
    {
        if (PointX >= x
                && PointX < x + width
                && PointY >= y
                && PointY < y + height)
            return true;
        else
            return false;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
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
}
