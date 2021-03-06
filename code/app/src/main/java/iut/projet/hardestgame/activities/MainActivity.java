package iut.projet.hardestgame.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.IOException;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameManager;
import iut.projet.hardestgame.models.SongPlayer;


public class MainActivity extends AppCompatActivity {

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        SharedPreferences sharedPreferences = getSharedPreferences(myPref,MODE_PRIVATE);
        isMusic = sharedPreferences.getBoolean(music,true);
/*        int lvl = sharedPreferences.getInt(level,1);
        if(GameManager.getLvlMin()<lvl)
            GameManager.setLvlMin(lvl);*/
        System.out.println("MAIN2");
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
        if(isMusic())
            pauseMusic();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        //GameManager.setLevel(1);
        SharedPreferences sharedPreferences = getSharedPreferences(myPref,MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putBoolean(music,isMusic);
        //ed.putInt(level,GameManager.getLvlMin());
        ed.apply();
        System.out.println("DESTROY MAIN");
        super.onDestroy();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.buttonSettings :
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.aboutUS :
                Intent intentAbout = new Intent(this, UsActivity.class );
                startActivity(intentAbout);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void config(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void showLevels(View view){
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }

    public void showPlayers(View view){
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent) {
        restart = false;
        super.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {}

    /*
    public void startGame(View view){
        Intent intent = new Intent(this, LevelsActivity.class);
        startActivity(intent);
    }*/

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

    public static void pauseMusic(){
        songPlayer.pause();
    }
}
