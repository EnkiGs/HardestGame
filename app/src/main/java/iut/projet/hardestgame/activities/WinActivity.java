package iut.projet.hardestgame.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameManager;

public class WinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
    }

    public void onNextLevelClick(View view) {
        GameManager.nextLvl();
        if(GameManager.getLevel()>GameManager.getLvlMax()) {
            winnerOfTheGame();
            return;
        }
        Intent intent = new Intent(getApplicationContext(),GameActivity.class);
        startActivity(intent);
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }


    public void winnerOfTheGame() {
        Intent intent = new Intent(getApplicationContext(), GameWinnerActivity.class);
        startActivity(intent);
    }
}
