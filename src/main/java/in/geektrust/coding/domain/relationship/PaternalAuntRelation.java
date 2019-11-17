package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.family.Person;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

class PaternalAuntRelation extends Relation {
    private static PaternalAuntRelation INSTANCE = new PaternalAuntRelation();

    private PaternalAuntRelation() {
    }

    public static PaternalAuntRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Parental-Aunt";
    }

    @Override
    public String description() {
        return "Father's sisters";
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> {
            Person father = person.getFather();
            if (father == null) {
                return Collections.emptySet();
            }

            return father.search(Relation.sister());
        };
    }
}
