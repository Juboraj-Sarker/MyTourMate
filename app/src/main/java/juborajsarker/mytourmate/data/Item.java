package juborajsarker.mytourmate.data;

import org.json.JSONObject;

/**
 * Created by dell on 1/22/2017.
 */

public class Item implements JSONPopulator {
    private Condition condition;
    private Forecast forecast;

    public Condition getCondition() {
        return condition;
    }

    public Forecast getForecast() {
        return forecast;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

        forecast = new Forecast();
        forecast.populateArray(data.optJSONArray("forecast"));
    }


}
