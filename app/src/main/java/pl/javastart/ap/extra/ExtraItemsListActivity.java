package pl.javastart.ap.extra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.Lesson;
import pl.javastart.ap.R;
import pl.javastart.ap.extra.listview.AlternatelyItemColorListActivity;
import pl.javastart.ap.extra.listview.CustomListActivity;
import pl.javastart.ap.extra.listview.SimpleListActivity;

public class ExtraItemsListActivity extends AppCompatActivity {

	private List<Lesson> lessons = new ArrayList<>(2);
	{
		lessons.add(new Lesson("ListView: Prosta lista", SimpleListActivity.class));
		lessons.add(new Lesson("ListView: Niestandardowe elementy", CustomListActivity.class));
		lessons.add(new Lesson("ListView: Naprzemienne kolory", AlternatelyItemColorListActivity.class));
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
