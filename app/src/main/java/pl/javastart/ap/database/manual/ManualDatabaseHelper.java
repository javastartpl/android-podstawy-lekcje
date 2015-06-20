package pl.javastart.ap.database.manual;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.net.ContentHandler;


public class ManualDatabaseHelper extends SQLiteOpenHelper {

    private static ManualDatabaseHelper instance;

    private static final String DATABASE_NAME = "manualDB";
    private static final int DATABASE_VERSION = 6;

    private ManualDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static ManualDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ManualDatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("database", "onCreate start");
        recreateDB(db);
        Log.d("database", "onCreate finished");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("database", "onUpgrade start");
        recreateDB(db);
        Log.d("database", "onUpgrade finished");

    }

    private void recreateDB(SQLiteDatabase db) {
        db.execSQL("drop table if exists user");
        db.execSQL("create table user (" +
                "id integer primary key autoincrement not null, " +
                "name TEXT, " +
                "surname TEXT" +
                ")");
    }


}