package iut.projet.hardestgame.models;

import android.opengl.ETC1Util;

public class Tile {
    private ETC1Util.ETC1Texture texture;
    private float x, y, height, width;

    public Tile(float x, float y, float height, float width, ETC1Util.ETC1Texture texture) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
}
