package iut.projet.hardestgame.models;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatButton;

public class LevelButton extends AppCompatButton {
    private static int size =  200;

    public LevelButton(Context context) {
        super(context);
        setHeight(size/2);
        setMinWidth(5);
        setWidth(size);
        setTextSize(10);
    }

    public static int getSize() {
        return size;
    }
}
