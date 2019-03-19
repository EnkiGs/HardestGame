package iut.projet.hardestgame.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Locale;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameManager;
import iut.projet.hardestgame.models.LevelButton;

public class LevelsActivity extends AppCompatActivity {
    private TableLayout table;
    private LevelButton[] buttons;
    private int nbCol;
    private int nbLines;

    private int screenWidth;
    private int screenHeight;

    private WindowManager w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        w = ((WindowManager)getSystemService(WINDOW_SERVICE));
        Point size = new Point();
        w.getDefaultDisplay().getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        System.out.println(screenHeight+"/"+screenWidth);
        nbCol = screenWidth/ LevelButton.getSize();

        table = findViewById(R.id.LevelsTable);
        buttons = new LevelButton[GameManager.getLvlMax()];
        int nbLvls = GameManager.getLvlMax();
        for(int i=0;i< nbLvls;i++){
            LevelButton b = new LevelButton(this);
            b.setText(String.format("%d",i+1));
            buttons[i]=b;
        }
        nbLines = nbLvls/nbCol+1;
        int nbLvlsRestants = nbLvls;
        int col;
        for (int i=0;i<nbLines;i++){
            TableRow t = new TableRow(this);
            if(nbLvlsRestants<nbCol)
                col = nbLvlsRestants;
            else
                col = nbCol;
            for (int j=0;j<col;j++){
                t.addView(buttons[j+i*nbCol]);
                nbLvlsRestants--;
            }
            table.addView(t);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        super.onBackPressed();
    }
}
