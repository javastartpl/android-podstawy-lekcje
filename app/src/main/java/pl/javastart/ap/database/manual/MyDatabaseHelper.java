package pl.javastart.ap.database.manual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static MyDatabaseHelper instance;

    private static final String DATABASE_NAME = "bazadanych";
    private static final int VERSION = 1;

    public static MyDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MyDatabaseHelper(context, DATABASE_NAME, null, VERSION);
        }
        return instance;
    }

    private MyDatabaseHelper(Context context,
                             String name,
                             SQLiteDatabase.CursorFactory factory,
                             int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO
    }
}
