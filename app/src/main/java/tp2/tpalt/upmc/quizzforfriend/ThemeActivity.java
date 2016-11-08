package tp2.tpalt.upmc.quizzforfriend;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Random;


/**
 * Created by macbookpro on 17/10/2016.
 */

public class ThemeActivity extends AppCompatActivity {

    //Les bouttons qui me redirige soit vers un layout_choix_theme, soit qui charge un theme au hasard
    private Button themeAleatoire;
    private Button themeChoisis;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 5;
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

        setContentView(R.layout.choisir_theme_layout);
        getSupportActionBar().hide();
        mContentView = findViewById(R.id.themeChoice);

        themeAleatoire = (Button)findViewById(R.id.themeAleatoire);
        themeChoisis   = (Button)findViewById(R.id.themeChoisis);
        QuizzForFriend.playLoadingActivity(getApplicationContext());
    }

    //On redirige en fonction du bouton sur lequel on appuie
    public void themeDirection(View v){
        QuizzForFriend.playSelectTheme(getApplicationContext());
        final View currentView = v;
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(ThemeActivity.this, R.style.MyAlertDialogStyle);
        //builderSingle.setIcon(R.drawable.);
        builderSingle.setTitle("Quel type de partie ?");
        //builderSingle.setTI

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                ThemeActivity.this,
                android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Contre la montre");
        arrayAdapter.add("Normal");

        builderSingle.setNegativeButton(
                "Annuler",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        QuizzForFriend.playSelectPartySound(getApplicationContext());
                    }
                });

        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QuizzForFriend.playSelectPartySound(getApplicationContext());
                        String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(ThemeActivity.this , R.style.MyAlertDialogStyle);
                        builderInner.setMessage(strName);
                        QuizzForFriend.gameMode = strName;
                        builderInner.setTitle("Vous avez choisis le mode :");
                        builderInner.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        dialog.dismiss();
                                        QuizzForFriend.playSelectPartySound(getApplicationContext());
                                        startActivity(intentForId(currentView));
                                    }
                                });
                        builderInner.show();
                    }
                });
        builderSingle.show();
    }

    private Intent intentForId(View v){
        Intent intent;
        if (v.getId() == R.id.themeChoisis){
            intent = new Intent(this, ListThemeActivity.class);}
        else {
            Random random = new Random();
            int position = random.nextInt(QuizzForFriend.quizes.length - 0);
            intent = new Intent();
            intent.setClass(this, ListItemDetailActivity.class);
            intent.putExtra("position", position);
        }
        return intent;
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
    public void toogleSound(View v){
        ImageButton muteSoundBtn = (ImageButton) findViewById(R.id.muteSound);
        QuizzForFriend.toogleThemeSound(muteSoundBtn);
        QuizzForFriend.playToggleSound(getBaseContext());
    }
}
