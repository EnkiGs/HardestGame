package iut.projet.hardestgame.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.views.GameView;

import static android.content.Context.WINDOW_SERVICE;

public class GameLoop extends Thread implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor mAccelerator;
    float mSensorX = 0;
    float mSensorY = 0;
    /** Variable booléenne pour arrêter le jeu */
    public boolean running;

    /**
     * durée de la pause entre chaque frame
     * du jeu pour frame per second FPS=10
     * on a sleepTime=100
     */
    private long fps = 10;
    private long sleepTime = 1/fps*1000;

    /** Ecran de jeu */
    public GameView screen;

    /** le dernier évenement enregistré sur l'écran*/
    public int lastEvent;

    /** Position de l'image que nous dessinons sur l'écran */
    private float x, y;

    /** contexte de l'application */
    private Context context;
    /** activer ou désactiver l'animation*/
    private boolean animate;

    private LinearLayout rootLayout;
    public void initGame(Context context) {
        this.context = context;
        animate = true;
        running = true;
        this.screen = new GameView(context, this);
        x = screen.getCurrX();
        y = screen.getCurrY();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mAccelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, mAccelerator, SensorManager.SENSOR_DELAY_NORMAL);
        //sensorManager.unregisterListener(this);

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.d("Enki", ((Float)event.values[0]).toString());
        TextView t1 = screen.t1;
        TextView t2 = screen.t2;
        TextView t3 = screen.t3;
/*        TextView t2 = screen.findViewById(R.id.textview_sensor2);
        TextView t3 = screen.findViewById(R.id.textview_sensor3);*/
/*        t1.setText("val1 : "+String.format("%f",(Float)event.values[0]));
        t2.setText("val2 : "+String.format("%f",(Float)event.values[1]));*/
        //t3.setText("Acc3 : "+String.format("%f",(Float)event.values[2]));

        switch (((WindowManager)context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getRotation()) {
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
/*
        screen.setCurrX(x - 1*mSensorX);
        screen.setCurrY(y + 1*mSensorY);

        screen.setBallColor(Color.BLUE);
        screen.performClick();
        screen.invalidate();*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //((TextView)findViewById(R.id.textview_sensor)).setText("Acc : "+String.format("%d",accuracy));
    }



    /** la boucle de jeu */
    @Override
    public void run() {
        long startTime;
        long elapsedTime; // durée de (update()+render())
        long sleepCorrected; // sleeptime corrigé
        while (this.running) {
            startTime = System.currentTimeMillis();
            this.processEvents();
            this.update();
            this.render();
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepCorrected = sleepTime - elapsedTime;
            // si jamais sleepCorrected<0 alors faire une pause de 1 ms
            if (sleepCorrected < 0) {
                sleepCorrected = 1;
            }
            try {
                Thread.sleep(sleepCorrected > 0 ? sleepCorrected : 1);
            } catch (InterruptedException ignored) {
            }
            // calculer le FSP
            fps = (int) (1000/(System.currentTimeMillis() - startTime));
            sleepTime = 1/fps*1000;
        }
    }

    /** Dessiner les composant du jeu sur le buffer de l'écran*/
    private void render() {
        screen.setCurrX(x - 1*mSensorX);
        screen.setCurrY(y + 1*mSensorY);

        screen.setBallColor(Color.BLUE);
        screen.invalidate();
    }

    /** Mise à jour des composants du jeu
     *  Ici nous déplaçon le personnage avec la vitesse vx
     *  S'il sort de l'écran, on le fait changer de direction
     *  */
    private void update() {
        if(!this.animate) return;
        float oldX = x;
        float oldY = y;
        x = x - 1*mSensorX;
        y = y - 1*mSensorY;
        if (x < 0 || x > screen.isWidth() - screen.getRadius()) {
            x = oldX;
        }
        if (y < 0 || y > screen.isHeight() - screen.getRadius()) {
            y = oldY;
        }
    }

    /** Ici on va faire en sorte que lorsqu'on clique sur l'écran,
     * L'animation s'arrête/redémarre
     * */
    private void processEvents() {
        if (lastEvent == MotionEvent.ACTION_BUTTON_PRESS) {
            this.animate = ! this.animate;
        }
        lastEvent = -1;
    }
}
