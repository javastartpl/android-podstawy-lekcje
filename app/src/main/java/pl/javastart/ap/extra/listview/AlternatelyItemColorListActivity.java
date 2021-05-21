package pl.javastart.ap.extra.listview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import pl.javastart.ap.R;

public class AlternatelyItemColorListActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		ListView animalList = findViewById(R.id.animalList);

		final String[] animals = { "Kot", "Pies", "Słoń", "Kot", "Pies", "Słoń", "Kot", "Pies", "Słoń", "Kot", "Pies",
				"Słoń" };
		
		AlternatelyColorAdapter adapter= new AlternatelyColorAdapter(animals);

		animalList.setAdapter(adapter);

	}
	
	private class AlternatelyColorAdapter extends BaseAdapter {
		
		private String[] items;
		
		public AlternatelyColorAdapter(String[] items) {
			this.items = items;
		}

		@Override
		public int getCount() {
			return items.length;
		}
			
		@Override
		public String getItem(int position) {
			return items[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
			}

			TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
			
			textView.setText(getItem(position));
			
			int color = Color.LTGRAY;
			if(position % 2 == 0) {
				color = Color.WHITE;
			} 
			textView.setBackgroundColor(color);

			return convertView;
		}
	}

}
