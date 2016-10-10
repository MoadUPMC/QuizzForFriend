package tp2.tpalt.upmc.quizzforfriend;

import android.app.Application;

import java.util.List;

/**
 * Created by macbookpro on 10/10/2016.
 */
public class QuizzForFriend extends Application {

    private List<Partie> parties;

    //Ne jamais utiliser le contructeur par default
    public QuizzForFriend(){}

    public QuizzForFriend(List<Partie> p){
        this.parties = p;
    }

    public List<Partie> getParties() {
        return parties;
    }
}
