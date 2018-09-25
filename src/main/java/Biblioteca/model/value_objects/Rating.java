package Biblioteca.model.value_objects;

// A measure of how good something is
public class Rating {
    private final String value;

    public Rating(int value) {
        if (value > 0 && value <= 10) {
            this.value = Integer.toString(value);
            return;
        }
        this.value = "Unrated";
    }

    public String displayRating() {
        return value;
    }
}
