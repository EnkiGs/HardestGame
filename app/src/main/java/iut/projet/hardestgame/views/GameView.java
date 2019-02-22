package iut.projet.hardestgame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;
import android.widget.LinearLayout;

import iut.projet.hardestgame.controllers.GameLoop;

public class GameView extends SurfaceView {

    private int width = 1000;
    private int height = 1000;
    private float radius = 35;
    private float currX = 100;
    private float currY = 100;
    private Paint paint;
    private GameLoop game;

    private int ballColor = Color.GREEN;

    public GameView(Context context, final GameLoop game) {
        super(context);
        this.game = game;
        paint = new Paint();
        setLayoutParams(new LinearLayout.LayoutParams(width, height));
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawRGB(255,255,255);
        paint.setColor(this.getBallColor());
        canvas.drawCircle(currX, currY, radius, paint);
    }


    public int getBallColor() {
        return ballColor;
    }

    public void setBallColor(int ballColor) {
        this.ballColor = ballColor;
    }

    public float getCurrX() {
        return currX;
    }

    public float getCurrY() {
        return currY;
    }

    public void setCurrX(float currX) {
        this.currX = currX;
    }

    public void setCurrY(float currY) {
        this.currY = currY;
    }

    public float getRadius(){ return radius;}

    public int isWidth(){return width;}

    public int isHeight(){return height;}
}
