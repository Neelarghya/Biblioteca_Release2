package Biblioteca.model.value_objects;

public class PhoneNumber {
    private final int number;

    public PhoneNumber(int number) {
        this.number = number;
    }

    public String getNumber() {
        return Integer.toString(number);
    }
}
