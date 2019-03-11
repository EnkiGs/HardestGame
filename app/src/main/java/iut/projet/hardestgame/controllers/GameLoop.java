package iut.projet.hardestgame.controllers;

public class GameLoop extends Thread {

    private boolean running = true;
    private GameManager gm;

    public GameLoop(GameManager g){
        gm = g;
    }
    @Override
    public void run() {
/*        long startTime;
        long elapsedTime; // durée de (update()+render())
        long sleepCorrected; // sleeptime corrigé*/
        try {
            Thread.sleep(60);
        } catch (InterruptedException ignored) {
        }
        System.out.println("DEHORS");
        while (this.running) {
            System.out.println("RUN");
            beep();
            try {
                Thread.sleep(30);
            } catch (InterruptedException ignored) {
            }

            /*startTime = System.currentTimeMillis();
            *//*this.processEvents();

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
            sleepTime = 1/fps*1000;*/
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
