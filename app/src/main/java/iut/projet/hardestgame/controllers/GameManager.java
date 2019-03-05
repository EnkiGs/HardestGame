package iut.projet.hardestgame.controllers;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.io.IOException;

import iut.projet.hardestgame.Activities.GameActivity;
import iut.projet.hardestgame.Activities.MainActivity;
import iut.projet.hardestgame.R;
import iut.projet.hardestgame.views.GameView;

import static android.content.Context.WINDOW_SERVICE;

public class GameManager implements SensorEventListener {

    private GameLoop gameLoop;
    private GameView gameView;
    private GameActivity ga;
    private SensorManager sensorManager;
    private Sensor mAccelerator;
    private float mSensorX = 0;
    private float mSensorY = 0;

    public GameManager(GameActivity g){
        ga = g;
        gameView = new GameView(ga.getApplicationContext());
        sensorManager = (SensorManager) ga.getSystemService(Context.SENSOR_SERVICE);
        mAccelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, mAccelerator, SensorManager.SENSOR_DELAY_NORMAL);

        LinearLayout rootLayout = ga.findViewById(R.id.gameA);
        rootLayout.addView(gameView);
       //MainActivity.getSongPlayer().stop();
        try {
            MainActivity.getSongPlayer().putMusic(ga.getApplicationContext(), R.raw.giletsjaunes); //= new SongPlayer(getBaseContext(), R.raw.musiquedebut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ga.findViewById(R.id.buttonRetour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ga.backAction();
            }
        });


        gameLoop = new GameLoop(this);
        gameLoop.start();
    }


    public void update() {
        ga.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameView.update(mSensorX, mSensorY);
            }
        });
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = (float)(Math.round(-event.values[1]*100.0)/100.0);
        float y = (float)(Math.round(event.values[0]*100.0)/100.0);
        //if(x<mSensorX-1 || x>mSensorX+1)
        mSensorX = x;
        //if(y<mSensorY-1 || y>mSensorY+1)
        mSensorY = y;
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

    public void stopRunning() {
        gameLoop.running(false);
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }
}
