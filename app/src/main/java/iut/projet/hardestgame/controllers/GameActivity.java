package iut.projet.hardestgame.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.w3c.dom.Text;


import java.io.IOException;
import java.text.NumberFormat;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;
import iut.projet.hardestgame.views.GameView;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private View myView;
    private TextView viewDeaths;
    SongPlayer songPlayer = MainActivity.getSongPlayer();
    private static int nbDeaths = 0;
    private SensorManager sensorManager;
    private Sensor mAccelerator;

    GameView gameView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        LinearLayout layout = (LinearLayout) findViewById(R.id.ballDisplay);



        myView = findViewById(R.id.gameA);
        myView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        nbDeaths++;
                        viewDeaths = (TextView) findViewById(R.id.nbDeaths);
                        viewDeaths.setText("DEATHS :" + Integer.toString(nbDeaths));
                        return true;
                    default:
                        System.out.println("WHAT THE FUK");
                }
                return true;
            }
        });

        songPlayer.stop();


        LinearLayout rootLayout = findViewById(R.id.ballDisplay);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        gameView = new GameView(this) {
            @Override
            public boolean performClick() {
                return super.performClick();
            }
        };

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
        rootLayout.addView(gameView);

        try {
            songPlayer = new SongPlayer(getBaseContext(), R.raw.musiquedebut);
            //songPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, mAccelerator, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float mSensorX = 0;
        float mSensorY = 0;
        switch (((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];

                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];

                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                break;
        }
        // FAIRE UNE BOUCLE DE JEU !!!

        gameView.setCurrX(gameView.getCurrX() - 1*mSensorX);
        gameView.setCurrY(gameView.getCurrY() + 1*mSensorY);

        gameView.setBallColor(Color.BLUE);
        gameView.performClick();
        gameView.invalidate();
    }

    public static int  getNbDeaths(){
        return nbDeaths;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //((TextView)findViewById(R.id.textview_sensor)).setText("Acc : "+String.format("%d",accuracy));
    }
}
