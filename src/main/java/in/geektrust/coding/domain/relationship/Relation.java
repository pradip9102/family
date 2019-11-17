package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.Person;

import java.util.List;
import java.util.function.Function;

public abstract class Relation {

    public abstract  String name();

    public abstract String description();

    public abstract Function<Person, List<Person>> searchStrategy();

    public static Relation son() {
        return new SonRelation();
    }

    public static Relation daughter() {
        return new DaughterRelation();
    }

    public static Relation sibling() {
        return new SiblingRelation();
    }
}
