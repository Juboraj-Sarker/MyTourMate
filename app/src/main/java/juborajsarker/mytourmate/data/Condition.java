package juborajsarker.mytourmate.data;

import org.json.JSONObject;

/**
 * Created by dell on 1/22/2017.
 */

public class Condition implements JSONPopulator {
    private int code;
    private String date;
    private String discription;
    private String curentTemp;

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        date = data.optString("date");
        discription = data.optString("text");
        curentTemp = data.optString("temp");
    }

    public int getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getDiscription() {
        return discription;
    }

    public String getCurentTemp() {
        return curentTemp;
    }
}
