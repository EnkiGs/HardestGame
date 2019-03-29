package iut.projet.hardestgame.views;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import iut.projet.hardestgame.activities.PlayerActivity;
import iut.projet.hardestgame.controllers.GameManager;

public class PlayerButton extends AppCompatButton {
    private static int size = 350;
    private int num;

    public PlayerButton(final Context context, @DrawableRes int res, int num) {
        super(context);
        this.num = num;
        setHeight(size);
        setWidth(size);
        setBackgroundResource(res);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GameManager.setPlayerNum(getNum());
                ((PlayerActivity)context).playerChoosed(getNum());
            }
        });
    }

    public static int getSize() {
        return size;
    }

    public int getNum() {
        return num;
    }
}
