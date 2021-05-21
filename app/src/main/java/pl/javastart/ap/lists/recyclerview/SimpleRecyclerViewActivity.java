package pl.javastart.ap.lists.recyclerview;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import pl.javastart.ap.R;

public class SimpleRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_simple);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        String[] possibleAnimals = {"Pies", "Kot", "Kangur", "Panda", "Żyrafa", "Królik"};

        String[] animals = new String[100];
        Random random = new Random();
        for (int i = 0; i < animals.length; i++) {
            animals[i] = possibleAnimals[random.nextInt(possibleAnimals.length)] + " " + (i + 1);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new AnimalsAdapter(animals));
    }

    private static class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder> {

        private String[] animals;

        public AnimalsAdapter(String[] animals) {
            this.animals = animals;
        }

        public static class AnimalViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;

            public AnimalViewHolder(TextView v) {
                super(v);
                textView = v;
            }
        }

        @NonNull
        @Override
        public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

            AnimalViewHolder viewHolder = new AnimalViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
            String animal = animals[position];
            holder.textView.setText(animal);
        }

        @Override
        public int getItemCount() {
            return animals.length;
        }
    }

}
