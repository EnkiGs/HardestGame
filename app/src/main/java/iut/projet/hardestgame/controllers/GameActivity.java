package iut.projet.hardestgame.controllers;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;
import iut.projet.hardestgame.views.GameView;

public class GameActivity extends AppCompatActivity implements SensorEventListener{

    //private GameLoop game;
    private GameView game;
    SongPlayer songPlayer;
    private SensorManager sensorManager;
    private Sensor mAccelerator;
    private float mSensorX = 0;
    private float mSensorY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //game = new GameLoop(this);
        //setContentView(game.screen);
        setContentView(R.layout.activity_game);
        //game.initGame(this);
        LinearLayout rootLayout = findViewById(R.id.gameA);
        game = new GameView(this, this);
        rootLayout.addView(game);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, mAccelerator, SensorManager.SENSOR_DELAY_NORMAL);

        try {
            songPlayer = new SongPlayer(getBaseContext());
            //songPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ((Button) findViewById(R.id.buttonRetour)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
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
        //game.running = false;
        sensorManager.unregisterListener(this);
        super.onDestroy();
    }




    @Override
    public void onSensorChanged(SensorEvent event) {
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
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public float getmSensorX() {
        return mSensorX;
    }

    public float getmSensorY() {
        return mSensorY;
    }
}
