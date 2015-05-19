package pl.javastart.ap.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.Lesson;
import pl.javastart.ap.R;
import pl.javastart.ap.filesystem.FileSystemActivity;
import pl.javastart.ap.filesystem.FileSystemHomeworkActivity;

public class DialogActivity extends Activity implements DialogInterface.OnClickListener {

    private TextView selectedOptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        selectedOptionText = (TextView) findViewById(R.id.selected_option_text);
    }

    public void infoDialog(View view) {
        InfoDialogFragment infoDialogFragment = new InfoDialogFragment();
        infoDialogFragment.show(getFragmentManager(), null);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {

        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                selectedOptionText.setText("OK");
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                selectedOptionText.setText("Anuluj");
                break;
        }
    }

    public void singleListElementDialog(View view) {
        SingleListElementDialogFragment singleListElementDialogFragment = new SingleListElementDialogFragment();
        singleListElementDialogFragment.show(getFragmentManager(), null);
    }


    public void multipleListElementsDialog(View view) {
        MultipleListElementsDialogFragment multipleListElementsDialogFragment = new MultipleListElementsDialogFragment();
        multipleListElementsDialogFragment.show(getFragmentManager(), null);
    }


    public void hourSelectionDialog(View view) {
        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
        timePickerDialogFragment.show(getFragmentManager(), null);
    }

    public void dateSelectionDialog(View view) {
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.show(getFragmentManager(), null);
    }


    public void customDialog(View view) {
        CustomDialogFragment customDialogFragment = new CustomDialogFragment();
        customDialogFragment.show(getFragmentManager(), null);
    }
}
