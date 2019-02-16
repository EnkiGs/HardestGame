package iut.projet.hardestgame.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.NumberFormat;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;
import iut.projet.hardestgame.views.GameView;

public class GameActivity extends AppCompatActivity {

    private View myView;
    private TextView viewDeaths;
    SongPlayer songPlayer = MainActivity.getSongPlayer();
    private static int nbDeaths = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        myView = findViewById(R.id.gameActivityView);
        myView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);
                switch(action){
                    case MotionEvent.ACTION_DOWN :
                        nbDeaths ++;
                        viewDeaths = (TextView) findViewById(R.id.nbDeaths);
                        viewDeaths.setText("DEATHS :"+Integer.toString(nbDeaths));
                        return true;
                    default :
                        System.out.println("WHAT THE FUK");
                }
                return true;
            }
        });

        songPlayer.stop();
    }


    public static int  getNbDeaths(){
        return nbDeaths;
    }



}
