package pl.javastart.ap.webclient;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class DownloadCategoriesAsyncTask extends AsyncTask<String, String, List<Category>> {

    private TextView log;
    private FinishedDownloadingCatetegoriesCallback callback;

    public DownloadCategoriesAsyncTask(FinishedDownloadingCatetegoriesCallback callback, TextView log) {
        this.callback = callback;
        this.log = log;
    }

    @Override
    protected List<Category> doInBackground(String... params) {
        InputStream response = request("https://webservice-javastartpl.rhcloud.com/categories");
        publishProgress("Pobrano dane, rozpoczęcie parsowania JSON");
        try {
            return readJsonStream(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Util.appendToLog(log, values[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Util.appendToLog(log, "Rozpoczęcie połączenia");
    }

    @Override
    protected void onPostExecute(List<Category> categories) {
        for (Category category : categories) {
            Util.appendToLog(log, "Pobrano kategorię: " + category.getName());
        }
        callback.onFinishedDownloadingCategories(categories);
    }

    private InputStream request(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setDoOutput(false);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            return connection.getInputStream();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Category> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readCategoryArray(reader);
        } finally {
            reader.close();
        }
    }

    private List<Category> readCategoryArray(JsonReader reader) throws IOException {
        List<Category> categories = new ArrayList<>();
        reader.beginArray();

        while (reader.hasNext()) {
            categories.add(readCategory(reader));
        }
        reader.endArray();

        return categories;
    }

    private Category readCategory(JsonReader reader) throws IOException {
        Category category = new Category();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "id":
                    category.setId(reader.nextLong());
                    break;
                case "name":
                    category.setName(reader.nextString());
                    break;
                default:
                    break; // ignore
            }
        }
        reader.endObject();
        return category;
    }
}
