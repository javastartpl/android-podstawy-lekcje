package pl.javastart.ap.preference;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import pl.javastart.ap.R;

public class PreferenceExampleActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new PreferencesFragment()).commit();
	}

	public static class PreferencesFragment extends PreferenceFragmentCompat {

		@Override
		public void onCreatePreferences(Bundle bundle, String s) {
			addPreferencesFromResource(R.xml.preferences);
		}
	}
}
