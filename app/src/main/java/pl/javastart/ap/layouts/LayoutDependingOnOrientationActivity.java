package pl.javastart.ap.layouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.javastart.ap.R;

public class LayoutDependingOnOrientationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_depending_on_orientation);
    }
}
