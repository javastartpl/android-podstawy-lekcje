package pl.javastart.ap.webclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import pl.javastart.ap.R;

public class WebclientActivity extends Activity implements NewCategoryCallback, FinishedDownloadingCatetegoriesCallback {

    private Spinner categorySpinner;
    private ArrayAdapter<Category> categoryAdapter;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webclient);
        log = (TextView) findViewById(R.id.log);
        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        categoryAdapter = new ArrayAdapter<>(WebclientActivity.this, android.R.layout.simple_dropdown_item_1line);
        categorySpinner.setAdapter(categoryAdapter);
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

        if(item.getItemId() == R.id.refresh) {
            refreshCategories();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshCategories() {
        new DownloadCategoriesAsyncTask(this, log).execute();
    }


    private void addNewCategoryPressed() {
        NewCategoryFragment fragment = new NewCategoryFragment();
        fragment.show(getFragmentManager(), "newCategoryFragment");
    }

    @Override
    public void newCategoryAddButtonPressed(String name) {
        Category category = new Category(name);
        new NewCategoryAsyncTask(WebclientActivity.this, log).execute(category);
    }

    @Override
    public void onFinishedDownloadingCategories(List<Category> categories) {
        categoryAdapter.clear();
        categoryAdapter.addAll(categories);
        categorySpinner.invalidate();
    }
}
