package tp2.tpalt.upmc.quizzforfriend;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;
import java.util.List;

/**
 * Created by macbookpro on 10/10/2016.
 */
public class QuizzForFriend extends Application {

    private MediaPlayer mediaPlayer;

    private List<Partie> parties;

    //Ne jamais utiliser le contructeur par default
    public QuizzForFriend(){}

    public QuizzForFriend(List<Partie> p){
        this.parties = p;
    }

    public List<Partie> getParties() {
        return parties;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void audioPlayerFromPath(String path, String fileName){
        //set up MediaPlayer
        MediaPlayer mp = new MediaPlayer();

        try {
            mp.setDataSource(path + File.separator + fileName);
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void audioPlayer(int path){
        if(this.mediaPlayer != null && this.mediaPlayer.isPlaying()) return;
        this.mediaPlayer = MediaPlayer.create(getApplicationContext(), path);
        this.mediaPlayer.start();
        this.mediaPlayer.setLooping(true);
    }
}
