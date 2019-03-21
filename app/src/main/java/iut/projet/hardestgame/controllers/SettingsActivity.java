package iut.projet.hardestgame.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;

public class SettingsActivity extends AppCompatActivity {
    private static SongPlayer songPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

}
