package iut.projet.hardestgame.models;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;
import java.io.Serializable;

import iut.projet.hardestgame.R;
import iut.projet.hardestgame.activities.MainActivity;

public class SongPlayer implements Serializable {
    private MediaPlayer player;
    private int currentPosition = 0;

  /*  public SongPlayer(Context context, int res) throws IOException {
        player = new MediaPlayer();
        Uri myUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + res);
        player.setDataSource(context, myUri);
        player.prepare();
    }

    public SongPlayer(String url) throws IOException {
        player = new MediaPlayer();
        //player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDataSource(url);
        player.prepare();
    }
*/
    public SongPlayer(){
        player = new MediaPlayer();
        player.setLooping(true);
    }

    public MediaPlayer getPlayer(){
        return player;
    }

    public void start(){
       // player.seekTo(currentPosition);
        if(MainActivity.isMusic())
            player.start();
    }

    public void pause(){

        player.pause();
        //currentPosition = player.getCurrentPosition();
    }

    public void stop(){
        pause();
        player.stop();
    }

    public void putMusic(Context context, int res)  throws IOException {
        Uri myUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + res);
        player.reset();
        player.setDataSource(context, myUri);
        player.prepare();
    }

    public int getTemps() {
        return currentPosition;
    }

    public void setTemps(int temps){
        //player.seekTo(temps);
        currentPosition = temps;
    }
}
