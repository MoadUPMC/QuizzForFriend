package tp2.tpalt.upmc.quizzforfriend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected QuizzForFriend quizzForFriendApp;

    private Button muteSoundBtn;
    private Button partieSolo;
    private Button partieVersus;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do
            //nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        mContentView = findViewById(R.id.fullscreen_content);
        quizzForFriendApp = (QuizzForFriend) getApplication();
        quizzForFriendApp.playRessource(R.raw.intro_remix);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
        muteSoundBtn = (Button) findViewById(R.id.muteSound);
        partieSolo  = (Button)findViewById(R.id.boutonSolo);
        partieVersus = (Button)findViewById(R.id.boutonVersus);

    partieSolo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent (getApplicationContext(), ThemeActivity.class);
            startActivity(i);
        }
    });
    }

/*
    public void playDirection(View v){
        Intent itnt = intentForId(v);
        //itnt.
        startActivity(itnt);
    }

    private Intent intentForId(View v){
        Toast.makeText(quizzForFriendApp, "LOL", Toast.LENGTH_SHORT).show();
        return new Intent (getApplicationContext(), ThemeActivity.class);

        if (v.getId() == R.id.boutonSolo){
            return new Intent(getApplicationContext(), ThemeActivity.class);}
        else {
            //On passe un theme a l'application au hasard ici
            return new Intent (getApplicationContext(), ThemeActivity.class);
        }
    }
*/




    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        muteSoundBtn.setText(R.string.MUTE_SOUND);
        super.onPostCreate(savedInstanceState);
        delayedHide(0);
    }

    private void toggle() {
        hide();
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }


    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void toogleSound(View view){
        if(quizzForFriendApp.isPlaying() && !quizzForFriendApp.isSoundMuted()){
            Toast.makeText(this, "IS PLAYING, SOUHLD MUTE", Toast.LENGTH_SHORT).show();
            quizzForFriendApp.muteSound();
            muteSoundBtn.setText(R.string.UNMUTE_SOUND);
        }else {
            Toast.makeText(this, "IS NOT PLAYING, SOUHLD UNMUTE", Toast.LENGTH_SHORT).show();
            quizzForFriendApp.unMuteSound();
            muteSoundBtn.setText(R.string.MUTE_SOUND);
        }
    }
}
