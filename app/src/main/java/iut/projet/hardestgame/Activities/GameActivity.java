package iut.projet.hardestgame.Activities;

import android.app.Activity;
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
import iut.projet.hardestgame.controllers.GameManager;
import iut.projet.hardestgame.views.GameView;

public class GameActivity extends AppCompatActivity{

    private GameManager m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        m = new GameManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.getSongPlayer().start();
    }

    @Override
    protected void onPause() {
        MainActivity.getSongPlayer().stop();
        m.stopRunning();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        m.stop();
        super.onDestroy();
    }

    public void backAction(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
