package pl.javastart.ap.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.UUID;

import pl.javastart.ap.R;

public class GlideCameraActivity extends Activity {

    private static final int CAMERA_REQUEST_CODE = 1234;
    private Uri fileUri;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_camera);
    }

    public void makePhotoButtonPressed(View view) {
        String randomName = UUID.randomUUID().toString();

        File storageDir = new File(getExternalFilesDir(null), "wydatex_photos");
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        file = new File(storageDir, randomName);
        fileUri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Sukces", Toast.LENGTH_SHORT).show();

                ImageView photo = (ImageView) findViewById(R.id.photo);
                Glide.with(this).load(file.getAbsoluteFile()).asBitmap().into(photo);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Anulowano", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
