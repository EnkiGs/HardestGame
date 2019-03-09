package iut.projet.hardestgame.controllers;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;

public class MainActivity extends AppCompatActivity {

    private static SongPlayer songPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            songPlayer = new SongPlayer(getBaseContext(), R.raw.musiquedebut);
            songPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final TextView textView = findViewById(R.id.title_app);
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,textView, ViewCompat.getTransitionName(textView));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }

    public void showLevels(View view){
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }

    public void startGame(View view){
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }

    public void config(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    public static SongPlayer getSongPlayer(){
        return songPlayer;
    }

}
