package tp2.tpalt.upmc.quizzforfriend;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by macbookpro on 18/10/2016.
 * Cette classe va nous servir a stocker le theme choisis dans notre application
 * Et aussi a cherche le Json adequat
 */
public class ListItemDetailActivity extends AppCompatActivity {

    QuizzForFriend quizzy;
    Partie porty;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_layout);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        // Here we turn your string.xml in an array
        String[] myKeys = getResources().getStringArray(R.array.choix);

        TextView myTextView = (TextView) findViewById(R.id.un_choix);

        //On recupere l appli pour y mettre le theme DANS LA PARTIE EN COURS
//        quizzy  = (QuizzForFriend)getApplication();
//        porty = new Partie();
//        porty.setTheme(myKeys[position]);
//        porty   = quizzy.get
        myTextView.setText(myKeys[position]);

      Toast.makeText(ListItemDetailActivity.this, "Mon JSon Parser "+loadJSONFromAsset("quiz_"+myKeys[position]), Toast.LENGTH_SHORT).show();
    }

    //Methode de contruction de Questions a partir du Json
    public String loadJSONFromAsset(String fileName) {
        String json = null;
        try {

            InputStream is = getAssets().open((new File("quizz_"+fileName).getAbsolutePath()));

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return ex.toString();
        }
        return json;

    }

}
