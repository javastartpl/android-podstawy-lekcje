package pl.javastart.ap.layouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import pl.javastart.ap.R;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button okButton = findViewById(R.id.ok);

        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TextView nameTextView = findViewById(R.id.name);
                RadioButton genderMale = findViewById(R.id.male);
                RadioButton genderFemale = findViewById(R.id.female);

                String name = nameTextView.getText().toString();
                String gender = "";

                if(genderMale.isChecked()) {
                    gender = "Facet! ";
                }

                if(genderFemale.isChecked()) {
                    gender = "Kobieta! ";
                }

                Toast.makeText(getApplicationContext(), "O " + gender + name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
