package pl.javastart.ap.actionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import pl.javastart.ap.R;


public class ActionBarActivity extends Activity implements ActionMode.Callback {

    private boolean displayTitle = true;
    private boolean displayHomeButton = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        if(getActionBar() != null) {
            getActionBar().setDisplayShowTitleEnabled(displayTitle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_example_one:
                Toast.makeText(this, "Jeden", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_example_two:
                Toast.makeText(this, "Dwa", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_example_three:
                Toast.makeText(this, "Trzy", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_example_four:
                Toast.makeText(this, "Cztery", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_example_five:
                Toast.makeText(this, "Pięć", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_example_six:
                Toast.makeText(this, "Sześć", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                Toast.makeText(this, "Naciśnięto przycisk home!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleTitleButton(View view) {
        displayTitle = !displayTitle;
        if (getActionBar() != null) {
            getActionBar().setDisplayShowTitleEnabled(displayTitle);
        }
    }

    public void toogleActionBarVisibilityButton(View view) {
        if(getActionBar() != null) {
            if(getActionBar().isShowing()) {
                getActionBar().hide();
            } else {
                getActionBar().show();
            }
        }
    }

    public void startActionModeButton(View view) {
        startActionMode(this);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_example_action_mode, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_1:
                Toast.makeText(this, "Akcja 1", Toast.LENGTH_SHORT).show();;
                return true;
            case R.id.menu_action_2:
                Toast.makeText(this, "Akcja 2", Toast.LENGTH_SHORT).show();;
                mode.finish();
                return true;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // NOOP
    }

    public void toggleHomeButton(View view) {
        if(getActionBar() != null) {
            displayHomeButton = !displayHomeButton;
            getActionBar().setDisplayHomeAsUpEnabled(displayHomeButton);
        }
    }
}
