package iut.projet.hardestgame.models;

public class Box extends Collisionable {
    private float width;
    private float height;

    public Box(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean checkCollisions(Box other){
        if((other.x >= x + width)      // trop à droite
                || (other.x + other.width <= x) // trop à gauche
                || (other.y >= y + height) // trop en bas
                || (other.y + other.height <= y))  // trop en haut
            return false;
        else
            return true;
    }

    public boolean checkCollisions(Circle other){
        Box boxCercle = new Box(other.x-other.getRadius(), other.y-other.getRadius(), other.getRadius()*2, other.getRadius()*2);
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
    }


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
}
