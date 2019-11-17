package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.Person;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BrotherInLawRelation extends Relation {
    private static BrotherInLawRelation INSTANCE = new BrotherInLawRelation();

    private BrotherInLawRelation() {
    }

    public static BrotherInLawRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Brother-In-Law";
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> {
            Stream<Person> brothersOfSpouse = person.getSpouse()
                    .map(it -> it.search(Relation.brother()))
                    .orElseGet(Collections::emptySet)
                    .stream();
            Stream<Person> husbandsOfSisters = person.search(Relation.sister()).stream()
                    .map(Person::getSpouse)
                    .filter(Optional::isPresent)
                    .map(Optional::get);
            return Stream.concat(brothersOfSpouse, husbandsOfSisters)
                    .collect(Collectors.toSet());
        };
    }
}
