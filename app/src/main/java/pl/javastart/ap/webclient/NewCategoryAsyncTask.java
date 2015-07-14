package pl.javastart.ap.webclient;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class NewCategoryAsyncTask extends AsyncTask<Category, String, Void> {

    private WebclientActivity activity;
    private TextView log;

    public NewCategoryAsyncTask(WebclientActivity webclientActivity, TextView log) {
        this.activity = webclientActivity;
        this.log = log;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Util.appendToLog(log, values[0]);
    }

    @Override
    protected Void doInBackground(Category... params) {
        request(params[0], "https://webservice-javastartpl.rhcloud.com/categories");
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Util.appendToLog(log, "Dodaję nową kategorię");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Util.appendToLog(log, "Dodano");
    }

    private InputStream request(Category category, String urlString) {

        JSONObject object = new JSONObject();
        try {
            object.put("name", category.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setDoOutput(false);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(object.toString());
            wr.flush();

            connection.connect();

            Log.d("", "responsecode: " + connection.getResponseCode());
            connection.getResponseCode();

            return connection.getInputStream();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

//    private StringBuffer request(String urlString) {
//        StringBuffer chaine = new StringBuffer("");
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestProperty("User-Agent", "");
//            connection.setDoOutput(false);
//            connection.setRequestMethod("GET");
//            connection.setDoInput(true);
//            connection.connect();
//
//            InputStream inputStream = connection.getInputStream();
//
//            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                chaine.append(line);
//            }
//
//        } catch (IOException e) {
//            // writing exception to log
//            e.printStackTrace();
//        }
//
//        return chaine;
//    }

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
        reader.beginObject();
        skipLinks(reader);

        reader.nextName();
        reader.beginObject();
        reader.nextName();
        reader.beginArray();
        while (reader.hasNext()) {
            categories.add(readCategory(reader));
        }
        reader.endArray();

        reader.endObject();
        return categories;
    }

    private Category readCategory(JsonReader reader) throws IOException {
        String categoryName = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                categoryName = reader.nextString();
            }
            skipLinks(reader);
        }
        reader.endObject();
        return new Category(categoryName);
    }

    private void skipLinks(JsonReader reader) throws IOException {
        reader.nextName();
        reader.beginObject();
        reader.nextName();
        reader.beginObject();
        reader.nextName();
        reader.nextString();
        reader.endObject();
        reader.endObject();
    }
}
