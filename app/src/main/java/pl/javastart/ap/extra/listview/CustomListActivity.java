package pl.javastart.ap.extra.listview;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import pl.javastart.ap.R;

public class CustomListActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		ListView animalList = findViewById(R.id.animalList);

		final Animal[] animals = { new Animal("Kot", R.drawable.cat), new Animal("Pies", R.drawable.dog),
				new Animal("Słoń", R.drawable.elephant) };

		AnimalAdapter adapter = new AnimalAdapter(animals);
		animalList.setAdapter(adapter);

		animalList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "Wybrano element " + position + ", czyli " + animals[position],
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	private class Animal {
		private String name;
		private int imageResource;

		Animal(String name, int imageResource) {
			this.name = name;
			this.imageResource = imageResource;
		}

		public String getName() {
			return name;
		}

		int getImageResource() {
			return imageResource;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private class AnimalAdapter extends BaseAdapter {

		private Animal[] animals;

		AnimalAdapter(Animal[] animals) {
			this.animals = animals;
		}

		@Override
		public int getCount() {
			return animals.length;
		}

		@Override
		public Animal getItem(int position) {
			return animals[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.list_item_animal, parent, false);
			}

			ImageView animalImage = convertView.findViewById(R.id.animal_image);
			TextView animalName = convertView.findViewById(R.id.animal_name);

			animalImage.setImageResource(getItem(position).getImageResource());
			animalName.setText(getItem(position).getName());

			return convertView;
		}
	}

}
