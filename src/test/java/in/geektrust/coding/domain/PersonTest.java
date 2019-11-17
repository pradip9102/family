package in.geektrust.coding.domain;

import in.geektrust.coding.domain.relationship.Relation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    @Test
    @DisplayName("create person")
    void createPerson() {
        String name = "person_name_1";
        Gender gender = Gender.MALE;

        Person person = new Person(name, gender);

        assertThat(person.getId())
                .as("new person must have an id")
                .isNotNull();
        assertThat(person.getName())
                .as("new person must have a name")
                .isEqualTo(name);
        assertThat(person.getGender())
                .as("new person must have gender")
                .isEqualTo(gender);
        assertThat(person.isMarried())
                .as("new person must not be married")
                .isFalse();
        assertThat(person.getChildren())
                .as("new person must not have any children")
                .isEmpty();
    }

    @Test
    @DisplayName("married person has spouse")
    void givenMarried_thenHasSpouse() {
        Person man = new Person("person_name_1", Gender.MALE);
        Person woman = new Person("person_name_2", Gender.FEMALE);

        man.marry(woman);

        assertThat(man.isMarried())
                .isTrue();
        assertThat(woman.isMarried())
                .isTrue();
        assertThat(man.getSpouse())
                .as("married man must have spouse")
                .isEqualTo(woman);
        assertThat(woman.getSpouse())
                .as("married woman must have spouse")
                .isEqualTo(man);
    }

    @Test
    @DisplayName("can not marry self")
    void aPersonCanNotMarrySelf() {
        Person person = new Person("person_name_1", Gender.MALE);

        person.marry(person);

        assertThat(person.isMarried())
                .as("a person can not marry themselves")
                .isFalse();
    }

    @Test
    @DisplayName("married couple can have child")
    void givenMarried_whenMakeChild_thenMakeNewPerson() {
        Person man = new Person("person_name_1", Gender.MALE);
        Person woman = new Person("person_name_2", Gender.FEMALE);
        man.marry(woman);

        Person child = woman.addChild(new Person("child_name", Gender.FEMALE));

        assertThat(child.getName())
                .isEqualTo("child_name");
        assertThat(child.getGender())
                .isEqualTo(Gender.FEMALE);
        assertThat(man.getChildren())
                .as("man must have child")
                .contains(child);
        assertThat(woman.getChildren())
                .as("woman must have child")
                .contains(child);
        assertThat(child.getMother())
                .as("child must have mother")
                .isEqualTo(woman);
        assertThat(child.getFather())
                .as("child must have father")
                .isEqualTo(man);
    }

    @Test
    @DisplayName("single person can't have child")
    void givenNotMarried_whenMakeChild_thenMustNotMakeChild() {
        Person person = new Person("person_name_1", Gender.MALE);

        Person child = person.addChild(new Person("child_name", Gender.FEMALE));

        assertThat(child)
                .as("unmarried person can not have child")
                .isNull();
    }

    @DisplayName("search child (son/daughter)")
    @ParameterizedTest
    @MethodSource("dataChildRelations")
    void findChildRelations(List<Person> children, Relation relation, List<Person> expectedChildren) {
        Person father = new Person("father_name", Gender.MALE);
        Person mother = new Person("mother_name", Gender.FEMALE);
        father.marry(mother);
        children.forEach(mother::addChild);

        List<Person> childrenOfFather = father.search(relation);
        List<Person> childrenOfMother = mother.search(relation);

        assertThat(childrenOfFather)
                .as("both parents must have same children")
                .containsAll(childrenOfMother)
                .as("must contain all %s", relation.name())
                .containsAll(expectedChildren);
    }

    private static Stream<Arguments> dataChildRelations() {
        Person child1 = parsePerson("m_c1");
        Person child2 = parsePerson("m_c2");
        Person child3 = parsePerson("f_c3");
        Person child4 = parsePerson("m_c4");
        Person child5 = parsePerson("f_c5");
        List<Person> allChildren = Arrays.asList(child1, child2, child3, child4, child5);
        return Stream.of(
                Arguments.of(allChildren, Relation.son(), Arrays.asList(child1, child2, child4)),
                Arguments.of(allChildren, Relation.daughter(), Arrays.asList(child3, child5))
        );
    }

    @DisplayName("search sibling")
    @ParameterizedTest
    @MethodSource("dataSiblingRelations")
    void findSiblingRelations(List<Person> children, Person child, List<Person> expectedChildren) {
        Person father = new Person("father_name", Gender.MALE);
        Person mother = new Person("mother_name", Gender.FEMALE);
        father.marry(mother);
        children.forEach(mother::addChild);

        List<Person> siblings = child.search(Relation.sibling());

        assertThat(siblings)
                .as("siblings must not contains self")
                .doesNotContain(child)
                .as("must contain all siblings")
                .containsAll(expectedChildren);
    }

    private static Stream<Arguments> dataSiblingRelations() {
        Person child1 = parsePerson("m_c1");
        Person child2 = parsePerson("m_c2");
        Person child3 = parsePerson("f_c3");
        Person child4 = parsePerson("m_c4");
        Person child5 = parsePerson("f_c5");
        List<Person> allChildren = Arrays.asList(child1, child2, child3, child4, child5);
        return Stream.of(
                Arguments.of(allChildren, child1, Arrays.asList(child2, child3, child4, child5))
        );
    }

    private static Person parsePerson(String detail) {
        String name = parseName(detail);
        Gender gender = parseGender(detail);
        return new Person(name, gender);
    }

    private static String parseName(String detail) {
        return detail.substring(2);
    }

    private static Gender parseGender(String detail) {
        return detail.charAt(0) == 'm' ? Gender.MALE : Gender.FEMALE;
    }
}
