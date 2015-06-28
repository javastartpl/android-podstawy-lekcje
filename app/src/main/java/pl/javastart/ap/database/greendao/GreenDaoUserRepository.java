package pl.javastart.ap.database.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import pl.javastart.ap.database.greendao.database.DaoMaster;
import pl.javastart.ap.database.greendao.database.DaoSession;
import pl.javastart.ap.database.greendao.database.User;
import pl.javastart.ap.database.greendao.database.UserDao;

public class GreenDaoUserRepository {

    private static UserDao getUserDao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "ormlite.db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getUserDao();
    }

    public static List<User> findAll(Context context) {
        return getUserDao(context).loadAll();
    }

    public static User findById(Context context, long userId) {
        return getUserDao(context).load(userId);
    }

    public static void addUser(Context context, User user) {
        getUserDao(context).insert(user);
    }

    public static void updateUser(Context context, User user) {
        getUserDao(context).update(user);
    }

    public static void deleteUser(Context context, User user) {
        getUserDao(context).delete(user);
    }
}
