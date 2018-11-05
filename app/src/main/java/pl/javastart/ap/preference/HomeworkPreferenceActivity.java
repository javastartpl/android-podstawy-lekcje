package pl.javastart.ap.preference;

import android.os.Bundle;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;

import pl.javastart.ap.R;

public class HomeworkPreferenceActivity extends AppCompatActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferencesFragment()).commit();
	}

	public static class PreferencesFragment extends PreferenceFragment {

		@Override
		public void onCreatePreferences(Bundle bundle, String s) {
			addPreferencesFromResource(R.xml.homework_preferences);
		}
	}
}
