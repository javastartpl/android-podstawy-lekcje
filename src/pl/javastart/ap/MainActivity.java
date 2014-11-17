package pl.javastart.ap;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.asynctask.AsyncTaskListActivity;
import pl.javastart.ap.camera.CameraSelectionActivity;
import pl.javastart.ap.filesystem.FileSystemActivity;
import pl.javastart.ap.filesystem.FileSystemListActivity;
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
		lessons.add(0, new Lesson("Listy (ListView)", ListExampleSelectionActivity.class));
		lessons.add(1, new Lesson("SharedPreferences", SharedPreferencesActivity.class));
		lessons.add(2, new Lesson("Ustawienia (Preferences)", CurrentPreferencesActivity.class));
		lessons.add(3, new Lesson("System plików", FileSystemActivity.class));
		lessons.add(4, new Lesson("Obsługa aparatu", CameraSelectionActivity.class));
		lessons.add(5, new Lesson("System plików", FileSystemListActivity.class));
		lessons.add(6, new Lesson("AsyncTask", AsyncTaskListActivity.class));
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
