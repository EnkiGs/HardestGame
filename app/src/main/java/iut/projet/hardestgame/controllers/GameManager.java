package iut.projet.hardestgame.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import iut.projet.hardestgame.activities.GameActivity;
import iut.projet.hardestgame.activities.MainActivity;
import iut.projet.hardestgame.R;
import iut.projet.hardestgame.models.Arrival;
import iut.projet.hardestgame.models.Box;
import iut.projet.hardestgame.models.Collisionable;
import iut.projet.hardestgame.models.EnemyH;
import iut.projet.hardestgame.models.EnemyV;
import iut.projet.hardestgame.models.Player;
import iut.projet.hardestgame.models.Tile;
import iut.projet.hardestgame.views.GameView;

public class GameManager implements SensorEventListener {

    private static int level = 1;
    private static int lvlMax = 2;
    private static int nbDeaths = 0;
    private GameLoop gameLoop;
    private GameView gameView;
    private GameActivity ga;
    private SensorManager sensorManager;
    private Sensor mAccelerator;
    private float mSensorX = 0;
    private float mSensorY = 0;
    private Context context;
    Collisionable[] tab;
    private boolean stopped;
    private int screenWidth;
    private int screenHeight;
    private int posDepX;
    private int posDepY;

    public GameManager(GameActivity g, Context context){
        this.context = context;
        ga = g;
        if(level<=0)
            level=1;
        WindowManager w = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE));
        Point size = new Point();
        w.getDefaultDisplay().getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        createLevel();
        gameView = new GameView(context,tab);
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mAccelerator = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorOn();
        LinearLayout rootLayout = ga.findViewById(R.id.gameA);
        rootLayout.addView(gameView);
       //MainActivity.getSongPlayer().stop();

        ga.findViewById(R.id.buttonRetour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ga.backAction();
            }
        });

        gameLoop = new GameLoop(this);
        gameLoop.running(true);
        stopped = false;
        gameLoop.start();
    }


    public void sensorOn(){
        sensorManager.registerListener(this, mAccelerator, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void setMusic(){
        try {
            MainActivity.getSongPlayer().putMusic(context, R.raw.giletsjaunes); //= new SongPlayer(getBaseContext(), R.raw.musiquedebut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void relaunchGame(){
        gameLoop = new GameLoop(this);
        gameLoop.running(true);
        stopped = false;
        gameLoop.start();
    }

    private void createLevel() {
        Bitmap[] bitmaps = getBitmaps();
        String lvl = "lvl"+level;
        //InputStream inputStream = ga.getResources().openRawResource(R.raw.);
        InputStream inputStream = context.getResources().openRawResource(context.getResources().getIdentifier(lvl,"raw",context.getPackageName()));
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
                int departH = (screenWidth-nbC*Collisionable.TILE_SIZE)/2;
                int departV =  (screenHeight-nbL*Collisionable.TILE_SIZE)/2;
                for (char c : tabL) {
                    if(c=='\n') {
                        i++;
                        j=0;
                    }
                    else {
                        Collisionable col = null;
                        switch (c){
                            case '1':
                                col = new Tile(departH+Collisionable.TILE_SIZE*j,departV+Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE, bitmaps[0]);
                                break;
                            case '2':
                                posDepX = departH+Collisionable.TILE_SIZE*j;
                                posDepY = departV+Collisionable.TILE_SIZE*i;
                                col = new Player(posDepX,posDepY,Collisionable.TILE_SIZE-20, Collisionable.TILE_SIZE-20, bitmaps[1]);
                                break;
                            case '3':
                                col = new Arrival(departH+Collisionable.TILE_SIZE*j,departV+Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE, bitmaps[2]);
                                break;
                            case '4':
                                col = new EnemyH(departH+Collisionable.TILE_SIZE*j,departV+Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE,Collisionable.TILE_SIZE, bitmaps[3]);
                                break;
                            case '5':
                                col = new EnemyV(departH+Collisionable.TILE_SIZE*j,departV+Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE,Collisionable.TILE_SIZE, bitmaps[3]);
                                break;
                            case '6':
                                col = new Tile(departH+Collisionable.TILE_SIZE*j,departV+Collisionable.TILE_SIZE*i,Collisionable.TILE_SIZE, bitmaps[4]);
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
        bitmaps[0] = ((BitmapDrawable) ResourcesCompat.getDrawable(context.getResources(),R.drawable.tile,null)).getBitmap();
        bitmaps[1] = ((BitmapDrawable) ResourcesCompat.getDrawable(context.getResources(),R.drawable.player,null)).getBitmap();
        bitmaps[2] = ((BitmapDrawable) ResourcesCompat.getDrawable(context.getResources(),R.drawable.arrival,null)).getBitmap();
        bitmaps[3] = ((BitmapDrawable) ResourcesCompat.getDrawable(context.getResources(),R.drawable.ennemi,null)).getBitmap();
        bitmaps[4] = ((BitmapDrawable) ResourcesCompat.getDrawable(context.getResources(),R.drawable.tile,null)).getBitmap();
        return bitmaps;
    }


    public void update() {
        ga.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateColl();
                if(stopped)
                    return;
                gameView.update();
            }
        });
    }

    private void updateColl(){

        for(Collisionable c : tab){
            if(c==null)
                continue;
            if(stopped)
                break;
            float[] newPos;
            switch(c.getClass().getSimpleName()) {
                case "Player":
                    updatePlayer((Player) c);
                    break;
                case "EnemyH":
                    newPos = new float[]{c.getX()+2*level*0.75F*((EnemyH)c).getSens(),c.getY()};
                    newPos = updateEnemy(newPos,(Box)c);
                    if(c.getX()==newPos[0]){
                        ((EnemyH)c).invertSens();
                    }
                    ((EnemyH)c).move(newPos[0],newPos[1]);
                    break;
                case "EnemyV":
                    newPos = new float[]{c.getX(),c.getY()+2*((EnemyV)c).getSens()};
                    newPos = updateEnemy(newPos,(Box)c);
                    if(c.getY()==newPos[1]){
                        ((EnemyV)c).invertSens();
                    }
                    ((EnemyV)c).move(newPos[0],newPos[1]);
                    break;
                default:
                    break;
            }
        }
    }

    private float[] updateEnemy(float[] newPos, Box e) {
        float[] pos;
        for (Collisionable col: tab) {
            if (col == null)
                continue;
            switch (col.getClass().getSimpleName()) {
                case "Tile":
                    pos = e.checkCollisions((Tile)col,newPos);
                    if(pos != null)
                        newPos = pos;
                    break;
                default:
                    break;
            }
        }
        return newPos;
    }

    private void updatePlayer(Player p){
        float[] newPos = {p.getX(),p.getY()};
        if(Math.abs(mSensorX)>0.5)
            newPos[0] -= mSensorX*3;
        if (Math.abs(mSensorY)>0.5)
            newPos[1] += mSensorY*3;
        float[] pos;

        for (Collisionable col: tab) {
            if (col == null)
                continue;
            switch (col.getClass().getSimpleName()) {
                case "Tile":
                    pos = p.checkCollisions((Tile)col,newPos);
                    if(pos != null)
                        newPos = pos;
                    break;
                case "Arrival":
                    if(p.checkArrival((Arrival) col)){
                        stopped = true;
                        endGame();
                    }
                    break;
                case "EnemyH":
                case "EnemyV":
                    pos = p.checkCollisions((Box)col,newPos);
                    if(pos != null) {
                        newPos[0] = posDepX;
                        newPos[1] = posDepY;
                        nbDeaths++;
                        checkDeaths();
                    }
                    break;
                default:
                    break;
            }
            if(stopped)
                break;
        }
        p.move(newPos[0],newPos[1]);

    }

    private void checkDeaths(){
        if(nbDeaths==5){
            level = 1;
            nbDeaths = 0;
            ga.loseGame();
        }
    }

    public void endGame(){
        stopRunning();
        stop();
        ga.endGame();
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

    public void stopRunning() {
        gameLoop.running(false);
        try {
            gameLoop.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    public boolean isStopped(){
        return stopped;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int lvl) {
        level = lvl;
    }

    public static void nextLvl(){
        level++;
    }

    public static void prevLvl() {
        level--;
    }

    public static int getLvlMax() {
        return lvlMax;
    }


}