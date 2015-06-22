package pl.javastart.ap.database.ormlite.model;

import android.content.Context;

import java.util.List;

import pl.javastart.ap.database.ormlite.OrmLiteDatabaseHelper;

public class OrmLiteUserRepository {

    public static List<User> findAll(Context context) {
        OrmLiteDatabaseHelper databaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return databaseHelper.getUserDao().queryForAll();
    }

    public static User findById(Context context, long userId) {
        OrmLiteDatabaseHelper databaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        return databaseHelper.getUserDao().queryForId(userId);
    }

    public static void addUser(Context context, User user) {
        OrmLiteDatabaseHelper databaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        databaseHelper.getUserDao().create(user);
    }

    public static void updateUser(Context context, User user) {
        OrmLiteDatabaseHelper databaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        databaseHelper.getUserDao().update(user);
    }

    public static void deleteUser(Context context, User user) {
        OrmLiteDatabaseHelper databaseHelper = OrmLiteDatabaseHelper.getInstance(context);
        databaseHelper.getUserDao().delete(user);
    }
}
