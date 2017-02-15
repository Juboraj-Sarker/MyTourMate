package juborajsarker.mytourmate.service;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import juborajsarker.mytourmate.data.Channel;

/**
 * Created by dell on 1/22/2017.
 */

public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;

    private Exception error;

    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }


    public void refreshWeather(final String cityName) {
        this.location = cityName;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                //String  w = "select * from weather.forecast where woeid in (select woeid from geo.places where text=\"%s\") and u = 'C' limit 5 | sort(field=\"item.forecast.date\", descending=\"false\")";

                //String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places where text=\"%s\") and u = 'C' limit 5 | sort(field=\"item.forecast.date\", descending=\"false\")", cityName);

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'", cityName);
                String endpont = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpont);
                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);

                    }
                    return result.toString();

                } catch (Exception e) {
                    error = e;

                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if (s == null && error != null) {
                    callback.serviceFailure(error);
                    return;
                }

                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResult = data.optJSONObject("query");

                    int count = queryResult.optInt("count");
                    if (count == 0) {
                        callback.serviceFailure(new locationWeatherException("No weather information found for " + location));
                        return;
                    }
                    Channel channel = new Channel();
                    channel.populate(queryResult.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSuccess(channel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(location);
    }

    private class locationWeatherException extends Exception {
        public locationWeatherException(String locationExceptionMsg) {
            super(locationExceptionMsg);
        }
    }
}
