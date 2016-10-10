package tp2.tpalt.upmc.quizzforfriend;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 10/10/2016.
 */
public class QuizzActivity extends AppCompatActivity {

    private TextView question;
    private List<Button> boutons;
    //Ma liste de boutons Ids
    private static final int[] BUTTON_IDS = {
            R.id.reponseA,
            R.id.reponseB,
            R.id.reponseC,
            R.id.reponseD
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz_layout);

        boutons = new ArrayList<Button>();
        for (int id : BUTTON_IDS){
            Button b = (Button) findViewById(id);
        }

    }
}
