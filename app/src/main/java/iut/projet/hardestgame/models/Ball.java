package iut.projet.hardestgame.models;

import android.graphics.Color;

public class Ball {
    private int radius;
    private float yPos;
    private float xPos;
    private Color color;

    public Ball(int radius, float yPos, float xPos, Color color) {
        this.radius = radius;
        this.yPos = yPos;
        this.xPos = xPos;
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
