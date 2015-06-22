package pl.javastart.ap.database.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import pl.javastart.ap.database.ormlite.model.User;


public class OrmLiteDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static OrmLiteDatabaseHelper instance;

    private static final String DATABASE_NAME = "ormLiteDB";
    private static final int DATABASE_VERSION = 2;

    private RuntimeExceptionDao<User, Long> userDao;

    private OrmLiteDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static OrmLiteDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new OrmLiteDatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.d("database", "onCreate start");
        recreateTables();
        Log.d("database", "onCreate end");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.d("database", "onUpgrade start");
        recreateTables();
        Log.d("database", "onUpgrade end");
    }

    private void recreateTables() {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public RuntimeExceptionDao<User, Long> getUserDao() {
        if (userDao == null) {
            userDao = getRuntimeExceptionDao(User.class);
        }
        return userDao;
    }

}