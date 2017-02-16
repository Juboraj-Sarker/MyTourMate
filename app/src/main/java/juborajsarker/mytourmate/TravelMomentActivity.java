package juborajsarker.mytourmate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class TravelMomentActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1;
    EditText editText;
    ProgressDialog mProgress;
    private Button mUploadBtn;
    private ImageView mImageView;
    private ImageView saveImageShow;
    private StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_moment);

        editText = (EditText) findViewById(R.id.travel_moment);

        mStorage = FirebaseStorage.getInstance().getReference();
        mProgress = new ProgressDialog(this);

        mUploadBtn = (Button) findViewById(R.id.uploadBtn);
        mImageView = (ImageView) findViewById(R.id.imageCapture);
        saveImageShow = (ImageView) findViewById(R.id.saveImageShow);
    }

    public void viewGalarry(View view) {

        startActivity(new Intent(TravelMomentActivity.this, MomentGalleryActivity.class));
    }



    public void takePhoto(View view) {


        Intent camera = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, 1);


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            saveImageShow.setImageBitmap(photo);
            Uri tempUri = getImageUri(getApplicationContext(), photo);
            File finalFile = new File(getRealPathFromURI(tempUri));
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    public void save_moment(View view) {

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.id.imageCapture);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();


        Intent intent = new Intent(TravelMomentActivity.this, MomentGalleryActivity.class);
        intent.putExtra("picture", byteArray);
        intent.putExtra("moment", editText.getText().toString());
        startActivity(intent);

    }



}

