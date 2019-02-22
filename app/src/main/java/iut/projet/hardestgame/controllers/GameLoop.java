package iut.projet.hardestgame.controllers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;
import android.widget.Button;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.views.GameView;

public class GameLoop extends Thread {



    /** Variable booléenne pour arrêter le jeu */
    public boolean running;

    /**
     * durée de la pause entre chaque frame
     * du jeu pour frame per second FPS=10
     * on a sleepTime=100
     */
    private long fps = 10;
    private long sleepTime = 1/fps*1000;

    /** Ecran de jeu */
    public GameView screen;

    public GameActivity acti;

    /** le dernier évenement enregistré sur l'écran*/
    public int lastEvent;

    /** Position de l'image que nous dessinons sur l'écran */
    private float x, y;

    /** contexte de l'application */
    private Context context;
    /** activer ou désactiver l'animation*/
    private boolean animate;

    public GameLoop(GameActivity a){
        acti = a;
    }

    public void initGame(Context context) {
        this.context = context;
        animate = true;
        running = true;
        this.screen = new GameView(context, this);
        x = screen.getCurrX();
        y = screen.getCurrY();
    }

    /** la boucle de jeu */
    @Override
    public void run() {
        long startTime;
        long elapsedTime; // durée de (update()+render())
        long sleepCorrected; // sleeptime corrigé
        while (this.running) {
            startTime = System.currentTimeMillis();
            this.processEvents();
            this.update();
            this.render();
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepCorrected = sleepTime - elapsedTime;
            // si jamais sleepCorrected<0 alors faire une pause de 1 ms
            if (sleepCorrected < 0) {
                sleepCorrected = 1;
            }
            try {
                Thread.sleep(sleepCorrected > 0 ? sleepCorrected : 1);
            } catch (InterruptedException ignored) {
            }
            // calculer le FSP
            fps = (int) (1000/(System.currentTimeMillis() - startTime));
            sleepTime = 1/fps*1000;
        }



    }

    /** Dessiner les composant du jeu sur le buffer de l'écran*/
    private void render() {
        screen.setCurrX(x - 1*acti.getmSensorX());
        screen.setCurrY(y + 1*acti.getmSensorY());

        screen.setBallColor(Color.RED);
        screen.invalidate();
    }

    /** Mise à jour des composants du jeu
     *  Ici nous déplaçon le personnage avec la vitesse vx
     *  S'il sort de l'écran, on le fait changer de direction
     *  */
    private void update() {
        if(!this.animate) return;
        float oldX = x;
        float oldY = y;
        x = x - 1*acti.getmSensorX();
        y = y - 1*acti.getmSensorY();
        if (x < 0 || x > screen.isWidth() - screen.getRadius()) {
            x = oldX;
        }
        if (y < 0 || y > screen.isHeight() - screen.getRadius()) {
            y = oldY;
        }
    }

    /** Ici on va faire en sorte que lorsqu'on clique sur l'écran,
     * L'animation s'arrête/redémarre
     * */
    private void processEvents() {
        if (lastEvent == MotionEvent.ACTION_BUTTON_PRESS) {
            this.animate = ! this.animate;
        }
        lastEvent = -1;
    }
}
