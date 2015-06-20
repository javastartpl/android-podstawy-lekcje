package pl.javastart.ap.database.manual.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.database.manual.ManualDatabaseHelper;

public class UserRepository {


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


    public static User findById(Context context, int userId) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from user where id = ?", new String[]{Long.toString(userId)});
        User user = null;
        if(cursor.moveToNext() != false) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setSurname(cursor.getString(2));
        }
        db.close();
        return user;
    }
}
