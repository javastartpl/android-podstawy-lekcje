package pl.javastart.ap.database;

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
import pl.javastart.ap.database.greendao.GreenDaoDatabaseUserListActivity;
import pl.javastart.ap.database.manual.ManualDatabaseUserListActivity;
import pl.javastart.ap.database.ormlite.OrmLiteDatabaseUserListActivity;

public class DatabaseListActivity extends AppCompatActivity {

    private List<Lesson> lessons = new ArrayList<>(2);

    {
        lessons.add(new Lesson("Manualna obsługa", ManualDatabaseUserListActivity.class));
        lessons.add(new Lesson("OrmLite", OrmLiteDatabaseUserListActivity.class));
        lessons.add(new Lesson("GreenDao", GreenDaoDatabaseUserListActivity.class));
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
