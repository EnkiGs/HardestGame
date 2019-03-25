package iut.projet.hardestgame.views;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.activities.LevelsActivity;

public class LevelButton extends AppCompatButton {
    private static int size = 350;

    public LevelButton(final Context context, String text, boolean clickable) {
        super(context);
        setHeight(size);
        setWidth(size);
        setText(text);
        setClickable(clickable);
        if(clickable) {
            setBackgroundResource(R.drawable.lvlbutton);
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((LevelsActivity) context).levelChoosed(Integer.parseInt(getText().toString()));
                }
            });
        }
        else
            setBackgroundResource(R.drawable.lvlbuttondisabled);
    }

    public static int getSize() {
        return size;
    }
}
