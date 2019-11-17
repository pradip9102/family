package in.geektrust.coding.exceptions;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(String personName) {
        super(String.format("Person(%s) not found!", personName));
    }
}
