package pl.javastart.ap.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pl.javastart.ap.R;

public class IntentExampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_example);
    }

    public void googleButton(View view) {
        String url = "http://www.google.pl";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void shareButton(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Testujemy wysylanie");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Wszystko dziala!");
        startActivity(intent);
    }

    public void mapButton(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:51.109596,17.032613"));
        startActivity(intent);
    }

}
