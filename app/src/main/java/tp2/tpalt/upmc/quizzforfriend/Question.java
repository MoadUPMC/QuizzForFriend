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

    }

    //public boolean bonneReponse(String tentative){}

    public String getQuestion() {return question;}

}
