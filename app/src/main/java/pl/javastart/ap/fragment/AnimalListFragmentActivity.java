package pl.javastart.ap.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import pl.javastart.ap.R;

public class AnimalListFragmentActivity extends AppCompatActivity implements AnimalListFragment.OnAnimalClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_animal_list);
    }

    @Override
    public void onAnimalClicked(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(AnimalDetailFragment.ANIMAL_ID, id);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AnimalDetailFragment animalDetailFragment = new AnimalDetailFragment();
            animalDetailFragment.setArguments(bundle);
            findViewById(R.id.label_show_details).setVisibility(View.GONE);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container_animal_detail, animalDetailFragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();

            return;
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, AnimalDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
