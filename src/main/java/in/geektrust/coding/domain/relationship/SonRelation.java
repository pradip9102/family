package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.family.Gender;
import in.geektrust.coding.domain.family.Person;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class SonRelation extends Relation {
    private static SonRelation INSTANCE = new SonRelation();

    private SonRelation() {
    }

    public static SonRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Son";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> person.getChildren().stream()
                .filter(it -> Gender.MALE.equals(it.getGender()))
                .collect(Collectors.toSet());
    }
}
