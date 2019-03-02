package iut.projet.hardestgame.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;
import iut.projet.hardestgame.views.GameView;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    private int screenWidth;
    private int screenHeight;
    private ImageView arrowUp;
    private ImageView arrowDown;
    private ImageView arrowRight;
    private ImageView arrowLeft;
    private float arrowUpX;
    private float arrowUpY;
    private float arrowDownX;
    private float arrowDownY;
    private float arrowRoghtX;
    private float arrowRightY;
    private float arrowLeftX;
    private float arrowLeftY;
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private LinearLayout myView;
    private TextView viewDeaths;
    SongPlayer songPlayer = MainActivity.getSongPlayer();
    private static int nbDeaths = 0;
    private SensorManager sensorManager;
    private Sensor mAccelerator;
    GameView gameView;
    private float mSensorX = 0;
    private float mSensorY = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        arrowUp = findViewById(R.id.arrowUp);
        arrowDown = findViewById(R.id.arrowDown);
        arrowRight = findViewById(R.id.arrowRight);
        arrowLeft = findViewById(R.id.arrowLeft);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;

        arrowUp.setX(-80.0f);
        arrowUp.setY(+80.0f);
        arrowDown.setX(-80.0f);
        arrowDown.setY(screenHeight + 80.0f);
        arrowRight.setY(screenHeight + 80.0f);
        arrowRight.setX(screenWidth + 80.0f);
        arrowLeft.setY(-80.0f);
        arrowLeft.setY(-80.0f);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable (){
                    @Override
                    public void run(){
                        changePosition();
                    }
                });
            }
        }, 0,20);
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

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, mAccelerator, SensorManager.SENSOR_DELAY_NORMAL);

        gameView = new GameView(this,this);
        myView.addView(gameView);

        try {
            songPlayer = new SongPlayer(getBaseContext(), R.raw.musiquedebut);
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


    public void changePosition(){
        arrowUpY -= 10;
        if(arrowUp.getY() + arrowUp.getHeight() < 0){
            arrowUpX = (float) Math.floor(Math.random()*(screenWidth - arrowUp.getWidth()));
            arrowUpY = screenHeight + 100.0f;
        }
        arrowUp.setX(arrowUpX);
        arrowUp.setY(arrowUpY);

        arrowDownY += 10;
        if(arrowDown.getY() > screenHeight){
            arrowDownX = (float)Math.floor(Math.random()*(screenWidth - arrowDown.getWidth()));
            arrowDownY = -100.0f;
        }
        arrowDown.setX(arrowDownX);
        arrowDown.setY(arrowDownY);

        arrowRoghtX += 10;
        if(arrowRight.getX()> screenWidth){
            arrowRoghtX = -100.0f;
            arrowRightY = (float)Math.floor(Math.random() * (screenHeight - arrowRight.getHeight()));
        }
        arrowRight.setY(arrowRightY);
        arrowRight.setX(arrowRoghtX);

        arrowLeftX -= 10;
        if(arrowLeft.getX() + arrowLeft.getWidth() > 0){
            arrowLeftX = screenWidth + 100.0f;
            arrowLeftY = (float)Math.floor(Math.random() * screenHeight - arrowLeft.getHeight());
        }
        arrowLeft.setX(arrowLeftX);
        arrowLeft.setY(arrowLeftY);
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

    public static int  getNbDeaths(){
        return nbDeaths;
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
