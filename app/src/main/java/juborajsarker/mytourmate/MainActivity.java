package juborajsarker.mytourmate;

import android.content.Intent;


import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
        Boolean isLogedIn = sharedPreferences.getBoolean("isLogedIn", false);

        if (isLogedIn) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void signInActivity(View view) {

        startActivity(new Intent(MainActivity.this, SignInActivity.class));

    }

    public void signUpActivity(View view) {

        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }
}
