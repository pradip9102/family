package in.geektrust.coding.domain.relationship;

import in.geektrust.coding.domain.Gender;
import in.geektrust.coding.domain.Person;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class SonRelation extends Relation {

    @Override
    public String name() {
        return "son";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public Function<Person, List<Person>> searchStrategy() {
        return person -> person.getChildren().stream()
                .filter(it -> Gender.MALE.equals(it.getGender()))
                .collect(Collectors.toList());
    }
}
