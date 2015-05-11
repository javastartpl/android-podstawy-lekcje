package pl.javastart.ap.preference;

import java.util.Set;

import pl.javastart.ap.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CurrentPreferencesActivity extends Activity {

	private static final String PREF_LIGHT = "pref_light";
	private static final String PREF_WAHSING_MASHINE = "pref_washing_machine";
	private static final String PREF_NAME = "pref_name";
	private static final String PREF_ANIMALS = "pref_animals";
	private static final String PREF_ICE_CREAM_FLAVOURS = "pref_ice_cream_flavours";
	private static final String PREF_RINGTONE = "pref_ringtone";

	// views
	private TextView light;
	private TextView washingMachine;
	private TextView name;
	private TextView animal;
	private TextView iceCreamFlavour;
	private TextView ringtone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_preferences);

		light = (TextView) findViewById(R.id.light);
		washingMachine = (TextView) findViewById(R.id.washing_machine);
		name = (TextView) findViewById(R.id.name);
		animal = (TextView) findViewById(R.id.animal);
		iceCreamFlavour = (TextView) findViewById(R.id.ice_cream_flavour);
		ringtone = (TextView) findViewById(R.id.ringtone);
	}

	@Override
	protected void onResume() {
		super.onResume();

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

		boolean lightEnabled = sharedPreferences.getBoolean(PREF_LIGHT, false);
		boolean washingMachineEnabled = sharedPreferences.getBoolean(PREF_WAHSING_MASHINE, false);
		String nameValue = sharedPreferences.getString(PREF_NAME, "");
		String animalValue = sharedPreferences.getString(PREF_ANIMALS, "");
		Set<String> iceCreamFlavourValues = sharedPreferences.getStringSet(PREF_ICE_CREAM_FLAVOURS, null);
		String ringtoneValue = sharedPreferences.getString(PREF_RINGTONE, "");

		String lightValue = (lightEnabled) ? "włączone" : "wyłączone";
		String washingMachineValue = (washingMachineEnabled) ? "włączona" : "wyłączona";

		light.setText(lightValue);
		washingMachine.setText(washingMachineValue);
		name.setText(nameValue);
		animal.setText(animalValue);
        if(iceCreamFlavourValues != null) {
            iceCreamFlavour.setText(iceCreamFlavourValues.toString());
        }
		ringtone.setText(ringtoneValue);
	}

	public void changeSettings(View view) {
		Intent intent = new Intent(getApplicationContext(), PreferenceExampleActivity.class);
		startActivity(intent);
	}

	public void homework(View view) {
		Intent intent = new Intent(getApplicationContext(), HomeworkPreferenceActivity.class);
		startActivity(intent);
	}

}
