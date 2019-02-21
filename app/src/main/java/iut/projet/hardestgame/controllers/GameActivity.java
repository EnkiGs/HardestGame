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

public class GameActivity extends AppCompatActivity implements SensorEventListener {


    SongPlayer songPlayer;

    private SensorManager sensorManager;
    private Sensor mAccelerator;

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        LinearLayout rootLayout = findViewById(R.id.gameA);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        gameView = new GameView(this){
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
            songPlayer = new SongPlayer(getBaseContext());
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
        //Log.d("Enki", ((Float)event.values[0]).toString());
        TextView t1 = ((TextView)findViewById(R.id.textview_sensor1));
        TextView t2 = ((TextView)findViewById(R.id.textview_sensor2));
        TextView t3 = ((TextView)findViewById(R.id.textview_sensor3));
/*        t1.setText("val1 : "+String.format("%f",(Float)event.values[0]));
        t2.setText("val2 : "+String.format("%f",(Float)event.values[1]));*/
        //t3.setText("Acc3 : "+String.format("%f",(Float)event.values[2]));
        float mSensorX = 0;
        float mSensorY = 0;
        switch (((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                mSensorX = event.values[0];
                mSensorY = event.values[1];
                t3.setText("Rot : 0 ");
                break;
            case Surface.ROTATION_90:
                mSensorX = -event.values[1];
                mSensorY = event.values[0];
                t3.setText("Rot : 90");
                break;
            case Surface.ROTATION_180:
                mSensorX = -event.values[0];
                mSensorY = -event.values[1];
                t3.setText("Rot : 180");
                break;
            case Surface.ROTATION_270:
                mSensorX = event.values[1];
                mSensorY = -event.values[0];
                t3.setText("Rot : 270");
                break;
        }

        t1.setText("val1 : "+String.format("%f",mSensorX));
        t2.setText("val2 : "+String.format("%f",mSensorY));



            // FAIRE UNE BOUCLE DE JEU !!!

        gameView.setCurrX(gameView.getCurrX() - 1*mSensorX);
        gameView.setCurrY(gameView.getCurrY() + 1*mSensorY);

        gameView.setBallColor(Color.BLUE);
        gameView.performClick();
        gameView.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //((TextView)findViewById(R.id.textview_sensor)).setText("Acc : "+String.format("%d",accuracy));
    }
}
