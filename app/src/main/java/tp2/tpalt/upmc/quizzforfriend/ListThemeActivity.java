package tp2.tpalt.upmc.quizzforfriend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by macbookpro on 17/10/2016.
 */

public class ListThemeActivity extends AppCompatActivity {

    private QuizzForFriend myApp;

    private ArrayAdapter<ThemeList> themeListArrayAdapter;
    private ListView listView;

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

    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_theme_layout);
        QuizzForFriend.crossFade(getApplicationContext());

        mContentView = findViewById(R.id.listdesTheme);

        //* *EDIT* *

        //Questionnaire questionnaire = new Questionnaire(getApplicationContext(),R.raw.quiz_aeronotiques);

        myApp = (QuizzForFriend) getApplication();

        themeListArrayAdapter = new ThemeListAdapter(this, myApp.getThemeList());


        listView = (ListView)findViewById(R.id.listTheme);
        listView.setAdapter(themeListArrayAdapter);
        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
            }
        });*/

    }
    /*@Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        //Intent intent = new Intent();
        //intent.setClass(this, ListItemDetailActivity.class);
        //intent.putExtra("position", position);
        // Or / And
        //intent.putExtra("id", id);
        //startActivity(intent);
    }*/

    public void playSelectPartySound(View v){
        QuizzForFriend.playSelectPartySound(getBaseContext());
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
