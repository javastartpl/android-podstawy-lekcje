package pl.javastart.ap.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pl.javastart.ap.R;

public class AsyncTaskExampleActivity extends Activity {

    private TextView informationLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask_lesson);

        informationLabel = (TextView) findViewById(R.id.information_label);
        final EditText liczbaWejsciowa = (EditText) findViewById(R.id.number_to_check);

        Button checkButton = (Button) findViewById(R.id.check_button);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObliczenieParzystosci obliczenie = new ObliczenieParzystosci();
                try {
                    Long numberToCheck = Long.parseLong(liczbaWejsciowa.getText().toString());
                    obliczenie.execute(numberToCheck);
                }catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Podana liczba jest niepoprawna", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class ObliczenieParzystosci extends AsyncTask<Long,Integer,Boolean> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(AsyncTaskExampleActivity.this);
            dialog.setTitle("Sprawdzanie");
            dialog.setMessage("Trwa sprawdzanie parzystości...");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setIndeterminate(false);
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(Long... longs) {
            publishProgress(0, longs[0].intValue());

            int counter = 0;
            for(long i= longs[0]; i >= 1; i -= 2) {
                try {
                    Thread.sleep(50);
                    publishProgress(counter);
                    counter += 2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(i == 1) {
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(values.length == 2) {
                dialog.setMax(values[1]);
            }
            dialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Boolean czyPodzielne) {
            dialog.dismiss();
            String czyJest = (czyPodzielne) ? "jest" : "nie jest";
            informationLabel.setText("Obliczenia wykazaly, że podana liczba " + czyJest + " parzysta");
        }
    }
}
