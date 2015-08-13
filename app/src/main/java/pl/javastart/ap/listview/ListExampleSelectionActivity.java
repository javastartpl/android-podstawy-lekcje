package pl.javastart.ap.listview;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.Lesson;
import pl.javastart.ap.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListExampleSelectionActivity extends Activity {

	private List<Lesson> lessons = new ArrayList<>(3);
	{
		lessons.add(0, new Lesson("Prosta lista", SimpleListActivity.class));
		lessons.add(1, new Lesson("Niestandardowe elementy", CustomListActivity.class));
		lessons.add(2, new Lesson("Naprzemienne kolory", AlternatelyItemColorListActivity.class));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_lessons_list);

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
