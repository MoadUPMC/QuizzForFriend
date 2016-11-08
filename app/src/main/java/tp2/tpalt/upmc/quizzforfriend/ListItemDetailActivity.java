package tp2.tpalt.upmc.quizzforfriend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class ListItemDetailActivity extends AppCompatActivity{

    //QuizzForFriend quizzy;
    Partie party;

    private ImageButton muteSoundBtn;
    private LinearLayout readeaySteadyGo;
    private RelativeLayout answlay;
    private TextView questionOverView, tfgans;
    private Button response1OverView,response2OverView,response3OverView,response4OverView;
    Animation animationSlideUp;
    TextView decompte, theme;
    Question q;


    // Toast...
    Toast toast;


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.party_layout);
        mContentView = findViewById(R.id.prtlyt);

        muteSoundBtn = (ImageButton) findViewById(R.id.muteSound);
        readeaySteadyGo = (LinearLayout) findViewById(R.id.readeaySteadyGo);
        readeaySteadyGo.setVisibility(View.VISIBLE);
        decompte = (TextView)  findViewById(R.id.decompte);
        theme = (TextView)  findViewById(R.id.theme);


        answlay = (RelativeLayout) findViewById(R.id.answlay);
        tfgans = (TextView) findViewById(R.id.tfgans);

        questionOverView = (TextView) findViewById(R.id.questionOverView);

        response1OverView = (Button) findViewById(R.id.response1OverView);
        response2OverView = (Button) findViewById(R.id.response2OverView);
        response3OverView = (Button) findViewById(R.id.response3OverView);
        response4OverView = (Button) findViewById(R.id.response4OverView);



        // Toast...
        toast = new Toast(getApplicationContext());

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        QuizzForFriend.crossFade(getApplicationContext(), R.raw.countdown60s_wav_by_nsstudios_320771_2776777_lq, true);

        Questionnaire questionnaire = new Questionnaire(getApplicationContext(), QuizzForFriend.quizes[position]);

        party = new Partie(questionnaire);

        theme.setText("Theme : " + questionnaire.getTheme() + "\n" + questionnaire.getDescription());

    }


    public void toogleSound(View v){
        QuizzForFriend.toogleThemeSound(muteSoundBtn);
        QuizzForFriend.playToggleSound(getBaseContext());
    }

    public void playSelectPartySound(View v){
        QuizzForFriend.playSelectPartySound(getBaseContext());
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        animationSlideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationSlideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //Todo
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(animation == animationSlideUp){
                    readeaySteadyGo.setVisibility(View.INVISIBLE);
                    questionOverView.setVisibility(View.VISIBLE);
                    response1OverView.setVisibility(View.VISIBLE);
                    response2OverView.setVisibility(View.VISIBLE);
                    response3OverView.setVisibility(View.VISIBLE);
                    response4OverView.setVisibility(View.VISIBLE);
                    answlay.setVisibility(View.VISIBLE);
                    tfgans.setVisibility(View.VISIBLE);
                    showQuestion();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO
            }
        });
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                decompte.setText("On commence dans "+millisUntilFinished / 1000+" Secondes :)");
            }

            public void onFinish() {
                decompte.setText("GO !");
                readeaySteadyGo.startAnimation(animationSlideUp);

            }
        }.start();
        super.onPostCreate(savedInstanceState);
        delayedHide(0);
/*
        Intent myIntent = new Intent(getBaseContext(), ListItemDetailActivity.class);
        startActivityForResult(myIntent, 0);*/
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



    @Override
    public void onBackPressed(){
        moveTaskToBack(false);
    }

    public void makeAchoice(View v){
        if(party.isFinish()) {
            Intent itnt = new Intent (getApplicationContext(), DisplayResultActivity.class);
            itnt.putExtra("nbBonnerep", party.getNbBonneReponse());
            itnt.putExtra("nbRep", party.getNbReponse());
            startActivity(itnt);
            return;
        }else {
            LayoutInflater inflater = getLayoutInflater();
            View layout;
            Button b = (Button)v;
            String resp = b.getText().toString();
            if (party.bonneReponse(resp)){
                QuizzForFriend.playGoodAnswerSound(getApplicationContext());
                layout = inflater.inflate(R.layout.bonneresponse_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_layout_id));
            }else {
                QuizzForFriend.playBadAnswer(getApplicationContext());
                layout = inflater.inflate(R.layout.badresponse_toast,
                        (ViewGroup) findViewById(R.id.custom_toast_layout_id2));
            }
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
            showQuestion();
        }

    }

    public void showQuestion(){
        q = party.getNexQuestion();
        questionOverView.setText(q.getQuestion());
        JSONArray responses = q.getResponsesJsonArray();
        if(responses == null){
            Intent itnt = new Intent (getApplicationContext(), DisplayResultActivity.class);
            itnt.putExtra("nbBonnerep", party.getNbBonneReponse());
            itnt.putExtra("nbRep", party.getNbReponse());
            startActivity(itnt);
            return;
        }
        for (int u = 0; u < responses.length(); u++) {
            switch(u) {
                case 0 :
                    try {
                        response1OverView.setText(responses.getJSONObject(0).get("name").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break; // optional

                case 1 :
                    try {
                        response2OverView.setText(responses.getJSONObject(1).get("name").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break; // optional

                case 2 :
                    try {
                        response3OverView.setText(responses.getJSONObject(2).get("name").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break; // optional

                case 3 :
                    try {
                        response4OverView.setText(responses.getJSONObject(3).get("name").toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break; // optional
            }
        }
    }
}
