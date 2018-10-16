package pl.javastart.ap.filesystem;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import pl.javastart.ap.R;

public class FileSystemActivity extends AppCompatActivity {

	private TextView log;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filesystem);
		log = (TextView) findViewById(R.id.log);

		File homeDir = Environment.getExternalStorageDirectory();

		if (homeDir.isDirectory()) {
			System.out.println("true");
		}

		for (File file : homeDir.listFiles()) {
			Log.d("log", file.getName());
		}

		Log.d("log", "Removeable: " + Environment.isExternalStorageRemovable());

	}

	public void createDir(View view) {
		File dir = new File(Environment.getExternalStorageDirectory(), "/javastart/android/podstawy/");
		if (!dir.exists()) {
			dir.mkdirs();
			appendToLog("Utworzono folder " + dir.getAbsolutePath());
		} else {
			appendToLog("Folder " + dir.getAbsolutePath() + " już istnieje");
		}
	}

	public void removeDir(View view) {
		File dir = new File(Environment.getExternalStorageDirectory(), "/javastart/android/podstawy/");
		if (dir.exists()) {
			boolean deleted = dir.delete();
			if (deleted) {
				appendToLog("Usunięto folder");
			} else {
				appendToLog("Nie usunięto. Może folder nie jest pusty?");
			}
		} else {
			appendToLog("Folder nie istnieje");
		}
	}

	public void createFile(View view) {
		File dir = new File(Environment.getExternalStorageDirectory(), "/javastart/android/podstawy/");
		if (dir.exists()) {
			File file = new File(dir, "test.txt");
			try {
				boolean created = file.createNewFile();
				if(created) {
					appendToLog("Utworzono plik " + file.getAbsolutePath());
				} else {
					appendToLog("Nie utworzono. Może już istnieje?");
				}
			} catch (IOException e) {
				appendToLog("Błąd! " + e.getMessage());
			}
		} else {
			appendToLog("Najpierw utwórz folder");
		}
	}
	
	public void innerStorage(View view) {
		appendToLog(getFilesDir().getAbsolutePath());
		
		File internal = getFilesDir();
		
		File file = new File(internal, "test.txt");
		try {
			boolean created = file.createNewFile();
			if(created) {
				appendToLog("Utworzono plik " + file.getAbsolutePath());
			} else {
				appendToLog("Nie utworzono. Może już istnieje?");
			}
		} catch (IOException e) {
			appendToLog("Błąd! " + e.getMessage());
		}
		
		appendToLog("File list: "+fileList());
	}

	public void removeFile(View view) {
		File dir = new File(Environment.getExternalStorageDirectory(), "/javastart/android/podstawy/");
		File file = new File(dir, "test.txt");
		
		if(file.exists()) {
			file.delete();
			appendToLog("Usunięto");
		} else {
			appendToLog("Plik nie istnieje");
		}
	}

	private void appendToLog(String text) {
		String currentText = log.getText().toString();
		log.setText(text + "\n" + currentText);
	}
	
	public void removeDirWithContent(File dir) {
		if(dir.isDirectory()) {
			for(File innerFile : dir.listFiles()) {
				removeDirWithContent(innerFile);
			}
		}
		dir.delete();
	}

}
