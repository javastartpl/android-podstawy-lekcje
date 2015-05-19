package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

public class DatePickerDialogFragment extends DialogFragment implements OnDateSetListener {

    public static final int MODE_LOAN_START = 1;
    public static final int MODE_RETURN = 2;

    public static final String ARGUMENT_MODE = "argument.mode";
    public static final String ARGUMENT_DAY = "argument.day";
    public static final String ARGUMENT_MONTH = "argument.month";
    public static final String ARGUMENT_YEAR = "argument.year";

    private OnLoanDateSetListener loanDateSetListener;
    private int mode;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mode = getArguments().getInt(ARGUMENT_MODE);
        int day = getArguments().getInt(ARGUMENT_DAY);
        int month = getArguments().getInt(ARGUMENT_MONTH);
        int year = getArguments().getInt(ARGUMENT_YEAR);

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month - 1, day);

        return dialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            loanDateSetListener = (OnLoanDateSetListener) getActivity();
        } catch (ClassCastException e) {
            throw new RuntimeException(getActivity().getClass().getSimpleName() + " should implement DatePickerDialogFragment.OnLoanDateSetListener");
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        loanDateSetListener.onDateSet(mode, year, monthOfYear + 1, dayOfMonth);
    }

    public interface OnLoanDateSetListener {
        void onDateSet(int mode, int year, int month, int day);
    }

    public static class Builder {
        private Bundle args = new Bundle();

        public Builder day(int day) {
            args.putInt(ARGUMENT_DAY, day);
            return this;
        }

        public Builder month(int month) {
            args.putInt(ARGUMENT_MONTH, month);
            return this;
        }

        public Builder year(int year) {
            args.putInt(ARGUMENT_YEAR, year);
            return this;
        }

        public Builder mode(int mode) {
            args.putInt(ARGUMENT_MODE, mode);
            return this;
        }

        public DatePickerDialogFragment build() {
            DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
            datePickerDialogFragment.setArguments(args);
            return datePickerDialogFragment;
        }
    }
}
