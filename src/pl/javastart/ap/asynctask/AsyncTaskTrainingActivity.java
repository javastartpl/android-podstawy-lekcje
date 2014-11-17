package pl.javastart.ap.asynctask;

import pl.javastart.ap.R;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

public class AsyncTaskTrainingActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_asynctask_training);
	}

	private class Odliczanie extends AsyncTask<Integer, String, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Integer... miliseconds) {
			
			for(int i = miliseconds[0]; i>0; i -= 1000) {
				int seconds = i/1000;
				publishProgress(seconds+"...");
			}
			
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}

	}

}
