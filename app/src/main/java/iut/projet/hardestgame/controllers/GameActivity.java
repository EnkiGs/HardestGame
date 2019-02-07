package iut.projet.hardestgame.controllers;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;

public class GameActivity extends AppCompatActivity {

    SongPlayer songPlayer;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        try {
            songPlayer = new SongPlayer(getBaseContext());
            songPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
