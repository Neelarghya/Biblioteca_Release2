package Biblioteca.model.value_objects;

// Part of credential required to authenticate a user
public class Password {
    private final String value;

    public Password(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Password password = (Password) o;
        return value.equals(password.value);
    }
}
