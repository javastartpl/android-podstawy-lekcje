package pl.javastart.ap.webclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pl.javastart.ap.R;

public class WebclientActivity extends Activity implements NewCategoryCallback {

    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webclient);
        log = (TextView) findViewById(R.id.log);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webclient, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            addNewCategoryPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addNewCategoryPressed() {
        NewCategoryFragment fragment = new NewCategoryFragment();
        fragment.show(getFragmentManager(), "newCategoryFragment");
    }

    @Override
    public void newCategoryAdded(String name) {
        new AddNewCategoryAsyncTask(log).execute();
    }
}
