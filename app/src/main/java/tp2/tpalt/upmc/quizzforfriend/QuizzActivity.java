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

    private Partie partyUp;
    private List<Button> boutons;
    //Ma liste de boutons Ids
    private static final int[] BUTTON_IDS = {
            R.id.reponseA,
            R.id.reponseB,
            R.id.reponseC,
            R.id.reponseD
    };

    /** ETAPE 1
     * On recupere la valeur "theme" setter et gardee dans notre Application
     * Puis on appel le Json adequat en fonction de la veleur si dessous
     * Pour chaque objet question du Json creer une question et la sotcker dans "party"
     */

    /** ETAPE 2
     * Pour chaque question du partie, setter les questions et le score dynamiquement
     * A la fin de la partie, faire appel a un layout de fin de partie
     * Envoyer le score sur Firebase pour que l'autre utilisateur puisse faire la partie
     */

    /** ETAPE 3
     * A la connexion l'utilisateur doit avoir une vue layout pour comparer son score avec celui de son pote.
     */

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
