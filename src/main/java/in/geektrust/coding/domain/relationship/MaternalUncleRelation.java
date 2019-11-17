package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.family.Person;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

class MaternalUncleRelation extends Relation {
    private static MaternalUncleRelation INSTANCE = new MaternalUncleRelation();

    private MaternalUncleRelation() {
    }

    public static MaternalUncleRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Maternal-Uncle";
    }

    @Override
    public String description() {
        return "Mother's brothers";
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> {
            Person mother = person.getMother();
            if (mother == null) {
                return Collections.emptySet();
            }

            return mother.search(Relation.brother());
        };
    }
}
