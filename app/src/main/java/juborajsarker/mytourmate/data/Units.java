package juborajsarker.mytourmate.data;

import org.json.JSONObject;

/**
 * Created by dell on 1/22/2017.
 */

public class Units implements JSONPopulator {
    private String temperature;

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }

    public String getTemperature() {
        return temperature;
    }
}
