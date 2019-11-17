package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.Gender;
import in.geektrust.coding.domain.Person;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class SisterRelation extends Relation {
    private static SisterRelation INSTANCE = new SisterRelation();

    private SisterRelation() {
    }

    public static SisterRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Sister";
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> person.search(Relation.sibling()).stream()
                .filter(it -> Gender.FEMALE.equals(it.getGender()))
                .collect(Collectors.toSet());
    }
}
