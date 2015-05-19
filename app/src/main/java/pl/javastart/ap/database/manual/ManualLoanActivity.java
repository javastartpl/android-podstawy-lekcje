package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import pl.javastart.ap.R;
import pl.javastart.ap.database.manual.model.Loan;
import pl.javastart.ap.database.manual.model.LoanRepository;

public class ManualLoanActivity extends Activity implements DatePickerDialogFragment.OnLoanDateSetListener {

    private static final String ARG_LOAN_ID = "arg.loan.id";

    private Loan loan;
    private TextView loanDateTextView;
    private TextView returnDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_loan);
        loanDateTextView = (TextView) findViewById(R.id.loan_date_start_text_view);
        returnDateTextView = (TextView) findViewById(R.id.loan_date_return_text_view);

        int loanId = getIntent().getExtras().getInt(ARG_LOAN_ID);

        loan = LoanRepository.findById(loanId, this);

        fillFields();
    }

    private void fillFields() {
        loanDateTextView.setText(formatDate(loan.getDateFrom()));
        returnDateTextView.setText(formatDate(loan.getDateTo()));
    }


    public void changeLoanDate(View view) {
        int mode = DatePickerDialogFragment.MODE_LOAN_START;
        Date date = loan.getDateFrom();
        showDatePickerFragment(mode, date);
    }

    public void changeReturnDate(View view) {
        int mode = DatePickerDialogFragment.MODE_RETURN;
        Date date = loan.getDateTo();
        showDatePickerFragment(mode, date);
    }

    private void showDatePickerFragment(int mode, Date date) {
        DatePickerDialogFragment.Builder builder = new DatePickerDialogFragment.Builder();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        builder.day(day).month(month).year(month).mode(mode);
        builder.build().show(getFragmentManager(), null);
    }

    @Override
    public void onDateSet(int mode, int year, int month, int day) {
        switch (mode) {
            case DatePickerDialogFragment.MODE_LOAN_START:
                loanDateTextView.setText(formatDate(day, month, year));
                break;
            case DatePickerDialogFragment.MODE_RETURN:
                returnDateTextView.setText(formatDate(day, month, year));
                break;
        }
    }

    private String formatDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return formatDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
    }

    private String formatDate(int day, int month, int year) {
        return String.format("%02d-%02d-%04d", day, month, year);
    }
}
