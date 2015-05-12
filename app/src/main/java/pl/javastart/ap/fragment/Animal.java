package pl.javastart.ap.fragment;

public class Animal {

    private int image;
    private String name;
    private String description;

    public Animal(int image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

