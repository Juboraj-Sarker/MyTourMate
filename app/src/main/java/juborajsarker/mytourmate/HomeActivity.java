package juborajsarker.mytourmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import juborajsarker.mytourmate.nearby.NearbyActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

        startActivity(new Intent(HomeActivity.this, WeatherActivity.class));
    }
}
