package iut.projet.hardestgame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import iut.projet.hardestgame.models.Collisionable;

public class GameView extends View  {

    Paint backgroundPaint;
    Rect rect;

    Collisionable[] tabCol;

    private boolean first;

    public GameView(Context context,Collisionable[] tabCol) {
        super(context);

        setFocusable(true);
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);

        rect = new Rect(0,0,0,0);
        this.tabCol = tabCol;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect, backgroundPaint);
        Collisionable player = null;
        for (Collisionable col : tabCol) {
            if(col==null) {
                continue;
            }
            if(col.getClass().getSimpleName().equals("Player")){
                player = col;
            }
            else{
                canvas.drawBitmap(col.getBitmap(),null,col.getRectangle(),null);
            }
        }
        if(player!=null)
            canvas.drawBitmap(player.getBitmap(),null,player.getRectangle(),null);
    }


    public void update() {
        postInvalidate();
    }
}
