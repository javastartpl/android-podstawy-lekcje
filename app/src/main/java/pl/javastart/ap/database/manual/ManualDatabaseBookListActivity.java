package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.javastart.ap.R;

public class ManualDatabaseBookListActivity extends Activity {

    private Button userListButton;
    private Button bookListButton;

    private ManualDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_example);

        userListButton  = (Button) findViewById(R.id.user_list_button);
        userListButton = (Button) findViewById(R.id.book_list_button);


        databaseHelper = ManualDatabaseHelper.getInstance(getApplicationContext());

    }


}
