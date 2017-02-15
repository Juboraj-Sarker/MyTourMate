package juborajsarker.mytourmate.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by dell on 1/25/2017.
 */

public class Forecast implements JSONArrayPopulator {
    ArrayList<ForcasetDetails> forcasetDetailsArrayList;
    private int code;
    private String date;
    private String day;
    private String text;
    private String high;
    private String low;
    private ForcasetDetails forcasetDetails;

    @Override
    public void populateArray(JSONArray data) {

        try {

            for (int i = 0; i < data.length(); i++) {
                JSONObject dteals = data.getJSONObject(i);
                code = dteals.getInt("code");
                text = dteals.optString("text");

                forcasetDetails = new ForcasetDetails(code, text);
                forcasetDetailsArrayList = new ArrayList<>();
                forcasetDetailsArrayList.add(forcasetDetails);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public ArrayList<ForcasetDetails> getForcasetDetailsArrayList() {
        return forcasetDetailsArrayList;

    }

    public int getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getText() {
        return text;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }


}