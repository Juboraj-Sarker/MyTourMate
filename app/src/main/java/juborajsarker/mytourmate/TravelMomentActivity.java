package juborajsarker.mytourmate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TravelMomentActivity extends AppCompatActivity {

    private Button mUploadBtn;
    private ImageView mImageView;
    private static final int CAMERA_REQUEST_CODE = 1;
    private StorageReference mStorage;
    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_moment);

        mStorage = FirebaseStorage.getInstance().getReference();
        mProgress = new ProgressDialog(this);

        mUploadBtn = (Button) findViewById(R.id.uploadBtn);
        mImageView = (ImageView) findViewById(R.id.imageCapture);
    }

    public void viewGalarry(View view) {

        startActivity(new Intent(TravelMomentActivity.this, MomentGalleryActivity.class));
    }

    public void save_moment(View view) {

    }

    public void takePhoto(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            mProgress.setMessage("Uploading...");
            mProgress.show();

            Uri uri = data.getData();
            StorageReference filePath = mStorage.child("photos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mProgress.dismiss();
                    Toast.makeText(TravelMomentActivity.this, "Uploading finished", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {

                public void onFailure(@NonNull Exception exception) {

                    Toast.makeText(TravelMomentActivity.this, "Somethig went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
