package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.Person;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SisterInLawRelation extends Relation {
    private static SisterInLawRelation INSTANCE = new SisterInLawRelation();

    private SisterInLawRelation() {
    }

    public static SisterInLawRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Sister-In-Law";
    }

    @Override
    public String description() {
        return "Spouse's sisters, Wives of siblings";
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> {
            Stream<Person> sistersOfSpouse = person.getSpouse()
                    .map(it -> it.search(Relation.sister()))
                    .orElseGet(Collections::emptySet)
                    .stream();
            Stream<Person> wivesOfBrothers = person.search(Relation.brother()).stream()
                    .map(Person::getSpouse)
                    .filter(Optional::isPresent)
                    .map(Optional::get);
            return Stream.concat(sistersOfSpouse, wivesOfBrothers)
                    .collect(Collectors.toSet());
        };
    }
}
