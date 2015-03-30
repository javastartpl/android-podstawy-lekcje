package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import pl.javastart.ap.R;

public class ManualDatabaseActivity extends Activity {

    private TextView log;
    private EditText nameEditText;
    private EditText surnameEditText;

    private ManualDatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_example);

        log  = (TextView) findViewById(R.id.log);
        nameEditText = (EditText) findViewById(R.id.name);
        surnameEditText = (EditText) findViewById(R.id.surname);

        databaseHelper = ManualDatabaseHelper.getInstance(getApplicationContext());
//
//        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//
//        db.execSQL("delete from user");
//
//        db.execSQL("insert into user(name, surname) values ('Jan', 'Nowak')");
//        db.execSQL("insert into user values ('Karol', 'Krawczyk')");
//
//        Cursor cursor = db.rawQuery("select * from user", null);
//
//        while (cursor.moveToNext()) {
//            Log.d("database", cursor.getInt(0) + " " + cursor.getString(1) + " " + cursor.getString(2));
//        }
    }

    public void addUserClicked(View view) {
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String sql = "insert into user(name, surname) values (?, ?)";

        db.execSQL(sql, new String[]{name, surname});

        appendToLog(sql);
        db.close();;
    }

    public void showAllClicked(View view) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user", null);

        while (cursor.moveToNext()) {
            appendToLog(cursor.getInt(0) + " " + cursor.getString(1) + " " + cursor.getString(2));
        }
        cursor.close();
        db.close();;
    }

    public void deleteAllClicked(View view) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String sql = "delete from user";
        db.execSQL(sql);
        appendToLog(sql);
        db.close();
    }

    private void appendToLog(String text) {
        String temp = log.getText().toString();
        log.setText(text + "\n" + temp);
    }
}
