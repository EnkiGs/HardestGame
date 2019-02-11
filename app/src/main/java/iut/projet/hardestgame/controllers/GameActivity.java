package iut.projet.hardestgame.controllers;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;
import iut.projet.hardestgame.views.GameView;

public class GameActivity extends AppCompatActivity {


    SongPlayer songPlayer;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        LinearLayout rootLayout = (LinearLayout)findViewById(R.id.gameA);

        final GameView gameView = new GameView(this);

        gameView.setMinimumWidth(500);
        gameView.setMinimumHeight(800);

        View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gameView.setCurrX(motionEvent.getX());
                gameView.setCurrY(motionEvent.getY());

                gameView.setBallColor(Color.BLUE);

                gameView.invalidate();

                return true;
            }
        };

        gameView.setOnTouchListener(onTouchListener);

        rootLayout.addView(gameView);

        try {
            songPlayer = new SongPlayer(getBaseContext());
            songPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
