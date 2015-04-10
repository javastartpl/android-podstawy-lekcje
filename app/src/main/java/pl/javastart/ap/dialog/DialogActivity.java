package pl.javastart.ap.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.Lesson;
import pl.javastart.ap.R;
import pl.javastart.ap.filesystem.FileSystemActivity;
import pl.javastart.ap.filesystem.FileSystemHomeworkActivity;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void infoDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Prosty dialog");
        builder.setMessage("Tutaj treść dialogu");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Naciśnięto: OK", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Naciśnięto: Anuluj", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    public void singleListElementDialog(View view) {
        final String[] colors = {"Czerwony", "Zielony", "Niebieski"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz kolor");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Wybrano kolor " + colors[which], Toast.LENGTH_SHORT).show();
            }
        });
		builder.create().show();
    }


    public void multipleListElementsDialog(View view) {
        final String[] colors = {"Czerwony", "Zielony", "Niebieski"};
        final boolean[] selected = {false, true, false};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz kolor");
        builder.setMultiChoiceItems(colors, selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                String selected = (isChecked) ? "Zaznaczono" : "Odznaczono";
                Toast.makeText(getApplicationContext(), selected + " kolor " + colors[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("OK", null);
        builder.setNegativeButton("Anuluj", null);

        builder.create().show();
    }


    public void hourSelectionDialog(View view) {

    }

    public void dateSelectionDialog(View view) {
    }

    public void hourAndHourSelectionDialog(View view) {
    }
}
