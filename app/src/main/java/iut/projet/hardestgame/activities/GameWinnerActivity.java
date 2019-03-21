package iut.projet.hardestgame.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import iut.projet.hardestgame.R;

public class GameWinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_winner);
    }

    public void onMenuClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Main2Activity.class));
        super.onBackPressed();
    }
}
