package pl.javastart.ap.filesystem;

import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import pl.javastart.ap.R;

public class FileSystemHomeworkActivity extends AppCompatActivity {

	private EditText dirNameEditText;
	private EditText fileNameEditText;
	private TextView log;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filesystem_homework);

		log = (TextView) findViewById(R.id.log);
		dirNameEditText = (EditText) findViewById(R.id.dirName);
		fileNameEditText = (EditText) findViewById(R.id.fileName);
	}

	public void createFile(View view) {
		File dir = new File(Environment.getExternalStorageDirectory(), dirNameEditText.getText().toString());
		if (dir.exists()) {
			appendToLog("Folder: " + dir.getAbsolutePath() + " już istnial");
		} else {
			boolean created = dir.mkdirs();

			if (created) {
				appendToLog("Utworzono folder: " + dir.getAbsolutePath());
			} else {
				appendToLog("Nie udało się stworzyć folderu");
			}
		}

		File file = new File(dir, fileNameEditText.getText().toString());

		if (file.exists()) {
			appendToLog("Plik: " + dir.getAbsolutePath() + " już istnieje!");
		} else {

			boolean created;
			try {
				created = file.createNewFile();
				if (created) {
					appendToLog("Utworzono plik: " + file.getAbsolutePath());
				} else {
					appendToLog("Nie udało się stworzyć pliku");
				}
			} catch (IOException e) {
				appendToLog("Wystąpił błąd podczas tworzenia pliku:  " + e.getMessage());
			}
		}
	}

	private void appendToLog(String text) {
		String currentText = log.getText().toString();
		log.setText(text + "\n" + currentText);
	}

}
