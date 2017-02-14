package juborajsarker.mytourmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TravelMomentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_moment);
    }

    public void viewGalarry(View view) {

        startActivity(new Intent(TravelMomentActivity.this, MomentGalleryActivity.class));
    }

    public void save_moment(View view) {

    }
}
