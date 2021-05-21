package pl.javastart.ap.camera;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.Lesson;
import pl.javastart.ap.R;

public class CameraSelectionActivity extends AppCompatActivity {

    private List<Lesson> lessons = new ArrayList<>(2);

    {
        lessons.add(0, new Lesson("Pobranie zdjęcia z aparatu", SimpleCameraActivity.class));
        lessons.add(1, new Lesson("Pobranie zdjęcia z aparatu + Glide", GlideCameraActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_lessons_list);

        ListView lessonsList = (ListView) findViewById(R.id.lessonsList);

        lessonsList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lessons));

        lessonsList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), lessons.get(position).getActivity());
                startActivity(intent);
            }
        });
    }

}
