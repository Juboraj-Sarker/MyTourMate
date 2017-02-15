package juborajsarker.mytourmate;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherDetail extends AppCompatActivity {
    private int code;
    private TextView city;
    private TextView temp;
    private TextView date;
    private TextView chill;
    private TextView direction;
    private TextView speed;
    private TextView humidity;
    private TextView pressure;
    private TextView rising;
    private TextView visibility;
    private TextView sunrise;
    private TextView sunset;
    private ImageView forecastWeatherImage;
    private LinearLayout linearLayout;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle weatherIntent = getIntent().getExtras();

        city = (TextView) findViewById(R.id.city);
        temp = (TextView) findViewById(R.id.temp);
        date = (TextView) findViewById(R.id.date);
        chill = (TextView) findViewById(R.id.chill);
        direction = (TextView) findViewById(R.id.direction);
        speed = (TextView) findViewById(R.id.speed);
        humidity = (TextView) findViewById(R.id.humidity);
        pressure = (TextView) findViewById(R.id.pressure);
        rising = (TextView) findViewById(R.id.rising);
        visibility = (TextView) findViewById(R.id.visibility);
        sunrise = (TextView) findViewById(R.id.sunrise);
        sunset = (TextView) findViewById(R.id.sunset);
        forecastWeatherImage = (ImageView) findViewById(R.id.forecastWeatherImage);
        linearLayout = (LinearLayout) findViewById(R.id.weatherDetailsBg);

        if (weatherIntent != null) {
            code = weatherIntent.getInt("code");
            int resourceId = getResources().getIdentifier("drawable/icon_" + code, null, getPackageName());
            @SuppressWarnings("deprecation")
            Drawable weatherIconDrawables = getResources().getDrawable(resourceId);
            forecastWeatherImage.setImageDrawable(weatherIconDrawables);

            int resourceBGId = getResources().getIdentifier("drawable/bg_" + code, null, getPackageName());
            @SuppressWarnings("deprecation")
            Drawable weatherBGDrawable = getResources().getDrawable(resourceBGId);
            linearLayout.setBackground(weatherBGDrawable);


            city.setText("Location: " + weatherIntent.getString("city"));
            temp.setText("temp: " + weatherIntent.getString("temp") + "\u00b0");
            date.setText("date: " + weatherIntent.getString("date"));
            chill.setText("chill: " + weatherIntent.getString("chill"));
            direction.setText("direction: " + weatherIntent.getString("direction"));
            speed.setText("speed: " + weatherIntent.getString("speed"));
            humidity.setText("humidity: " + weatherIntent.getString("humidity"));
            pressure.setText("pressure: " + weatherIntent.getString("pressure"));
            rising.setText("rising: " + weatherIntent.getString("rising"));
            visibility.setText("visibility: " + weatherIntent.getString("visibility"));
            sunrise.setText("sunrise: " + weatherIntent.getString("sunrise"));
            sunset.setText("sunset: " + weatherIntent.getString("sunset"));

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
