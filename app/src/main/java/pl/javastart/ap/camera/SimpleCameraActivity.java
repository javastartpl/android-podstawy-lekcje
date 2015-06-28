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

import java.io.File;
import java.io.IOException;

import pl.javastart.ap.R;

public class SimpleCameraActivity extends Activity {

    private static final int CAMERA_REQUEST_CODE = 1234;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_camera);
    }

    public void makePhotoButtonPressed(View view) {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileUri = Uri.fromFile(tempFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Sukces", Toast.LENGTH_SHORT).show();

                ImageView photo = (ImageView) findViewById(R.id.photo);


//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                BitmapFactory.decodeFile(data.getData().getPath(), options);
//
//                int size = calculateInSampleSize(options, 600, 600);
//
//                options.inJustDecodeBounds = false;
//                Bitmap bitmap = BitmapFactory.decodeFile(data.getData().getPath(), options);

                Bundle extras = data.getExtras();
                Bitmap mImageBitmap = (Bitmap) extras.get("data");

                photo.setImageBitmap(mImageBitmap);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Anulowano", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
