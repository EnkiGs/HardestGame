package iut.projet.hardestgame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameLoop;

public class GameView extends SurfaceView {

    private int width = 1000;
    private int height = 1000;
    private float radius = 35;
    private float currX = 100;
    private float currY = 100;
    private Paint paint;
    private GameLoop game;

    private LinearLayout rootLayout;
    private Button b;
    public TextView t1;
    public TextView t2;
    public TextView t3;

    private int ballColor = Color.GREEN;

    public GameView(Context context, final GameLoop game) {
        super(context);
        this.game = game;
        paint = new Paint();
        setLayoutParams(new LinearLayout.LayoutParams(width, height));

        rootLayout = new LinearLayout(context);
        rootLayout.set
        b = new Button(context);
        b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        b.setText("Retour");

        t1 = new TextView(context);
        t2 = new TextView(context);
        t3 = new TextView(context);

        t1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        t2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        t3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));

        t1.setText("TextView");
        t1.setTextSize(24);
        t2.setText("TextView2");
        t2.setTextSize(24);
        t3.setText("COUCOU");
        t3.setTextSize(24);


        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                game.lastEvent = MotionEvent.ACTION_BUTTON_PRESS;
            }
        });

        rootLayout.addView(b);
        rootLayout.addView(t1);
        rootLayout.addView(t2);
        rootLayout.addView(t3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
