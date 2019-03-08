package iut.projet.hardestgame.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import iut.projet.hardestgame.Activities.GameActivity;
import iut.projet.hardestgame.Activities.MainActivity;
import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.Collisionable;
import iut.projet.hardestgame.models.Player;
import iut.projet.hardestgame.models.Tile;
import iut.projet.hardestgame.views.GameView;

public class GameManager implements SensorEventListener {

    private GameLoop gameLoop;
    private GameView gameView;
    private GameActivity ga;
    private SensorManager sensorManager;
    private Sensor mAccelerator;
    private float mSensorX = 0;
    private float mSensorY = 0;
    private Context context;
    Collisionable[] tab;

    public GameManager(GameActivity g, Context context){
        this.context = context;
        ga = g;
        createLevel();
        gameView = new GameView(context,tab);
        sensorManager = (SensorManager) ga.getSystemService(Context.SENSOR_SERVICE);
        mAccelerator = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, mAccelerator, SensorManager.SENSOR_DELAY_NORMAL);

        LinearLayout rootLayout = ga.findViewById(R.id.gameA);
        rootLayout.addView(gameView);
       //MainActivity.getSongPlayer().stop();
        try {
            MainActivity.getSongPlayer().putMusic(context, R.raw.giletsjaunes); //= new SongPlayer(getBaseContext(), R.raw.musiquedebut);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ga.findViewById(R.id.buttonRetour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ga.backAction();
            }
        });

        gameLoop = new GameLoop(this);
        gameLoop.start();
    }

    private void createLevel() {
        Bitmap[] bitmaps = getBitmaps();
        InputStream inputStream = ga.getResources().openRawResource(R.raw.lvl1);
        try {
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String str;
                StringBuilder buf = new StringBuilder();
                int nbC = Integer.parseInt(reader.readLine());
                int nbL = Integer.parseInt(reader.readLine());
                tab = new Collisionable[nbC*nbL];

                while ((str = reader.readLine()) != null) {
                    buf.append(str);
                    buf.append("\n");
                }
                reader.close();
                inputStream.close();
                char[] tabL = buf.toString().toCharArray();
                int i=0,j=0;
                for (char c : tabL) {
                    if(c=='\n') {
                        i++;
                        j=0;
                    }
                    else {
                        Collisionable col = null;
                        switch (c){
                            case '1':
                                col = new Tile(Collisionable.TILE_SIZE*j,Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE, bitmaps[0]);
                                break;
                            case '2':
                                col = new Player(Collisionable.TILE_SIZE*j,Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE-20, Collisionable.TILE_SIZE-20, bitmaps[1]);
                                break;
                            case '3':
                                col = new Tile(Collisionable.TILE_SIZE*j,Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE, bitmaps[2]);
                                break;
                            case '4':
                                col = new Tile(Collisionable.TILE_SIZE*j,Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE, bitmaps[3]);
                                break;
                            case '5':
                                col = new Tile(Collisionable.TILE_SIZE*j,Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE, bitmaps[4]);
                                break;
                            case '6':
                                col = new Tile(Collisionable.TILE_SIZE*j,Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE, bitmaps[5]);
                                break;
                            default:
                                break;
                        }
                        //Toast.makeText(context,"nbC : "+nbC+" / i : "+i+" / j :"+j+" / tot :"+(i*nbC+j),Toast.LENGTH_SHORT).show();
                        tab[i*nbC+j] = col;
                        j++;

                    }
                    //Toast.makeText(ga.getBaseContext(), "c'est un n",Toast.LENGTH_SHORT).show();
                    //Toast.makeText(ga.getBaseContext(), c+"",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(context, "FileNotFoundException", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(context, "FileNotFoundException", Toast.LENGTH_LONG);
        }
    }

    private Bitmap[] getBitmaps() {
        Bitmap[] bitmaps = new Bitmap[6];
        bitmaps[0] = ((BitmapDrawable)ga.getResources().getDrawable(R.drawable.tile)).getBitmap();
        bitmaps[1] = ((BitmapDrawable)ga.getResources().getDrawable(R.drawable.player)).getBitmap();
        bitmaps[2] = ((BitmapDrawable)ga.getResources().getDrawable(R.drawable.tile)).getBitmap();
        bitmaps[3] = ((BitmapDrawable)ga.getResources().getDrawable(R.drawable.tile)).getBitmap();
        bitmaps[4] = ((BitmapDrawable)ga.getResources().getDrawable(R.drawable.tile)).getBitmap();
        bitmaps[5] = ((BitmapDrawable)ga.getResources().getDrawable(R.drawable.tile)).getBitmap();
        return bitmaps;
    }


    public void update() {
        ga.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateColl();
                gameView.update();
            }
        });
    }

    private void updateColl(){

        for(Collisionable c : tab){
            if(c==null)
                continue;
            switch(c.getClass().getSimpleName()){
                case "Player":
                    updatePlayer((Player)c);
                    break;
                default:
                    break;
            }
        }
    }

    private void updatePlayer(Player p){
        float currX = p.getX()-mSensorX*3;
        float currY = p.getY()+mSensorY*3;

        for (Collisionable col: tab) {
            if (col == null)
                continue;
            switch (col.getClass().getSimpleName()) {
                case "Tile":
                    /*p.checkCollisionsX((Tile)col);
                    if(p.isCollisionLeft())
                        System.out.println("Left");
                    if(p.isCollisionRight())
                        System.out.println("Right");
                    if (p.isCollisionLeft() || p.isCollisionRight()) {
                        currX = oldX;
                    }
                    p.move(currX, currY);

                    p.checkCollisionsY((Tile)col);
                    if(p.isCollisionDown())
                        System.out.println("Down");
                    if(p.isCollisionUp())
                        System.out.println("Up");
                    if (p.isCollisionDown() || p.isCollisionUp()) {
                        currY = oldY;
                    }
                    p.move(currX, currY);*/
                    p.checkCollisions((Tile)col,currX,currY);
                    break;
                default:
                    break;
            }
        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = (float)(Math.round(-event.values[1]*100.0)/100.0);
        float y = (float)(Math.round(event.values[0]*100.0)/100.0);
        //if(x<mSensorX-1 || x>mSensorX+1)
        mSensorX = x;
        //if(y<mSensorY-1 || y>mSensorY+1)
        mSensorY = y;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public float getmSensorX() {
        return mSensorX;
    }

    public float getmSensorY() {
        return mSensorY;
    }

    public void stopRunning() {
        gameLoop.running(false);
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }
}
