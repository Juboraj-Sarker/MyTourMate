package juborajsarker.mytourmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    private EditText emailEditText;
    private EditText passwordEditText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailEditText = (EditText) findViewById(R.id.userEmailForLoginEt);
        passwordEditText = (EditText) findViewById(R.id.userPasswordForLoginEt);
        firebaseAuth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences("loginStatus", MODE_PRIVATE);
        Boolean isLogedIn = sharedPreferences.getBoolean("isLogedIn", false);
    }


    public void goToRegiserPage(View view) {

        startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
    }

    public void signIn(View view) {

     final ProgressDialog progressDialog = ProgressDialog.show(SignInActivity.this, "Please wait....", "Processing....", true);
        (firebaseAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                      progressDialog.dismiss();

                if (task.isSuccessful()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLogedIn", true);
                    editor.commit();

                    Toast.makeText(SignInActivity.this, "Login Success...", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignInActivity.this, HomeActivity.class));

                }else {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLogedIn", false);
                    editor.commit();

                    Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
