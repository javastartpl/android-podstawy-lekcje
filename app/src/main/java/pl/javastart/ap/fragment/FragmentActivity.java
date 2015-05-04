package pl.javastart.ap.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import pl.javastart.ap.R;

public class FragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Log.d("fragment", "before creating");
        MyFragment fragment = new MyFragment();
        Log.d("fragment", "after creating");

        Log.d("fragment", "before adding");
        getFragmentManager().beginTransaction().add(fragment, "").commit();
        Log.d("fragment", "after adding");
    }

}
