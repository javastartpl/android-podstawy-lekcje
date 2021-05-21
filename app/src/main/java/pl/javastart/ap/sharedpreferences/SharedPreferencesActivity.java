package pl.javastart.ap.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import pl.javastart.ap.R;

public class SharedPreferencesActivity extends AppCompatActivity {

	private static final String prefFirstRun = "pref_first_run";
	private static final String prefName = "pref_name";

	private TextView message;
	private EditText name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_preferences);

		message = (TextView) findViewById(R.id.textView1);
		name = (EditText) findViewById(R.id.dirName);

		SharedPreferences preferences = getPreferences(MODE_PRIVATE);

		boolean firstRun = preferences.getBoolean(prefFirstRun, true);

		if (firstRun) {
			message.setText("Cześć, miło Cię widzieć. Jesteś tutaj nowy?");
		} else {
			String savedName = preferences.getString(prefName, "");
			message.setText("Już Cię znamy " + savedName);
			name.setText(savedName);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);

		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean(prefFirstRun, false);
		editor.putString(prefName, name.getText().toString());
		editor.commit();
	}

}
