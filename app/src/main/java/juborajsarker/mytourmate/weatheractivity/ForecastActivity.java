package juborajsarker.mytourmate.weatheractivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import juborajsarker.mytourmate.R;
import juborajsarker.mytourmate.data.Channel;
import juborajsarker.mytourmate.data.ForcasetDetails;
import juborajsarker.mytourmate.data.Forecast;
import juborajsarker.mytourmate.service.WeatherServiceCallback;
import juborajsarker.mytourmate.service.YahooWeatherService;

public class ForecastActivity extends AppCompatActivity implements WeatherServiceCallback {
    final String LOG = this.getClass().getName();
    ArrayList<ForcasetDetails> data;
    private YahooWeatherService service;
    private TextView code;
    private TextView date;
    private TextView day;
    private TextView high;
    private TextView low;
    private TextView text;
    private ImageView weatherImage;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        date = (TextView) findViewById(R.id.forecastDate);
        day = (TextView) findViewById(R.id.forecastDay);
        high = (TextView) findViewById(R.id.forecastHigh);
        low = (TextView) findViewById(R.id.forecastLow);
        text = (TextView) findViewById(R.id.forecastText);
        weatherImage = (ImageView) findViewById(R.id.forecastWeatherImage);

        service = new YahooWeatherService(this);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                location = "";
                // service.refreshWeather(location);
            } else {
                location = extras.getString("location");
                service.refreshWeather(location);

            }
        } else {
            location = (String) savedInstanceState.getSerializable("location");
        }

    }

    @Override
    public void serviceSuccess(Channel channel) {

        data = new ArrayList<>();

        Forecast forecast = new Forecast();


        Toast.makeText(this, "data empty", Toast.LENGTH_SHORT).show();


        date.setText(String.valueOf(forecast.getCode()));
        day.setText(String.valueOf(forecast.getText()));


    }

    @Override
    public void serviceFailure(Exception exception) {
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
