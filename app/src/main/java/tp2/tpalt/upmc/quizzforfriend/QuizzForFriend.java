package tp2.tpalt.upmc.quizzforfriend;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.ImageButton;

import java.io.File;
import java.util.List;

/**
 * Created by macbookpro on 10/10/2016.
 */
public class QuizzForFriend extends Application {

    private static MediaPlayer mediaPlayer;

    private static boolean soundMuted = false;

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

    public static boolean isPlaying(){
        return (mediaPlayer != null && mediaPlayer.isPlaying());
    }


    public void playRessource(int path){
        if(this.mediaPlayer != null && this.mediaPlayer.isPlaying()) return;
        this.mediaPlayer = MediaPlayer.create(getApplicationContext(), path);
        this.mediaPlayer.start();
        this.mediaPlayer.setLooping(true);
    }

    public static void muteSound(){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.setVolume(0.0f, 0.0f);
            soundMuted = true;
        }
    }

    public static void unMuteSound(){
        if(mediaPlayer != null && mediaPlayer.isPlaying()){
            mediaPlayer.setVolume(1.0f, 1.0f);
            soundMuted = false;
        }
    }

    public static boolean isSoundMuted() {
        return soundMuted;
    }


    public static void toogleThemeSound(ImageButton muteSoundBtn){
        if(isPlaying() && !isSoundMuted()){
            muteSound();
            muteSoundBtn.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off);
        }else {
            unMuteSound();
            muteSoundBtn.setBackgroundResource(android.R.drawable.ic_lock_silent_mode);
        }
    }

    public static void playToggleSound(Context context){
        MediaPlayer actionSound = MediaPlayer.create(context, R.raw.jar_deny);
        actionSound.start();
    }

    public static void playSelectPartySound(Context context){
        MediaPlayer actionSound = MediaPlayer.create(context, R.raw.music_marimba_chord);
        actionSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp=null;
            }

        });;
        actionSound.start();
    }


    public static void playLoadingActivity(Context context){
        MediaPlayer activitySound = MediaPlayer.create(context, R.raw.short_whoosh2);
        activitySound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp=null;
            }

        });
        activitySound.start();
    }
}
