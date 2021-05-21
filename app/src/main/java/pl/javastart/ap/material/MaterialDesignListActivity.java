package pl.javastart.ap.material;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.Lesson;
import pl.javastart.ap.R;

public class MaterialDesignListActivity extends AppCompatActivity {

    private List<Lesson> lessons = new ArrayList<>();
    {
        lessons.add(new Lesson("Toolbar", ToolbarActivity.class));
        lessons.add(new Lesson("Status bar color", StatusBarColorActivity.class));
        lessons.add(new Lesson("Material colors", MaterialColorsActivity.class));
        lessons.add(new Lesson("Floating action button", FloatingActionButtonActivity.class));
        lessons.add(new Lesson("CardView", CardViewActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lessons_list);

        ListView lessonsList = (ListView) findViewById(R.id.lessonsList);

        lessonsList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lessons));

        lessonsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), lessons.get(position).getActivity());
                startActivity(intent);
            }
        });
    }
}
