package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.family.Gender;
import in.geektrust.coding.domain.family.Person;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class DaughterRelation extends Relation {
    private static DaughterRelation INSTANCE = new DaughterRelation();

    private DaughterRelation() {
    }

    public static DaughterRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Daughter";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> person.getChildren().stream()
                .filter(it -> Gender.FEMALE.equals(it.getGender()))
                .collect(Collectors.toSet());
    }
}
