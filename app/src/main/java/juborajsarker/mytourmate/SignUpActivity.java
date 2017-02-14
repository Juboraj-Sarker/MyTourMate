package juborajsarker.mytourmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailET;
    private EditText passwordET;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void register_new_user(View view) {

        if (emailET.getText().toString().isEmpty() || passwordET.getText().toString().isEmpty()){
            Toast.makeText(this, "All field must require", Toast.LENGTH_SHORT).show();

        }else {

            final ProgressDialog progressDialog = ProgressDialog.show(SignUpActivity.this, "Please wait....", "Processing...", true);
            (firebaseAuth.createUserWithEmailAndPassword(emailET.getText().toString(), passwordET.getText().toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.dismiss();

                            if (task.isSuccessful()){

                                Toast.makeText(SignUpActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                            }else {

                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }


    }
}
