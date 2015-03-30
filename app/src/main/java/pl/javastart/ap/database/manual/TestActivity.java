package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyDatabaseHelper databaseHelper = MyDatabaseHelper.getInstance(getApplicationContext());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL("select * from user");
        db.close();

    }
}
