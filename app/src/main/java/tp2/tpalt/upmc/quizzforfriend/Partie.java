package tp2.tpalt.upmc.quizzforfriend;

import java.util.List;

/**
 * Created by macbookpro on 10/10/2016.
 */

public class Partie {

    private List<Question> questions;
    private Joker joke;
    private String theme;

    public Partie(List<Question> q){
        this.questions = q;
        joke = Joker.getInstance();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Joker getJoke() {
        return joke;
    }

    public String getTheme() {return theme; }

    public void setTheme(String theme) {this.theme = theme; }
}
