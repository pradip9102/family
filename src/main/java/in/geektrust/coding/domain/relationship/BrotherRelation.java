package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.family.Gender;
import in.geektrust.coding.domain.family.Person;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class BrotherRelation extends Relation {
    private static BrotherRelation INSTANCE = new BrotherRelation();

    private BrotherRelation() {
    }

    public static BrotherRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Brother";
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> person.search(Relation.sibling()).stream()
                .filter(it -> Gender.MALE.equals(it.getGender()))
                .collect(Collectors.toSet());
    }
}
