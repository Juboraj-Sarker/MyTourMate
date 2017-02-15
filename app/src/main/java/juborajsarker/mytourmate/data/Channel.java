package juborajsarker.mytourmate.data;

import org.json.JSONObject;

/**
 * Created by dell on 1/22/2017.
 */

public class Channel implements JSONPopulator {
    private Units units;
    private Item item;
    private Wind wind;
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    private Location location;
    private String lastBuildDate;


    @Override
    public void populate(JSONObject data) {
        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        location = new Location();
        location.populate(data.optJSONObject("location"));

        wind = new Wind();
        wind.populate(data.optJSONObject("wind"));

        atmosphere = new Atmosphere();
        atmosphere.populate(data.optJSONObject("atmosphere"));

        astronomy = new Astronomy();
        astronomy.populate(data.optJSONObject("astronomy"));


        lastBuildDate = data.optString("lastBuildDate");
    }

    public Wind getWind() {
        return wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }


    public Location getLocation() {
        return location;
    }

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }


}
