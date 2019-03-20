package iut.projet.hardestgame.activities;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.controllers.GameManager;
import iut.projet.hardestgame.views.LevelButton;

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
        nbCol = screenWidth/ LevelButton.getSize();

        table = findViewById(R.id.LevelsTable);
        buttons = new LevelButton[GameManager.getLvlMax()];
        int nbLvls = GameManager.getLvlMax();
        int currentLvl = GameManager.getLevel();
        for(int i=0;i< nbLvls;i++){
            LevelButton b = new LevelButton(this,String.format("%d",i+1), i+1<=currentLvl);
            table.setColumnShrinkable(i,true);
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

    public void levelChoosed(int lvl){
        GameManager.setLevel(lvl);
        startActivity(new Intent(this,GameActivity.class));
    }
}
