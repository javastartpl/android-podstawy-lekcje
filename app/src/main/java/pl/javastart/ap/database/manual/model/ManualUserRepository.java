package pl.javastart.ap.database.manual.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.database.manual.ManualDatabaseHelper;

public class ManualUserRepository {

    public static List<User> findAll(Context context) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user", null);

        List<User> userList = new ArrayList<>();
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setSurname(cursor.getString(2));
            userList.add(user);
        }

        return userList;
    }

    public static User findById(Context context, long userId) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from user where id = ?", new String[]{Long.toString(userId)});
        User user = null;
        if (cursor.moveToNext()) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setSurname(cursor.getString(2));
        }
        db.close();
        return user;
    }

    public static void addUser(Context context, User user) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String sql = "insert into user(name, surname) values (?, ?)";
        db.execSQL(sql, new String[]{user.getName(), user.getSurname()});
        db.close();
    }

    public static void updateUser(Context context, User user) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("surname", user.getSurname());
        int result = db.update("user", values, "id = ?", new String[]{Long.toString(user.getId())});
        Log.d("update count", "update count: " + result);
        db.close();
    }

    public static void deleteUser(Context context, User user) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        int result = db.delete("user", "id = ?", new String[]{Long.toString(user.getId())});
        Log.d("delete count", "delete count: " + result);
        db.close();
    }
}
