package pl.javastart.ap.webclient;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import pl.javastart.ap.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebclientActivity extends AppCompatActivity implements NewCategoryCallback, FinishedDownloadingCatetegoriesCallback {

    private Spinner categorySpinner;
    private ArrayAdapter<Category> categoryAdapter;
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webclient);
        log = findViewById(R.id.log);
        categorySpinner = findViewById(R.id.category_spinner);
        categoryAdapter = new ArrayAdapter<>(WebclientActivity.this, android.R.layout.simple_dropdown_item_1line);
        categorySpinner.setAdapter(categoryAdapter);
        refreshCategories();
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

        if (item.getItemId() == R.id.refresh) {
            refreshCategories();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshCategoriesRetrofit() {
        Util.appendToLog(log, "Pobieranie danych za pomocą Retrofit");
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(WebServiceConstants.WEB_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryRetrofitService categoryService = restAdapter.create(CategoryRetrofitService.class);

        Call<List<Category>> call = categoryService.getAll();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Util.appendToLog(log, "Pobrano. Odświeżanie spinnera...");
                onFinishedDownloadingCategories(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // ignore
            }
        });
    }

    private void refreshCategories() {
        refreshCategoriesRetrofit();
        // old fasion way
        // new DownloadCategoriesAsyncTask(this, log).execute();
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
        Util.appendToLog(log, "Spinner odźwieżony.");
    }

    public void categoryDeleteButtonPressed(View view) {
        if (categorySpinner.getSelectedItem() == null) {
            return;
        }

        Category category = (Category) categorySpinner.getSelectedItem();

        Util.appendToLog(log, "Usuwanie kategorii " + category.getName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebServiceConstants.WEB_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CategoryRetrofitService categoryService = retrofit.create(CategoryRetrofitService.class);

        Call<Void> call = categoryService.delete(categorySpinner.getSelectedItemId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Util.appendToLog(log, "Usunięto.");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Util.appendToLog(log, "Coś poszło nie tak podczas usuwania.");
            }
        });
    }
}
