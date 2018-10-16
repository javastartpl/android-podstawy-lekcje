package pl.javastart.ap.extra.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import pl.javastart.ap.R;

public class SimpleListActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		ListView animalList = (ListView) findViewById(R.id.animalList);

		final String[] animals = { "Kot", "Pies", "Słoń", "Kot", "Pies", "Słoń", "Kot", "Pies", "Słoń", "Kot", "Pies", "Słoń" };
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, animals);

		animalList.setAdapter(adapter);

		animalList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "Wybrano element " + position + ", czyli " + animals[position],
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
