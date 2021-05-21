package pl.javastart.ap.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import pl.javastart.ap.R;

public class DialogActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private TextView selectedOptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        selectedOptionText = (TextView) findViewById(R.id.selected_option_text);
    }

    public void infoDialog(View view) {
        InfoDialogFragment infoDialogFragment = new InfoDialogFragment();
        infoDialogFragment.show(getSupportFragmentManager(), null);
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
