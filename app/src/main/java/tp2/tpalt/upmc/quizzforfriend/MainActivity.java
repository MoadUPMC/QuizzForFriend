package tp2.tpalt.upmc.quizzforfriend;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    protected QuizzForFriend quizzForFriendApp;

    private ImageButton muteSoundBtn;
    private AlertDialog alertDialog;


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
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_screen);
        mContentView = findViewById(R.id.partyChoice);
        getSupportActionBar().hide();
        QuizzForFriend.playLoadingActivity(getApplicationContext());

        //partieSolo  = (Button)findViewById(R.id.boutonSolo);
        //partieVersus = (Button)findViewById(R.id.boutonVersus);

        quizzForFriendApp = (QuizzForFriend) getApplication();
        quizzForFriendApp.playRessource(R.raw.intro_remix);

        muteSoundBtn = (ImageButton) findViewById(R.id.muteSound);

        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Pas tout de suite !");
        alertDialog.setMessage("La partie contre des joueurs sera disponnible dans la prochaine version");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

    }

    public void playDirection(View v){
        if (v.getId() != R.id.boutonSolo){
            alertDialog.show();
        }else {
            Intent itnt = intentForId(v);
            startActivity(itnt);
        }
    }

    private Intent intentForId(View v){
        if (v.getId() == R.id.boutonSolo){
            return new Intent (getApplicationContext(), ThemeActivity.class);}
        else {
            return new Intent (getApplicationContext(), ThemeActivity.class);
        }
    }

    public void toogleSound(View v){
        QuizzForFriend.toogleThemeSound(muteSoundBtn);
        QuizzForFriend.playToggleSound(getBaseContext());
    }

    public void playSelectPartySound(View v){
        QuizzForFriend.playSelectPartySound(getBaseContext());
        this.playDirection(v);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
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
