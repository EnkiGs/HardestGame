package iut.projet.hardestgame.controllers;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;
import iut.projet.hardestgame.views.GameView;

public class GameActivity extends AppCompatActivity{

    private GameLoop game;
    SongPlayer songPlayer;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new GameLoop();
        game.initGame(this);
        setContentView(game.screen);
        //LinearLayout rootLayout = findViewById(R.id.gameA);

        /*gameView = new GameView(this){
            @Override
            public boolean performClick() {
                return super.performClick();
            }
        };*/

       /* gameView.setMinimumWidth(500);
        gameView.setMinimumHeight(800);
*/


        /*View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                gameView.setCurrX(motionEvent.getX());
                gameView.setCurrY(motionEvent.getY());

                gameView.setBallColor(Color.BLUE);
                gameView.performClick();
                gameView.invalidate();

                return true;
            }
        };*/


        //gameView.setOnTouchListener(onTouchListener);
        //rootLayout.addView(gameView);

        try {
            songPlayer = new SongPlayer(getBaseContext());
            //songPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        game.running = false;
        super.onDestroy();
    }
}
