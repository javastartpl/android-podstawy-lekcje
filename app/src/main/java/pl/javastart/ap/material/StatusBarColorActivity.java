package pl.javastart.ap.material;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import pl.javastart.ap.R;

public class StatusBarColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_status_bar_color);
    }

    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    public void onColorBlueButtonPressed(View view) {
        setStatusBarColor(Color.BLUE);
    }

    public void onColorGreenButtonPressed(View view) {
        setStatusBarColor(Color.GREEN);
    }

    public void onColorRedButtonPressed(View view) {
        setStatusBarColor(Color.RED);
    }

    public void onColorBlackButtonPressed(View view) {
        setStatusBarColor(Color.BLACK);
    }
}
