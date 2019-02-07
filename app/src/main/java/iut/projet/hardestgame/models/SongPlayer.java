package iut.projet.hardestgame.models;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

import iut.projet.hardestgame.R;

public class SongPlayer{

    private MediaPlayer player;


    public SongPlayer(Context context) throws IOException {
        player = new MediaPlayer();
        Uri myUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.giletsjaunes);
        player.setDataSource(context, myUri);
        player.prepare();
    }

    public SongPlayer(String url) throws IOException {
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDataSource(url);
        player.prepare();
    }

    public MediaPlayer getPlayer(){
        return player;
    }

    public void start(){
        player.start();
    }

    public void pause(){
        player.pause();
    }

    public void stop(){
        player.stop();
    }
}
