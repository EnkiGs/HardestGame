package iut.projet.hardestgame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;

public class MainActivity extends AppCompatActivity {

    private static SongPlayer songPlayer = null;
    private boolean recreate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(songPlayer==null) {
            songPlayer = new SongPlayer();
            recreate = false;
        }
        else{
            recreate = true;
        }
    }

    public void showLevels(View view){
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }

    public void startGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void config(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!recreate) {
            try {
                //songPlayer = new SongPlayer(getBaseContext(), R.raw.musiquedebut);
                songPlayer.putMusic(getBaseContext(), R.raw.musiquedebut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        songPlayer.start();
    }

    @Override
    protected void onPause() {
        songPlayer.pause();
        super.onPause();
    }

    public static SongPlayer getSongPlayer(){
        return songPlayer;
    }

}
