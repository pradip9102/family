package in.geektrust.coding.domain;

import in.geektrust.coding.domain.family.Gender;
import in.geektrust.coding.domain.family.Person;
import in.geektrust.coding.exceptions.MotherNotFoundException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Lengaburu {

    public static final String WILDCARD_MOTHER_NAME = "?";
    private Person king;
    private Person queen;
    private Map<String, Person> people = new LinkedHashMap<>();

    public Lengaburu(Person king, Person queen) {
        this.king = king;
        this.queen = queen;
        people.put(this.king.getName(), this.king);
        people.put(this.queen.getName(), this.queen);
        king.marry(queen);
    }

    public Person getKing() {
        return king;
    }

    public Person getQueen() {
        return queen;
    }

    public Integer population() {
        return people.size();
    }

    public Optional<Person> find(String name) {
        return Optional.ofNullable(people.get(name));
    }

    public Person addPerson(String motherName, String childName, Gender childGender) {
        Person child;
        if (!Objects.equals(WILDCARD_MOTHER_NAME, motherName)) {
            Person mother = find(motherName)
                    .orElseThrow(() -> new MotherNotFoundException(motherName));
            child = mother.addChild(new Person(childName, childGender));
        } else {
            child = new Person(childName, childGender);
        }
        people.put(childName, child);
        return child;
    }
}
