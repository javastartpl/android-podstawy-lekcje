package pl.javastart.ap.database.manual.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import pl.javastart.ap.database.manual.ManualDatabaseHelper;

public class LoanRepository {

    public static Loan findById(int id, Context context) {
        ManualDatabaseHelper databaseHelper = ManualDatabaseHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from loan where id = ?", new String[]{Long.toString(id)});
        cursor.moveToNext();

//        Loan loan = new Loan();
//        loan.setId(cursor.getInt(0));
//        loan.setDateFrom();
//        loan.setDateTo();
//        loan.setName(cursor.getString(1));
//        loan.setSurname(cursor.getString(2));
//
//        db.close();

        return null;
    }


}
