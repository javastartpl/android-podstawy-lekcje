package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.javastart.ap.R;
import pl.javastart.ap.database.manual.model.Loan;
import pl.javastart.ap.database.manual.model.User;

public class ManualDatabaseUserActivity extends Activity {

    private ManualDatabaseHelper databaseHelper;
    private User user;

    private EditText nameEditText;
    private EditText surnameEditText;
    private Button saveButton;
    private Button deleteButton;
    private Button addLoanButton;
    private ListView loanListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = ManualDatabaseHelper.getInstance(getApplicationContext());
        setContentView(R.layout.activity_database_user);

        nameEditText = (EditText) findViewById(R.id.name);
        surnameEditText = (EditText) findViewById(R.id.surname);
        saveButton = (Button)findViewById(R.id.save_buton);
        addLoanButton = (Button) findViewById(R.id.add_loan_button);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", nameEditText.getText().toString());
                values.put("surname", surnameEditText.getText().toString());
                int result = db.update("user", values, "id = ?", new String[]{Integer.toString(user.getId())});
                Log.d("update count", "update count: " + result);
                db.close();

                finish();
            }
        });

        deleteButton = (Button)findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                int result = db.delete("user", "id = ?", new String[]{Integer.toString(user.getId())});
                Log.d("delete count", "update count: " + result);
                db.close();
                finish();
            }
        });

        long id = getIntent().getLongExtra("id", -1);

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from user where id = ?", new String[]{Long.toString(id)});

        cursor.moveToNext();

        user = new User();
        user.setId(cursor.getInt(0));
        user.setName(cursor.getString(1));
        user.setSurname(cursor.getString(2));

        db.close();

        nameEditText.setText(user.getName());
        surnameEditText.setText(user.getSurname());

        Log.d("", user.toString());

        loanListView = (ListView) findViewById(R.id.loan_list);


        String query = "select * from loan where user_id = ?";
        List<Loan> loanList = new ArrayList<>();
        db = databaseHelper.getWritableDatabase();
        Cursor loanListCursor = db.rawQuery("select * from loan", null);

        while (cursor.moveToNext()) {
            Loan loan = new Loan();
            loan.setId(cursor.getInt(0));
            loan.setUserId(cursor.getInt(1));
            loan.setBookId(cursor.getInt(2));
            loan.setDateFrom(new Date(cursor.getLong(3)));
            loan.setDateTo(new Date(cursor.getLong(4)));
            loanList.add(loan);
        }

        loanListView.setAdapter(new ManualLoanAdapter(this, loanList));

        addLoanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualDatabaseUserActivity.this, ManualLoanActivity.class);
                intent.putExtra("user_id", user.getId());
                startActivity(intent);
            }
        });

    }

}
