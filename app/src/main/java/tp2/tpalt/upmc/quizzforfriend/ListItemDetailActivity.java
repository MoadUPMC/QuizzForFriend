package tp2.tpalt.upmc.quizzforfriend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by macbookpro on 18/10/2016.
 */
public class ListItemDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_layout);

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        // Here we turn your string.xml in an array
        String[] myKeys = getResources().getStringArray(R.array.choix);

        TextView myTextView = (TextView) findViewById(R.id.un_choix);
        myTextView.setText(myKeys[position]);


    }

}
