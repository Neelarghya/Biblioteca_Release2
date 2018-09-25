package Biblioteca.model.value_objects;

// Mail contact information
public class EmailAddress {
    private final String address;

    public EmailAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
