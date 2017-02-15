package juborajsarker.mytourmate.data;

import org.json.JSONObject;

/**
 * Created by dell on 1/23/2017.
 */

public class Location implements JSONPopulator {
    private String city;
    private String country;

    @Override
    public void populate(JSONObject data) {
        city = data.optString("location");
        country = data.optString("country");
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
