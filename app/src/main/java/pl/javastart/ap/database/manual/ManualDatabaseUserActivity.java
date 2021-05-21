package pl.javastart.ap.database.manual;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.javastart.ap.R;
import pl.javastart.ap.database.manual.model.ManualUserRepository;
import pl.javastart.ap.database.manual.model.User;

public class ManualDatabaseUserActivity extends AppCompatActivity {

    public static final String PARAM_USER_ID = "param.user.id";
    private static final int INVALID_ID = -100;

    private User user;

    private EditText nameEditText;
    private EditText surnameEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_user);

        nameEditText = (EditText) findViewById(R.id.name);
        surnameEditText = (EditText) findViewById(R.id.surname);
        saveButton = (Button) findViewById(R.id.save_buton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(nameEditText.getText().toString());
                user.setSurname(surnameEditText.getText().toString());
                ManualUserRepository.updateUser(ManualDatabaseUserActivity.this, user);
                finish();
            }
        });


        long id = getIntent().getLongExtra(PARAM_USER_ID, INVALID_ID);
        if (id == INVALID_ID) {
            finish();
        }

        user = ManualUserRepository.findById(this, id);

        nameEditText.setText(user.getName());
        surnameEditText.setText(user.getSurname());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_delete_user) {
            ManualUserRepository.deleteUser(ManualDatabaseUserActivity.this, user);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
