package tp2.tpalt.upmc.quizzforfriend;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Question {

    private String question;

    private JSONObject questionWithResponsesJson;
    private JSONObject responsesJson;

    Map<String, String> responses = new HashMap<String, String>();





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
