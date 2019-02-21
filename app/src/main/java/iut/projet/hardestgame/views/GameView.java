package iut.projet.hardestgame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameView extends View {
    private float currX = 100;

    private float currY = 100;
    private Paint paint;

    private int ballColor = Color.GREEN;

    public GameView(Context context) {
        super(context);
        paint = new Paint();
        setLayoutParams(new LinearLayout.LayoutParams(300, 500));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(this.getBallColor());
        canvas.drawCircle(currX, currY, 35, paint);
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
}
