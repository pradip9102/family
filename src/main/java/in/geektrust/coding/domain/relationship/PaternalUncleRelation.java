package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.family.Person;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

class PaternalUncleRelation extends Relation {
    private static PaternalUncleRelation INSTANCE = new PaternalUncleRelation();

    private PaternalUncleRelation() {
    }

    public static PaternalUncleRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Paternal-Uncle";
    }

    @Override
    public String description() {
        return "Father's brothers";
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> {
            Person father = person.getFather();
            if (father == null) {
                return Collections.emptySet();
            }

            return father.search(Relation.brother());
        };
    }
}
