package iut.projet.hardestgame.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameManager;
import iut.projet.hardestgame.models.SongPlayer;


public class AncienneMainActivity extends AppCompatActivity {

    private static SongPlayer songPlayer = null;
    private static boolean restart = false;
    private static boolean isMusic;
    private final String myPref = "mypreferences";
    private final String music = "music";
    private final String level = "level";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(myPref,MODE_PRIVATE);
        isMusic = sharedPreferences.getBoolean(music,true);
        int lvl = sharedPreferences.getInt(level,1);
        if(GameManager.getLevel()<lvl)
            GameManager.setLevel(lvl);
        System.out.println("MainActi onCreate");
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
        System.out.println("MainActi onResume : "+GameManager.getLevel());

    }

    @Override
    protected void onPause() {
        songPlayer.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        System.out.println("STOP MAIN");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        //GameManager.setLevel(1);
        SharedPreferences sharedPreferences = getSharedPreferences(myPref,MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putBoolean(music,isMusic);
        ed.putInt(level,GameManager.getLevel());
        ed.apply();
        System.out.println("DESTROY MAIN");
        super.onDestroy();
    }

    public void showLevels(View view){
        restart = false;
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }

    public void startGame(View view){
        restart = false;
        System.out.println("MainActi startGame : "+GameManager.getLevel());
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
        AncienneMainActivity.isMusic = isMusic;
        if(!isMusic)
            songPlayer.stop();
    }
}
