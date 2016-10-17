package tp2.tpalt.upmc.quizzforfriend;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by macbookpro on 17/10/2016.
 */

public class ThemeActivity extends AppCompatActivity {

    //Les bouttons qui me redirige soit vers un layout_choix_theme, soit qui charge un theme au hasard
    private Button themeAleatoire;
    private Button themeChoisis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choisir_theme_layout);

        themeAleatoire = (Button)findViewById(R.id.themeAleatoire);
        themeChoisis   = (Button)findViewById(R.id.themeChoisis);

    }

    //On redirige en fonction du bouton sur lequel on appuie
    public void themeDirection(View v){
        startActivity(intentForId(v));
    }

    private Intent intentForId(View v){
        if (v.getId() == R.id.themeChoisis){
            return new Intent(this, ListThemeActivity.class);}
        else {
            //On passe un theme a l'application au hasard ici
            return new Intent (this, QuizzActivity.class);
        }
    }

}
