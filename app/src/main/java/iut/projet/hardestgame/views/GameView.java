package iut.projet.hardestgame.views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import iut.projet.hardestgame.Activities.GameActivity;
import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameManager;
import iut.projet.hardestgame.models.Box;
import iut.projet.hardestgame.models.Circle;
import iut.projet.hardestgame.models.Collisionable;

public class GameView extends SurfaceView implements SurfaceHolder.Callback  {

    private int width = 1000;
    private int height = 1000;
    private float radius = 35;
    private float currX = 100;
    private float currY = 100;
    private Paint paint;
    private SurfaceHolder surfaceHolder;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private Canvas canvas = null;
    private int ballColor = Color.GREEN;
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

    Collisionable[] tabCol;

    public GameView(Context context,Collisionable[] tabCol) {
        super(context);
        setFocusable(true);
        paint = new Paint();
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        //setZOrderOnTop(true);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);

        b1 = new Box(100, 100, 50, 50, BitmapFactory.decodeResource(context.getResources(), R.drawable.tile));
        b2 = new Box(500,300, 50, 50, BitmapFactory.decodeResource(context.getResources(), R.drawable.tile));
        c = new Circle(currX, currY, radius, BitmapFactory.decodeResource(context.getResources(), R.drawable.player));

        this.tabCol = tabCol;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenHeight = getHeight();
        screenWidth = getWidth();
        right = screenWidth - margin;
        bottom = screenHeight - margin;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}


    public void update(float xSensor, float ySensor) {
        /*paint.setColor(this.getBallColor());
        currX -= xSensor*3;
        currY += ySensor*3;
        c.setX(currX);
        c.setY(currY);
        b1.setX(b1.getX()-xSensor*2);
        b1.setY(b1.getY()+ySensor*2);
        if(b1.checkCollisions(b2)){
            b1.setX(b1.getX()+xSensor*2);
            b1.setY(b1.getY()-ySensor*2);
        }

        if(c.checkCollisions(b2)){
            currX += xSensor*3;
            currY -= ySensor*3;
            c.setX(currX);
            c.setY(currY);
        }

        canvas = surfaceHolder.lockCanvas();

        rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, backgroundPaint);

        canvas.drawCircle(currX, currY, radius, paint);

        Paint p1 = new Paint();
        p1.setColor(Color.RED);
        canvas.drawRect(new Rect((int)b1.getX(),(int)b1.getY(),(int)(screenWidth-(screenWidth-(b1.getX()+b1.getWidth()))),(int)(screenHeight-(screenHeight-(b1.getY()+b1.getHeight())))),p1);
        Paint p2 = new Paint();
        p2.setColor(Color.BLACK);
        canvas.drawRect(new Rect((int)b2.getX(),(int)b2.getY(),(int)(screenWidth-(screenWidth-(b2.getX()+b2.getWidth()))),(int)(screenHeight-(screenHeight-(b2.getY()+b2.getHeight())))),p2);
        surfaceHolder.unlockCanvasAndPost(canvas);*/
        canvas = surfaceHolder.lockCanvas();
        rect = new Rect(left, top, right, bottom);
        canvas.drawRect(rect, backgroundPaint);
        for (Collisionable col : tabCol) {
            if(col!=null) {
                canvas.drawBitmap(col.getBitmap(),col.getX(),col.getY(),null);
                //Toast.makeText(getContext(),"height : "+col.getBitmap().getHeight()+" / wdith : "+col.getBitmap().getWidth(),Toast.LENGTH_SHORT).show();
            }
        }
        surfaceHolder.unlockCanvasAndPost(canvas);
        
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
