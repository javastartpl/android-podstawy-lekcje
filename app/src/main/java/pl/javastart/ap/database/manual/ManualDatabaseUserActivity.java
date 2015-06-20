package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.javastart.ap.R;
import pl.javastart.ap.database.manual.model.User;

public class ManualDatabaseUserActivity extends Activity {

    public static final String PARAM_USER_ID = "param.user.id";
    private static final int INVALID_ID = -100;

    private ManualDatabaseHelper databaseHelper;
    private User user;

    private EditText nameEditText;
    private EditText surnameEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = ManualDatabaseHelper.getInstance(getApplicationContext());
        setContentView(R.layout.activity_database_user);

        nameEditText = (EditText) findViewById(R.id.name);
        surnameEditText = (EditText) findViewById(R.id.surname);
        saveButton = (Button)findViewById(R.id.save_buton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

    ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(getApplicationContext());
    SQLiteDatabase db = databaseHelper.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("name", nameEditText.getText().toString());
    values.put("surname", surnameEditText.getText().toString());
    int result = db.update("user", values, "id = ?", new String[]{Integer.toString(user.getId())});
    Log.d("update count", "update count: " + result);
    db.close();

                finish();
            }
        });


        long id = getIntent().getLongExtra(PARAM_USER_ID, INVALID_ID);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from user where id = ?", new String[]{Long.toString(id)});

        cursor.moveToNext();

        user = new User();
        user.setId(cursor.getInt(0));
        user.setName(cursor.getString(1));
        user.setSurname(cursor.getString(2));

        db.close();

        nameEditText.setText(user.getName());
        surnameEditText.setText(user.getSurname());

        Log.d("", user.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_delete_user) {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            int result = db.delete("user", "id = ?", new String[]{Integer.toString(user.getId())});
            Log.d("delete count", "update count: " + result);
            db.close();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
