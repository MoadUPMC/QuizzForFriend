package tp2.tpalt.upmc.quizzforfriend;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by alexmaxime on 08/11/2016.
 */
public class DisplayResultActivity extends AppCompatActivity {
    protected QuizzForFriend quizzForFriendApp;
    private ImageButton muteSoundBtn;
    private Button trophyScore;
    Animation animationInfinitBounce, wobbleAnnimation;
    private ImageView readeaySteadyGo;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
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

        QuizzForFriend.crossFade(getApplicationContext(), R.raw.round_complete, true);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result_screen);
        mContentView = findViewById(R.id.partyChoice);
        getSupportActionBar().hide();
        QuizzForFriend.playLoadingActivity(getApplicationContext());

        //partieSolo  = (Button)findViewById(R.id.boutonSolo);
        //partieVersus = (Button)findViewById(R.id.boutonVersus);

        muteSoundBtn = (ImageButton) findViewById(R.id.muteSound);
        trophyScore = (Button) findViewById(R.id.trophyScore);

        readeaySteadyGo = (ImageView) findViewById(R.id.ImageScore);
        animationInfinitBounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.infinite_bounce);
        wobbleAnnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_t);

        animationInfinitBounce.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //Todo
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(animation == animationInfinitBounce){
                    readeaySteadyGo.startAnimation(wobbleAnnimation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO
            }
        });

        readeaySteadyGo.startAnimation(animationInfinitBounce);
    }

    public void toogleSound(View v){
        QuizzForFriend.toogleThemeSound(muteSoundBtn);
        QuizzForFriend.playToggleSound(getBaseContext());
    }

    public void backToHome(View v){
        QuizzForFriend.crossFade(getApplicationContext(),R.raw.quizmania__disco);
        Intent itnt = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(itnt);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int nbBonnerep = intent.getIntExtra("nbBonnerep", 0);
        int nbRep = intent.getIntExtra("nbRep", 0);
        trophyScore.setText("Votre Score est de : " + nbBonnerep + "/" + nbRep);
        //muteSoundBtn.setText(R.string.MUTE_SOUND);
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
        mHideHandler.postDelayed(mHidePart2Runnable, 0);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    protected void onRestart() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getSupportActionBar().hide();
        QuizzForFriend.playLoadingActivity(getBaseContext());
        super.onRestart();
    }

    @Override
    protected void onResume() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getSupportActionBar().hide();
        QuizzForFriend.playLoadingActivity(getBaseContext());
        super.onResume();
    }
}
