package tp2.tpalt.upmc.quizzforfriend;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageButton;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 10/10/2016.
 */
public class QuizzForFriend extends Application {

    private static Context mContext;
    public static String gameMode;

    public static Context getContext() {
        return mContext;
    }

    private List<ThemeList> themes;

    private List<Partie> parties;

    private static MediaPlayer mediaPlayer;
    private static MediaPlayer currentMediaPlayer;

    private static boolean soundMuted = false;

    public static AudioManager audioManager;

    public static Integer[] quizes={R.raw.quiz_aeronotiques, R.raw.quiz_disney1, R.raw.quiz_disney2, R.raw.quiz_disney3, R.raw.quiz_music1, R.raw.quiz_simpsons};

    public QuizzForFriend() {
        this.themes = new ArrayList<>();
    }

    public QuizzForFriend(List<ThemeList> themes) {
        this.themes = themes;
    }

    public QuizzForFriend(List<ThemeList> themes, List<Partie> p) {
        this.themes = themes;
        this.parties = p;
    }


    public List<Partie> getParties() {
        return parties;
    }
    public void setParties(List<Partie> p) {
        this.parties = p;
    }

    public List<ThemeList> getThemesLists() {
        return themes;
    }
    public void setThemesList(List<ThemeList> t) {
        this.themes = t;
    }

    private void loadThemesList() throws Exception{
        if(themes != null) themes.clear();
        for (int i = 0; i < quizes.length; i++) {
            String thm = loadFromRessource(quizes[i]);
            JSONObject jsonObject = new JSONObject(thm);
            ThemeList neoThm = new ThemeList(jsonObject.get("name").toString(),jsonObject.get("description").toString());
            themes.add(neoThm);
        }
    }

    public String loadFromRessource(int resource) throws Exception {
        String jsonContent = "";
        try {
            InputStream jsonPath = mContext.getResources().openRawResource(resource);
            InputStream is = jsonPath;
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonContent = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return jsonContent;
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
        currentMediaPlayer = mediaPlayer;
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
            muteSoundBtn.setBackgroundResource(R.drawable.disablesound);
        }else {
            unMuteSound();
            muteSoundBtn.setBackgroundResource(R.drawable.enable_sound);
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



    public static void playSelectTheme(Context context){
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



    public static void playGoodAnswerSound(Context context){
        MediaPlayer activitySound = MediaPlayer.create(context, R.raw.xylophone_affirm);
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

    public static void playBadAnswer(Context context){
        MediaPlayer actionSound = MediaPlayer.create(context, R.raw.jar_deny);
        actionSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp=null;
            }

        });
        actionSound.start();
    }

    public List<ThemeList> getThemeList() {
        return this.themes;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        try {
            this.loadThemesList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void crossFade(Context context) {
        QuizzForFriend.fadeOut(currentMediaPlayer, 2000);
        MediaPlayer auxPlayer = MediaPlayer.create(context, R.raw.on_game);
        auxPlayer.setLooping(true);
        QuizzForFriend.fadeIn(auxPlayer, 2000);
        mediaPlayer = currentMediaPlayer = auxPlayer;
    }
    public static void crossFade(Context context, int soundId) {
        QuizzForFriend.fadeOut(currentMediaPlayer, 2000);
        MediaPlayer __auxPlayer = MediaPlayer.create(context, soundId);
        QuizzForFriend.fadeIn(__auxPlayer, 2000);
        mediaPlayer = currentMediaPlayer = __auxPlayer;
    }
    public static void crossFade(Context context, int soundId, boolean looping) {
        QuizzForFriend.fadeOut(currentMediaPlayer, 2000);
        MediaPlayer _auxPlayer = MediaPlayer.create(context, soundId);
        _auxPlayer.setLooping(looping);
        QuizzForFriend.fadeIn(_auxPlayer, 2000);
        mediaPlayer = currentMediaPlayer = _auxPlayer;
    }

    public static void fadeOut(final MediaPlayer _player, final int duration) {
        QuizzForFriend dummy = new QuizzForFriend();
        final float deviceVolume = dummy.getDeviceVolume();
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            private float time = duration;
            private float volume = 0.0f;

            @Override
            public void run() {
                if (!_player.isPlaying())
                    _player.start();
                // can call h again after work!
                time -= 100;
                volume = (deviceVolume * time) / duration;
                _player.setVolume(volume, volume);
                if (time > 0)
                    h.postDelayed(this, 100);
                else {
                    _player.stop();
                    _player.release();
                }
            }
        }, 100); // 1 second delay (takes millis)


    }

    public static void fadeIn(final MediaPlayer _player, final int duration) {
        QuizzForFriend dummy = new QuizzForFriend();
        final float deviceVolume = dummy.getDeviceVolume();
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            private float time = 0.0f;
            private float volume = 0.0f;

            @Override
            public void run() {
                if (!_player.isPlaying())
                    _player.start();
                // can call h again after work!
                time += 100;
                volume = (deviceVolume * time) / duration;
                _player.setVolume(volume, volume);
                if (time < duration)
                    h.postDelayed(this, 100);
            }
        }, 100); // 1 second delay (takes millis)

    }
    public float getDeviceVolume() {
        audioManager = (AudioManager) mContext.getSystemService(AUDIO_SERVICE);
        int volumeLevel = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        return (float) volumeLevel / maxVolume;
    }
}
