package pl.javastart.ap.fragment;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.R;

public class AnimalRepository {

    private static List<Animal> animalList;

    static {
        animalList = new ArrayList<>(3);
        animalList.add(new Animal(R.drawable.cat_image, "Kot", "Kot to zwierzę domowe."));
        animalList.add(new Animal(R.drawable.dog_image, "Pies", "Pies to najlepszy przyjaciel człowieka."));
        animalList.add(new Animal(R.drawable.elephant_image, "Słoń", "Słoń zjada do 225kg roślin dziennie i wypija do 130l wody na raz"));
    }

    public static List<Animal> getAnimalList() {
        return animalList;
    }
}
