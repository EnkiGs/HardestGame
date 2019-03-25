package iut.projet.hardestgame.activities;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameManager;
import iut.projet.hardestgame.views.PlayerButton;

public class PlayerActivity extends AppCompatActivity {
    private TableLayout table;
    private PlayerButton[] buttons;
    private int nbCol;
    private int nbLines;

    private int screenWidth;
    private int screenHeight;

    private WindowManager w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        w = ((WindowManager)getSystemService(WINDOW_SERVICE));
        Point size = new Point();
        w.getDefaultDisplay().getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        nbCol = screenWidth/ PlayerButton.getSize();
        table = findViewById(R.id.PlayersTable);
        int nbP = GameManager.getNbPlayers();
        buttons = new PlayerButton[nbP];
        for(int i=0;i< nbP;i++){
            String p = "player"+(i+1)+"_mini";
            @DrawableRes int r = getResources().getIdentifier(p,"drawable",getPackageName());
            PlayerButton b = new PlayerButton(this,r,i);
            table.setColumnShrinkable(i,true);
            buttons[i]=b;
        }
        nbLines = nbP/nbCol+1;
        int nbPRestants = nbP;
        int col;
        for (int i=0;i<nbLines;i++){
            TableRow t = new TableRow(this);
            if(nbPRestants<nbCol)
                col = nbPRestants;
            else
                col = nbCol;
            for (int j=0;j<col;j++){
                t.addView(buttons[j+i*nbCol]);
                nbPRestants--;
            }
            table.addView(t);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        super.onBackPressed();
    }

    public void playerChoosed(int num) {
        ImageView i = findViewById(R.id.selectedPlayer);
        String p = "player"+(num+1)+"_mini";
        @DrawableRes int r = getResources().getIdentifier(p,"drawable",getPackageName());
        i.setImageResource(r);
    }

    public void onOKButtonPressed(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent) {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        super.startActivity(intent);
    }
}
