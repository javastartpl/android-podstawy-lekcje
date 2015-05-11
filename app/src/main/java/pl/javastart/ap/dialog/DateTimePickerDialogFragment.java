package pl.javastart.ap.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

public class DateTimePickerDialogFragment extends DialogFragment {

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        TimePicker timePicker = new TimePicker(getActivity());
//        DatePicker datePicker = new DatePicker(getActivity());
//
//        LinearLayout layout = new LinearLayout(getActivity());
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.addView(timePicker);
//        layout.addView(datePicker);
//
//        Dialog dialog = new Dialog(getActivity());
//        dialog.setContentView(layout);
//        return dialog;
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TimePicker timePicker = new TimePicker(getActivity());
        DatePicker datePicker = new DatePicker(getActivity());
        datePicker.setCalendarViewShown(false);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(timePicker);
        layout.addView(datePicker);

        return layout;
    }
}
