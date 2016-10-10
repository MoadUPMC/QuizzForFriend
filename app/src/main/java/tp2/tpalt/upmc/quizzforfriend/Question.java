package tp2.tpalt.upmc.quizzforfriend;

/**
 * Created by macbookpro on 10/10/2016.
 */

public class Question {

    private String question; //-> La question
    private String reponseA; // Les
    private String reponseB; // Reponses
    private String reponseC; // Possibles
    private String reponseD; // Dont une, parmis elles, est bonne

    private String reponse; // La bonne reponse (qui est lq même qu'une de celles ci dessus)

    public Question(String question, String reponse,
                    String reponseA, String reponseB, String reponseC, String reponseD){
        this.question   = question;
        this.reponse    = reponse;
        this.reponseA   = reponseA;
        this.reponseB   = reponseB;
        this.reponseC   = reponseC;
        this.reponseD   = reponseD;
    }

    public boolean bonneReponse(String tentative){
        if (tentative.equals(this.reponse)) return true;
        else return false;
    }

    public String getQuestion() {return question;}

    public String getReponse()  {return reponse;}

    public String getReponseA() {return reponseA;}

    public String getReponseB() {return reponseB;}

    public String getReponseC() {return reponseC;}

    public String getReponseD() {return reponseD;}
}
