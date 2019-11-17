package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.Person;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

class SiblingRelation extends Relation {

    @Override
    public String name() {
        return "sibling";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Function<Person, List<Person>> searchStrategy() {
        return child -> child.getMother().getChildren().stream()
                .filter(it -> !Objects.equals(it, child))
                .collect(Collectors.toList());
    }
}
