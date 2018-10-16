package pl.javastart.ap.lists.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.javastart.ap.R;

public class CustomRecyclerViewActivity extends AppCompatActivity {

    private AnimalsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_custom);

        Animal[] possibleAnimals = {
                new Animal("Pies", R.drawable.dog_image),
                new Animal("Kot", R.drawable.cat_image),
                new Animal("Słoń", R.drawable.elephant_image)};


        List<Animal> animals = new ArrayList<>(100);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            Animal randomAnimal = possibleAnimals[random.nextInt(possibleAnimals.length)];
            Animal animal = new Animal(randomAnimal.name + " " + (i + 1), randomAnimal.image);
            animals.add(animal);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnimalsAdapter(animals);
        recyclerView.setAdapter(adapter);
    }

    private class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder> {

        private List<CustomRecyclerViewActivity.Animal> animals;

        public AnimalsAdapter(List<CustomRecyclerViewActivity.Animal> animals) {
            this.animals = animals;
        }

        public class AnimalViewHolder extends RecyclerView.ViewHolder {
            public TextView animalName;
            public ImageView animalImage;
            public ImageButton animalDeleteButton;

            public AnimalViewHolder(View v) {
                super(v);
            }
        }

        @NonNull
        @Override
        public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recycle_animal, parent, false);
            AnimalViewHolder viewHolder = new AnimalViewHolder(v);
            viewHolder.animalName = v.findViewById(R.id.animal_name);
            viewHolder.animalImage = v.findViewById(R.id.animal_image);
            viewHolder.animalDeleteButton = v.findViewById(R.id.delete_animal_button);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final AnimalViewHolder holder, int position) {
            CustomRecyclerViewActivity.Animal animal = animals.get(position);
            holder.animalName.setText(animal.name);
            holder.animalImage.setImageResource(animal.image);
            holder.animalDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getAdapterPosition();
                    animals.remove(position);
                    adapter.notifyItemRemoved(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return animals.size();
        }
    }

    private class Animal {
        String name;
        Integer image;

        public Animal(String name, Integer image) {
            this.name = name;
            this.image = image;
        }
    }

}
