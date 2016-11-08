package tp2.tpalt.upmc.quizzforfriend;

import java.util.ArrayList;
import java.util.List;


public class Partie {

    private Questionnaire questions;
    private Joker joker;
    private String theme;
    public int nbReponse, nbBonneReponse, currentRp;
    private ArrayList<Question> questionList;
    public boolean finished =false;


    public Partie(Questionnaire q){
        currentRp = 0;
        nbReponse = 0;
        nbBonneReponse = 0;
        this.questions = q;
        //TODO
        joker = Joker.getInstance();
        theme = q.getTheme();
        questionList = q.getQuestionList();
    }

    public Questionnaire getQuestions() {
        return questions;
    }

    public Joker getJoker() {
        return joker;
    }

    public String getTheme() {return theme; }

    public void setTheme(String theme) {this.theme = theme; }

    public Question getNexQuestion(){
        Question q;
        if (currentRp < questionList.size()) {
            q = questionList.get(currentRp);
            nbReponse++;
        }else {
            finished = true;
            q = new Question();
        }
        currentRp++;
        return q;
    }

    public void reponse(String value){}

    public int getCurrentRp() {
        return currentRp;
    }

    public void setCurrentRp(int currentRp) {
        this.currentRp = currentRp;
    }

    public int getNbBonneReponse() {
        return nbBonneReponse;
    }

    public void setNbBonneReponse(int nbBonneReponse) {
        this.nbBonneReponse = nbBonneReponse;
    }

    public int getNbReponse() {
        return nbReponse;
    }

    public void setNbReponse(int nbReponse) {
        this.nbReponse = nbReponse;
    }

    public boolean bonneReponse(String resp) {
        if (currentRp >= questionList.size()) return false;
        Question q = questionList.get(currentRp-1);
        Boolean b = q.isBonneReponse(resp);
        if(b == true) nbBonneReponse++;
        return b;
    }

    public boolean isFinish() {
        return finished;
    }


}
