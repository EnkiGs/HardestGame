package iut.projet.hardestgame.controllers;

public class GameLoop extends Thread {
    private int FPS = 40;
    private boolean running = true;
    private GameManager gm;

    public GameLoop(GameManager g){
        gm = g;
    }
    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        try {
            sleep(60);
        } catch (InterruptedException ignored) {
        }
        while (this.running) {
            startTime = System.currentTimeMillis();
            beep();
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(16);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private void beep() {
        gm.update();
    }

    public boolean isRunning(){
        return running;
    }

    public void running(boolean b){
        running = b;
    }
}
