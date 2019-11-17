package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.Person;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

class SiblingRelation extends Relation {
    private static SiblingRelation INSTANCE = new SiblingRelation();

    private SiblingRelation() {
    }

    public static SiblingRelation getInstance() {
        return INSTANCE;
    }

    @Override
    public String name() {
        return "Siblings";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Function<Person, Set<Person>> searchStrategy() {
        return child -> child.getMother().getChildren().stream()
                .filter(it -> !child.equals(it))
                .collect(Collectors.toSet());
    }
}
