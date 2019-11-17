package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.family.Person;

import java.util.Collections;
import java.util.Set;
import java.util.function.Function;

class MaternalAuntRelation extends Relation {
    private static MaternalAuntRelation INSTANCE = new MaternalAuntRelation();

    private MaternalAuntRelation() {
    }

    public static MaternalAuntRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Maternal-Aunt";
    }

    @Override
    public String description() {
        return "Mother's sisters";
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return person -> {
            Person mother = person.getMother();
            if (mother == null) {
                return Collections.emptySet();
            }

            return mother.search(Relation.sister());
        };
    }
}
