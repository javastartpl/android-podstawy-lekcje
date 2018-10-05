package pl.javastart.ap.layouts;

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

public class LayoutListActivity extends AppCompatActivity {

    private List<Lesson> lessons = new ArrayList<>(3);
    {
        lessons.add(new Lesson("Łączenie layoutu z Activity",InputActivity.class));
        lessons.add(new Lesson("Przykład z lekcji", LayoutExampleActivity.class));
        lessons.add(new Lesson("Zadanie do wykonania 1",LayoutTrainingActivity.class));
        lessons.add(new Lesson("Layout w zależności od orientacji",LayoutDependingOnOrientationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lessons_list);

        ListView lessonsList = findViewById(R.id.lessonsList);

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
