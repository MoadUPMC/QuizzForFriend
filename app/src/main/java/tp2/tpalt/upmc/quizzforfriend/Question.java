package tp2.tpalt.upmc.quizzforfriend;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Question {

    private String question;

    private JSONObject questionWithResponsesJson;
    private JSONArray responsesJsonArray;

    ArrayList<Map<String, String>> responses = new ArrayList<Map<String, String>>();

    private int tentatives;

    public Question() {
        setTentatives(0);
    }

    public Question(String question) {
        setQuestion(question);
        setTentatives(0);
    }

    public Question(JSONObject questionWithResponsesJson) {
        setQuestionWithResponsesJson(questionWithResponsesJson);
        setTentatives(0);
    }

    public Question(String question, JSONArray responsesJsonArray) {
        this.question = question;
        this.responsesJsonArray = responsesJsonArray;
        setTentatives(0);
    }

    public Question(String question, ArrayList<Map<String, String>> responses) {
        this.question = question;
        this.responses = responses;
        setTentatives(0);
    }

    // GETTERS

    public JSONObject getQuestionWithResponsesJson() {
        return questionWithResponsesJson;
    }


    public JSONArray getResponsesJsonArray() {
        return responsesJsonArray;
    }

    public ArrayList<Map<String, String>> getResponses() {
        return responses;
    }


    public int getTentatives() {
        return tentatives;
    }


    public String getQuestion() {
        return question;
    }


    //SETTERS

    private void setTentatives(int tentatives) {
        this.tentatives = tentatives;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setResponses(ArrayList<Map<String, String>> responses) {
        this.responses = responses;
    }


    public void setResponsesJsonArray(JSONArray responsesJsonArray) {
        this.responsesJsonArray = responsesJsonArray;
    }


    public void setQuestionWithResponsesJson(JSONObject _questionWithResponsesJson) {
        this.questionWithResponsesJson = _questionWithResponsesJson;
        try {
            setResponsesJsonArray(questionWithResponsesJson.getJSONArray("answers"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public boolean isBonneReponse(String response){
        //TODO
        return true;
    }

}
