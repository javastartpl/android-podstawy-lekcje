package pl.javastart.ap.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.javastart.ap.R;

public class AsyncTaskTrainingActivity extends Activity {

    private TextView countdownLabel;
    private Button countdownButton;

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asynctask_training);

        countdownLabel = (TextView)findViewById(R.id.counter);

        countdownButton = (Button)findViewById(R.id.countdownButton);
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Countdown countdown = new Countdown();
                countdown.execute(10000);
            }
        });
	}

	private class Countdown extends AsyncTask<Integer, String, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
            countdownButton.setVisibility(View.GONE);
            countdownLabel.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Integer... miliseconds) {
			
			for(int i = miliseconds[0]; i>0; i -= 1000) {
				int seconds = i/1000;
				publishProgress(seconds+"...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
			
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
            countdownLabel.setText(values[0]);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
            countdownButton.setVisibility(View.VISIBLE);
            countdownLabel.setVisibility(View.GONE);
		}

	}

}
