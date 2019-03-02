package iut.projet.hardestgame.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import iut.projet.hardestgame.R;

public class LevelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
    }

    public void goTolevel1(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
