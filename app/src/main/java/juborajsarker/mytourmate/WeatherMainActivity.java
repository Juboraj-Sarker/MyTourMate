package juborajsarker.mytourmate;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import juborajsarker.mytourmate.data.Channel;
import juborajsarker.mytourmate.data.Forecast;
import juborajsarker.mytourmate.data.Item;
import juborajsarker.mytourmate.service.WeatherServiceCallback;
import juborajsarker.mytourmate.service.YahooWeatherService;
import juborajsarker.mytourmate.weatheractivity.GetCurrentDateTime;

public class WeatherMainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private static final String SHAREDPREFERENCE_FILE_NAME = "weather_last_update_info";
    Channel channelObj;
    private TextView cityNameTV;
    private ImageView weatherConidtionImageTV;
    private TextView currentWConditionTV;
    private TextView currentWTempTV;
    private TextView currentWLocationTV;
    private RelativeLayout weatherBackgroundLayout;
    private YahooWeatherService service;
    private ProgressDialog dialog;
    private ImageView addCityButton;
    private EditText locationInputET;
    private TextView showTimeTV;
    private TextView showAmPmTV;
    private TextView showDateTV;
    private TextView lastUpdateDate;
    private GetCurrentDateTime getDateTIme;
    private TextView forecastViewTV;
    private TextView weatherDetails;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        cityNameTV = (TextView) findViewById(R.id.cityNameTV);
        weatherConidtionImageTV = (ImageView) findViewById(R.id.weatherConidtionImageView);
        currentWConditionTV = (TextView) findViewById(R.id.currentWConditionTV);
        currentWTempTV = (TextView) findViewById(R.id.currentWTempTV);
        currentWLocationTV = (TextView) findViewById(R.id.currentWLocationTV);
        forecastViewTV = (TextView) findViewById(R.id.forecastViewTV);
        weatherDetails = (TextView) findViewById(R.id.weatherDetails);
        addCityButton = (ImageView) findViewById(R.id.addCityButton);
        weatherBackgroundLayout = (RelativeLayout) findViewById(R.id.weatherBackgroundLayout);


        showTimeTV = (TextView) findViewById(R.id.showTimeTV);
        showAmPmTV = (TextView) findViewById(R.id.showAmPmTV);
        showDateTV = (TextView) findViewById(R.id.showDateTV);
        lastUpdateDate = (TextView) findViewById(R.id.lastUpdateDate);


        service = new YahooWeatherService(this);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading, please wait...");
        dialog.show();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Type Your Location Below");
        dialog.setIcon(R.drawable.flag_bd_map);
        dialog.setMessage("Please Input your Location to see the Weather Information");

        locationInputET = new EditText(this);
        locationInputET.setHint("Dhaka, BD");
        dialog.setView(locationInputET);
        service.refreshWeather("Dhaka, BD");

        dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String inputCity = locationInputET.getText().toString();
                if (TextUtils.isEmpty(inputCity)) {
                    locationInputET.setError("Can not Leave Blank");
                    return;
                } else if (!inputCity.matches("") && isDeviceIsConnectedWithInternet()) {

                    currentWLocationTV.setText(inputCity);
                    cityNameTV.setText(inputCity);
                    service.refreshWeather(inputCity);

                }

            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialogForInputLocation = dialog.create();


        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogForInputLocation.show();
            }
        });

        this.getSystemDateTime("dd-MM-yyyy hh:mm a", "GMT+6");

    }

    public void getSystemDateTime(String dateFormat, String setTimeZone) {

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone(setTimeZone));
        String currentDateTimeString = sdf.format(d);
        String[] value = currentDateTimeString.split(" ", 0);
        String cdate = value[0];
        String[] dateValue = cdate.split("-", 0);
        String day = dateValue[0];
        String month = dateValue[1];
        String year = dateValue[2];

        String[] time = value[1].split(":", 0);
        String hour = time[0];
        String min = time[1];
        String ampm = value[2];
        getDateTIme = new GetCurrentDateTime(hour, min, ampm, year, day, month);

        showTimeTV.setText(getDateTIme.getHour() + ":" + getDateTIme.getMin());
        showAmPmTV.setText(getDateTIme.getAmpm());
        showDateTV.setText(getDateTIme.getDay() + "-" + getDateTIme.getMonth() + "-" + getDateTIme.getYear());

    }

    @SuppressLint("NewApi")
    @Override
    public void serviceSuccess(Channel channel) {
        channelObj = channel;
        dialog.hide();

        Item item = channel.getItem();
        Forecast forecast = channel.getItem().getForecast();

        Forecast forcas = new Forecast();


        if (this.isDeviceIsConnectedWithInternet()) {

            SharedPreferences.Editor editor = getSharedPreferences(SHAREDPREFERENCE_FILE_NAME, MODE_PRIVATE).edit();
            editor.putString("city", channel.getLocation().getCity());
            editor.putString("country", channel.getLocation().getCountry());
            editor.putString("lastBuildDate", channel.getLastBuildDate());
            editor.putString("units", channel.getUnits().getTemperature());
            editor.putString("condition", channel.getItem().getCondition().getDiscription());
            editor.putString("temp", channel.getItem().getCondition().getCurentTemp());
            editor.putInt("id", channel.getItem().getCondition().getCode());
            editor.commit();

            // forecastViewTV.setText("Forecast");
            weatherDetails.setText("Details");
            cityNameTV.setText(service.getLocation());
            currentWConditionTV.setText(item.getCondition().getDiscription());
            currentWLocationTV.setText(service.getLocation());
            currentWTempTV.setText(item.getCondition().getCurentTemp() + "\u00b0" + channel.getUnits().getTemperature());
            lastUpdateDate.setText(channel.getLastBuildDate());

            int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());
            @SuppressWarnings("deprecation")
            Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
            weatherConidtionImageTV.setImageDrawable(weatherIconDrawable);

            int resourceBGId = getResources().getIdentifier("drawable/bg_" + item.getCondition().getCode(), null, getPackageName());
            @SuppressWarnings("deprecation")
            Drawable weatherBGDrawable = getResources().getDrawable(resourceBGId);
            weatherBackgroundLayout.setBackground(weatherBGDrawable);


        } else {
            SharedPreferences prefs = getSharedPreferences(SHAREDPREFERENCE_FILE_NAME, MODE_PRIVATE);
            String scity = prefs.getString("city", "");
            String scountry = prefs.getString("country", "");
            String slastBuildDate = prefs.getString("lastBuildDate", "");
            String sunits = prefs.getString("units", "");
            String scondition = prefs.getString("condition", "");
            String stemp = prefs.getString("temp", "");
            int sid = prefs.getInt("id", 44);

            int resourceId = getResources().getIdentifier("drawable/icon_" + sid, null, getPackageName());
            @SuppressWarnings("deprecation")
            Drawable weatherIconDrawable = getResources().getDrawable(resourceId);
            weatherConidtionImageTV.setImageDrawable(weatherIconDrawable);

            int resourceBGId = getResources().getIdentifier("drawable/bg_" + sid, null, getPackageName());
            @SuppressWarnings("deprecation")
            Drawable weatherBGDrawable = getResources().getDrawable(resourceBGId);
            weatherBackgroundLayout.setBackground(weatherBGDrawable);

            cityNameTV.setText(scountry);
            currentWConditionTV.setText(scondition);
            currentWLocationTV.setText(scity);
            currentWTempTV.setText(stemp + "\u00b0" + sunits);
            lastUpdateDate.setText(slastBuildDate);
        }


    }

    public boolean isDeviceIsConnectedWithInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;

    }

    public boolean isInternetAvailable() {

        boolean success = false;
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            success = connection.getResponseCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;


    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public void goToForecast(View view) {
//        Intent intent = new Intent(WeatherMainActivity.this, ForecastActivity.class);
//        intent.putExtra("location", "Dhaka, BD");
//        startActivity(intent);
    }

    public void goToDetails(View view) {

        Intent intentDetails = new Intent(WeatherMainActivity.this, WeatherDetail.class);
        intentDetails.putExtra("city", service.getLocation());
        intentDetails.putExtra("temp", channelObj.getItem().getCondition().getCurentTemp());
        intentDetails.putExtra("code", channelObj.getItem().getCondition().getCode());
        intentDetails.putExtra("date", channelObj.getLastBuildDate());
        intentDetails.putExtra("chill", channelObj.getWind().getChill());
        intentDetails.putExtra("direction", channelObj.getWind().getDirection());
        intentDetails.putExtra("speed", channelObj.getWind().getSpeed());
        intentDetails.putExtra("humidity", channelObj.getAtmosphere().getHumidity());
        intentDetails.putExtra("pressure", channelObj.getAtmosphere().getPressure());
        intentDetails.putExtra("rising", channelObj.getAtmosphere().getRising());
        intentDetails.putExtra("visibility", channelObj.getAtmosphere().getVisibility());
        intentDetails.putExtra("sunrise", channelObj.getAstronomy().getSunrise());
        intentDetails.putExtra("sunset", channelObj.getAstronomy().getSunset());

        startActivity(intentDetails);

    }
}
