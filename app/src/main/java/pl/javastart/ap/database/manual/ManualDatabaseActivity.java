package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.javastart.ap.R;

public class ManualDatabaseActivity extends Activity {

    private Button userListButton;
    private Button bookListButton;

    private ManualDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_example);

        userListButton  = (Button) findViewById(R.id.user_list_button);
        bookListButton = (Button) findViewById(R.id.book_list_button);

        addActionForUserListButton(userListButton);
        addActionForBookListButton(bookListButton);

        databaseHelper = ManualDatabaseHelper.getInstance(getApplicationContext());

    }

    private void addActionForUserListButton(Button userListButton) {
        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualDatabaseActivity.this, ManualDatabaseUserListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addActionForBookListButton(Button userListButton) {
        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualDatabaseActivity.this, ManualDatabaseBookListActivity.class);
                startActivity(intent);
            }
        });
    }

}
