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
        Intent intent = new Intent(getApplicationContext(),GameActivity.class);
        startActivity(intent);
    }

    public void onMenuClick(View view) {
        System.out.println("WinActi : "+GameManager.getLevel());
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        super.onBackPressed();
    }
}
