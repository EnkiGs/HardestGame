package iut.projet.hardestgame.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.SongPlayer;

public class MainActivity extends AppCompatActivity {

    private static SongPlayer songPlayer = null;
    private static boolean restart = false;
    private static boolean isMusic;
    private final String myPref = "mypreferences";
    private final String music = "music";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(myPref,MODE_PRIVATE);
        isMusic = sharedPreferences.getBoolean(music,true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*if(songPlayer==null) {
            songPlayer = new SongPlayer();
            restart = false;
        }
        else{
            restart = true;
        }*/
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("RESTORE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!restart) {
            try {
                songPlayer = new SongPlayer();
                //songPlayer = new SongPlayer(getBaseContext(), R.raw.musiquedebut);
                songPlayer.putMusic(getBaseContext(), R.raw.musiquedebut);
            } catch (IOException e) {
                e.printStackTrace();
            }
            restart = true;
        }
        songPlayer.start();
    }

    @Override
    protected void onPause() {
        songPlayer.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences(myPref,MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putBoolean(music,isMusic);
        ed.apply();
    }


    public void showLevels(View view){
        restart = false;
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }

    public void startGame(View view){
        restart = false;
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void config(View view){
        restart = false;
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }



    public static SongPlayer getSongPlayer(){
        return songPlayer;
    }

    public static boolean isMusic() {
        return isMusic;
    }

    public static void setIsMusic(boolean isMusic) {
        MainActivity.isMusic = isMusic;
        if(!isMusic)
            songPlayer.stop();
    }
}
