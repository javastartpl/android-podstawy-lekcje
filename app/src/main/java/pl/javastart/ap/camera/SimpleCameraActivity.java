package pl.javastart.ap.camera;

import pl.javastart.ap.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SimpleCameraActivity extends Activity {

	private static final int CAMERA_REQUEST_CODE = 1234;
	private Uri fileUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_camera);

		Button button = (Button) findViewById(R.id.b2);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				//File directory = new File(Environment.getExternalStorageDirectory(), "")
				
				
				
				
				startActivityForResult(intent, CAMERA_REQUEST_CODE);
			}
		});
	}

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == CAMERA_REQUEST_CODE) {
		if (resultCode == RESULT_OK) {
			Toast.makeText(getApplicationContext(), "Sukces", Toast.LENGTH_SHORT).show();
		} else if (resultCode == RESULT_CANCELED){
			Toast.makeText(getApplicationContext(), "Anulowano", Toast.LENGTH_SHORT).show();
		}
	}
}

}
