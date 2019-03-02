package iut.projet.hardestgame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import iut.projet.hardestgame.controllers.GameActivity;
import iut.projet.hardestgame.models.Box;
import iut.projet.hardestgame.models.Circle;

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
    Paint backgroundPaint;
    Rect rect;

    // Rect
    private int margin = 0;
    private int left = margin;
    private int top = margin;
    private int right;
    private int bottom;


    Box b1;
    Box b2;
    Circle c;
    public GameView(Context context, GameActivity ga/*, final GameLoop game*/) {
        super(context);
        setFocusable(true);
        paint = new Paint();
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        game = ga;
        setZOrderOnTop(true);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);

        b1 = new Box(100, 100, 50, 50);
        b2 = new Box(500,300, 50, 50);
        c = new Circle(currX, currY, radius);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //setWillNotDraw(false);
        thread = new Thread(this);
        thread.start();
        threadRunning = true;
        screenHeight = getHeight();
        screenWidth = getWidth();
        right = screenWidth - margin;
        bottom = screenHeight - margin;
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

            if(deltaTime < 500)
            {
                try {
                    Thread.sleep(40 - deltaTime);
                }catch (InterruptedException ex)
                {
                    //Log.e(LOG_TAG, ex.getMessage());
                }

            }
        }

    }


    public void update() {
        paint.setColor(this.getBallColor());
        currX -= game.getmSensorX()*3;
        currY += game.getmSensorY()*3;
        c.setX(currX);
        c.setY(currY);
        b1.setX(b1.getX()-game.getmSensorX()*2);
        b1.setY(b1.getY()+game.getmSensorY()*2);
        if(b1.checkCollisions(b2)){
            b1.setX(b1.getX()+game.getmSensorX()*2);
            b1.setY(b1.getY()-game.getmSensorY()*2);
        }

        if(c.checkCollisions(b2)){
            currX += game.getmSensorX()*3;
            currY -= game.getmSensorY()*3;
            c.setX(currX);
            c.setY(currY);
        }



        // Only draw text on the specified rectangle area.
        canvas = surfaceHolder.lockCanvas();

        // Draw the specify canvas background color.
        rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, backgroundPaint);

        canvas.drawCircle(currX, currY, radius, paint);

        Paint p1 = new Paint();
        p1.setColor(Color.RED);
        canvas.drawRect(new Rect((int)b1.getX(),(int)b1.getY(),(int)(screenWidth-(screenWidth-(b1.getX()+b1.getWidth()))),(int)(screenHeight-(screenHeight-(b1.getY()+b1.getHeight())))),p1);
        Paint p2 = new Paint();
        p2.setColor(Color.BLACK);
        canvas.drawRect(new Rect((int)b2.getX(),(int)b2.getY(),(int)(screenWidth-(screenWidth-(b2.getX()+b2.getWidth()))),(int)(screenHeight-(screenHeight-(b2.getY()+b2.getHeight())))),p2);

        // Send message to main UI thread to update the drawing to the main view special area.
        surfaceHolder.unlockCanvasAndPost(canvas);
        //invalidate();
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
