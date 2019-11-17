package in.geektrust.coding.domain;

import in.geektrust.coding.domain.relationship.Relation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {

    private Long id;
    private String name;
    private Gender gender;
    private Person father;
    private Person mother;
    private Person spouse;
    private List<Person> children = new ArrayList<>();

    public Person(String name, Gender gender) {
        this.id = IdGenerator.newId();
        this.name = name;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Person getFather() {
        return this.father;
    }

    public Person getMother() {
        return this.mother;
    }

    public Person getSpouse() {
        return spouse;
    }

    public List<Person> getChildren() {
        return children;
    }

    public boolean isMarried() {
        return spouse != null;
    }

    public void marry(Person other) {
        if (!Objects.equals(this, other)) {
            this.spouse = other;
            other.spouse = this;
        }
    }

    public Person addChild(Person child) {
        if (!isMarried()) {
            return null;
        }

        switch (this.gender) {
            case MALE:
                child.mother = this.spouse;
                child.father = this;
                break;
            case FEMALE:
                child.mother = this;
                child.father = this.spouse;
                break;
        }
        this.children.add(child);
        this.spouse.children.add(child);
        return child;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<Person> findSiblings() {
        return Relation.sibling().searchStrategy().apply(this);
    }

    public List<Person> search(Relation relation) {
        return relation.searchStrategy().apply(this);
    }
}
