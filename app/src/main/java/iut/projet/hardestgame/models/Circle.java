package iut.projet.hardestgame.models;

public class Circle extends Collisionable {
    private float radius = 0;

    public Circle(float x, float y, float radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public boolean checkCollisions(Circle other){
        float d2 = (x-other.x)*(x-other.x) + (y-other.y)*(y-other.y);
        if (d2 > (radius + other.radius)*(radius + other.radius))
            return false;
        else
            return true;
    }

    public boolean checkCollisions(Box other){
        Box boxCercle = new Box(x-radius, y-radius, radius*2, radius*2);
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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
