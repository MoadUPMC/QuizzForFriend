package tp2.tpalt.upmc.quizzforfriend;



import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexmaxime on 18/10/2016.
 */

public class Questionnaire {

    private ArrayList<Question> questionList;
    private InputStream jsonPath;
    private Context context;

    private String jsonContent;
    private JSONObject jsonObject;

    private String theme;
    private String description;

    public Questionnaire(Context appContext, int quizName) {
        context = appContext;
        try {
            loadFromRessource(quizName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.jsonContentToJsonObject();
    }

    public Questionnaire(InputStream jsonPath) {
        this.jsonPath = jsonPath;
    }

    public Questionnaire(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    public Questionnaire(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public InputStream getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(InputStream jsonPath) {
        this.jsonPath = jsonPath;
    }

    public void loadFromRessource(int resource) throws Exception {
        if (context == null) throw new Exception("You must initialize a context with setContext Method");
        try {
            jsonPath = context.getResources().openRawResource(resource);

            InputStream is = jsonPath;

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            jsonContent = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
    }

    public void jsonContentToJsonObject(String jsonString){
        if (jsonString != null) jsonContent = jsonString;
        this.jsonContentToJsonObject();
    }

    public void jsonContentToJsonObject(){
        try {
            jsonObject = new JSONObject(jsonContent);
            JSONArray questionsArray = jsonObject.getJSONArray("questions");

            HashMap<String, String> m_li;

            for (int i = 0; i < questionsArray.length(); i++) {

                JSONObject questionObj = questionsArray.getJSONObject(i);
                String qts = questionObj.get("name").toString();
                JSONArray answrs = questionObj.getJSONArray("answers");

                ArrayList<Map<String, String>> thoseResponse = new ArrayList<>();
                for (int j = 0; j < answrs.length(); j++) {
                    Map<String, String> response = new HashMap<String, String>();
                    response.put(answrs.getJSONObject(j).getString("name"), answrs.getJSONObject(j).getString("weight"));
                    thoseResponse.add(response);
                }
                Question theQuestion = new Question();
                theQuestion.setQuestion(qts);
                theQuestion.setResponses(thoseResponse);
                theQuestion.setResponsesJsonArray(answrs);
                theQuestion.setQuestionWithResponsesJson(questionObj);
                questionList.add(theQuestion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }
}
