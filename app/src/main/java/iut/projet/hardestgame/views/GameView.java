package iut.projet.hardestgame.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import iut.projet.hardestgame.Activities.GameActivity;
import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameManager;
import iut.projet.hardestgame.models.Box;
import iut.projet.hardestgame.models.Circle;
import iut.projet.hardestgame.models.Collisionable;

public class GameView extends View  {

    private int width = 1000;
    private int height = 1000;
    private int screenWidth;
    private int screenHeight;
    Paint backgroundPaint;
    Rect rect;

    // Rect
    private int margin = 0;
    private int left = margin;
    private int top = margin;
    private int right;
    private int bottom;
    Collisionable[] tabCol;

    public GameView(Context context,Collisionable[] tabCol) {
        super(context);

        screenHeight = getHeight();
        screenWidth = getWidth();
        right = screenWidth - margin;
        bottom = screenHeight - margin;
        setFocusable(true);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);

        rect = new Rect(left, top, right, bottom);
        this.tabCol = tabCol;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect, backgroundPaint);
        for (Collisionable col : tabCol) {
            if(col!=null) {
                canvas.drawBitmap(col.getBitmap(),col.getX(),col.getY(),null);
            }
        }
    }


    public void update() {
        postInvalidate();
    }

    public int isWidth(){return width;}

    public int isHeight(){return height;}
}
