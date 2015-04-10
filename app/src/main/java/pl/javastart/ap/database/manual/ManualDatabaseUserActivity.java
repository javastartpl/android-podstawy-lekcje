package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.R;

public class ManualDatabaseUserActivity extends Activity {

    private Button addUserButton;
    private EditText nameEditText;
    private EditText surnameEditText;
    private ListView userListView;
    private UserListAdapter userListAdapter;

    private List<User> userList = new ArrayList<>();

    private ManualDatabaseHelper databaseHelper;

    public ManualDatabaseUserActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_user_list);
        databaseHelper = ManualDatabaseHelper.getInstance(getApplicationContext());


        nameEditText = (EditText) findViewById(R.id.name);
        surnameEditText = (EditText) findViewById(R.id.surname);
        addUserButton  = (Button) findViewById(R.id.add_new_user_button);
        userListView = (ListView) findViewById(R.id.user_list);

        addActionForUserAddButton(addUserButton);

        userListAdapter = new UserListAdapter();
        updateUserList();
        userListView.setAdapter(userListAdapter);

    }

    private void addActionForUserAddButton(Button addUserButton) {
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser() {
        String name = nameEditText.getText().toString();
        String surname = surnameEditText.getText().toString();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String sql = "insert into user(name, surname) values (?, ?)";
        db.execSQL(sql, new String[]{name, surname});
        db.close();

        updateUserList();
    }

    private void updateUserList() {
        userList.clear();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user", null);

        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setSurname(cursor.getString(2));
            userList.add(user);
        }

        userListAdapter.notifyDataSetChanged();
    }

    private class UserListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return userList.size();
        }

        @Override
        public User getItem(int position) {
            return userList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(ManualDatabaseUserActivity.this);
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            TextView text = (TextView) convertView.findViewById(android.R.id.text1);
            User user = getItem(position);
            text.setText(user.getId() + ". " + user.getName() + " " + user.getSurname());

            return convertView;
        }
    }

}
