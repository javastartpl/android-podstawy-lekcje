package pl.javastart.ap;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.asynctask.AsyncTaskListActivity;
import pl.javastart.ap.camera.CameraSelectionActivity;
import pl.javastart.ap.database.DatabaseListActivity;
import pl.javastart.ap.filesystem.FileSystemActivity;
import pl.javastart.ap.filesystem.FileSystemListActivity;
import pl.javastart.ap.intent.IntentExampleActivity;
import pl.javastart.ap.intent.IntentListActivity;
import pl.javastart.ap.layouts.LayoutListActivity;
import pl.javastart.ap.listview.ListExampleSelectionActivity;
import pl.javastart.ap.preference.CurrentPreferencesActivity;
import pl.javastart.ap.sharedpreferences.SharedPreferencesActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private List<Lesson> lessons = new ArrayList<>(30);
	{
        lessons.add(new Lesson("Layouty", LayoutListActivity.class));
        lessons.add(new Lesson("Intent", IntentListActivity.class));
		lessons.add(new Lesson("Listy (ListView)", ListExampleSelectionActivity.class));
		lessons.add(new Lesson("SharedPreferences", SharedPreferencesActivity.class));
		lessons.add(new Lesson("Ustawienia (Preferences)", CurrentPreferencesActivity.class));
		lessons.add(new Lesson("System plików", FileSystemActivity.class));
		lessons.add(new Lesson("Obsługa aparatu", CameraSelectionActivity.class));
		lessons.add(new Lesson("System plików", FileSystemListActivity.class));
		lessons.add(new Lesson("AsyncTask", AsyncTaskListActivity.class));
        lessons.add(new Lesson(("Baza danych"), DatabaseListActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView lessonsList = (ListView) findViewById(R.id.lessonsList);

		lessonsList.setAdapter(new ArrayAdapter<Lesson>(this, android.R.layout.simple_list_item_1, lessons));

		lessonsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getApplicationContext(), lessons.get(position).getActivity());
				startActivity(intent);
			}
		});
	}
}
