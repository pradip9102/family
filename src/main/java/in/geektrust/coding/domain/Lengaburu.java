package in.geektrust.coding.domain;

import in.geektrust.coding.domain.family.Gender;
import in.geektrust.coding.domain.family.Person;

import java.util.LinkedHashMap;
import java.util.Map;

public class Lengaburu {

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

    public Person find(String name) {
        return people.get(name);
    }

    public Person addPerson(String motherName, String childName, Gender childGender) {
        Person mother = find(motherName);
        Person child = mother.addChild(new Person(childName, childGender));
        people.put(childName, child);
        return child;
    }
}
