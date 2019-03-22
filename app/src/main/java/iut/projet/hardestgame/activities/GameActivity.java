package iut.projet.hardestgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameManager;

public class GameActivity extends AppCompatActivity{

    private GameManager m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        m = new GameManager(this, this);
        System.out.println("CREATE");
        m.setMusic();
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("START");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        m.relaunchGame();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("RESUME");
        MainActivity.getSongPlayer().start();
        System.out.println("GameActi onResume : "+GameManager.getLevel());

    }

    @Override
    protected void onPause() {
        System.out.println("PAUSE");
        if(MainActivity.isMusic())
            MainActivity.pauseMusic();
        m.stopRunning();
        super.onPause();
    }

    @Override
    protected void onStop() {
        System.out.println("STOP");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("DESTROY");
        m.stop();
        super.onDestroy();
    }

    public void backAction(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LevelsActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    public void endGame() {
        if(GameManager.getLevel()==GameManager.getLvlMax()) {
            Intent intent = new Intent(getApplicationContext(), GameWinnerActivity.class);
            startActivity(intent);
        }
        else {
            GameManager.nextLvl();
            Intent intent = new Intent(getApplicationContext(), WinActivity.class);
            startActivity(intent);
        }
    }

    public void loseGame() {
        Intent intent = new Intent(getApplicationContext(),LoseActivity.class);
        startActivity(intent);
    }
}
