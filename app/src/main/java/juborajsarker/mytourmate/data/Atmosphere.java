package juborajsarker.mytourmate.data;

import org.json.JSONObject;

/**
 * Created by dell on 1/25/2017.
 */

public class Atmosphere implements JSONPopulator {
    private String humidity;
    private String pressure;
    private String rising;
    private String visibility;

    @Override
    public void populate(JSONObject data) {

        humidity = data.optString("humidity");
        pressure = data.optString("pressure");
        rising = data.optString("rising");
        visibility = data.optString("visibility");

    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getRising() {
        return rising;
    }

    public String getVisibility() {
        return visibility;
    }

}
