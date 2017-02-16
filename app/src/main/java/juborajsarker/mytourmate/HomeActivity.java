package juborajsarker.mytourmate;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import juborajsarker.mytourmate.nearby.NearbyActivity;
import juborajsarker.mytourmate.travel_event.TravelEventActivity;
import juborajsarker.mytourmate.travel_expence.TravelExpenceActivity;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
        Boolean isLogedIn = sharedPreferences.getBoolean("isLogedIn", false);
    }

    public void nearByPlaces(View view) {

        startActivity(new Intent(HomeActivity.this, NearbyActivity.class));
    }

    public void travelEvent(View view) {

        startActivity(new Intent(HomeActivity.this, TravelEventActivity.class));
    }

    public void travelExpence(View view) {
        startActivity(new Intent(HomeActivity.this, TravelExpenceActivity.class));

    }

    public void travelMoment(View view) {
        startActivity(new Intent(HomeActivity.this, TravelMomentActivity.class));

    }

    public void weatherUpdate(View view) {

        startActivity(new Intent(HomeActivity.this, WeatherMainActivity.class));
    }

    public void logout(View view) {

        sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
    }

    public void close(View view) {
        finish();
    }
}
