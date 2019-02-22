package iut.projet.hardestgame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.LinearLayout;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameActivity;
import iut.projet.hardestgame.controllers.GameLoop;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable  {

    private int width = 1000;
    private int height = 1000;
    private float radius = 35;
    private float currX = 100;
    private float currY = 100;
    private Paint paint;
    //private GameLoop game;
    private GameActivity game;
    private SurfaceHolder surfaceHolder;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private Canvas canvas = null;
    private int ballColor = Color.GREEN;
    private Thread thread = null;
    private boolean threadRunning = false;

    public GameView(Context context, GameActivity ga/*, final GameLoop game*/) {
        super(context);
        /*this.game = game;*/
        paint = new Paint();
        //setLayoutParams(new LinearLayout.LayoutParams(width, height));
        //setBackgroundColor(Color.WHITE);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

    }
    /*@Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(this.getBallColor());
        canvas.drawCircle(currX, currY, radius, paint);
    }*/

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

    public void update() {
        paint.setColor(this.getBallColor());
        currX = game.getmSensorX();
        currY = game.getmSensorY();
        ((Button)game.findViewById(R.id.buttonRetour)).setText(currX+"");
        ballColor = Color.RED;
        int margin = 200;

        int left = margin;

        int top = margin;

        int right = screenWidth - margin;

        int bottom = screenHeight - margin;


        Rect rect = new Rect(left, top, right, bottom);

        // Only draw text on the specified rectangle area.
        canvas = surfaceHolder.lockCanvas(rect);

        // Draw the specify canvas background color.
        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);
        canvas.drawRect(rect, backgroundPaint);

        canvas.drawCircle(currX, currY, radius, paint);

        // Send message to main UI thread to update the drawing to the main view special area.
        surfaceHolder.unlockCanvasAndPost(canvas);
        invalidate();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setWillNotDraw(false);
        thread = new Thread(this);
        thread.start();
        threadRunning = true;
        screenHeight = getHeight();
        screenWidth = getWidth();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        threadRunning = false;
    }

    @Override
    public void run() {
        while(threadRunning)
        {
            long startTime = System.currentTimeMillis();

            update();

            long endTime = System.currentTimeMillis();

            long deltaTime = endTime - startTime;

            if(deltaTime < 200)
            {
                try {
                    Thread.sleep(200 - deltaTime);
                }catch (InterruptedException ex)
                {
                    //Log.e(LOG_TAG, ex.getMessage());
                }

            }
        }

    }
}
