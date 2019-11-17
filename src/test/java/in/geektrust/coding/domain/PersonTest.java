package in.geektrust.coding.domain;

import in.geektrust.coding.domain.relationship.Relation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
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
                .as("man must be married")
                .isTrue();
        assertThat(woman.isMarried())
                .as("woman must be married")
                .isTrue();
        assertThat(man.getSpouse().isPresent())
                .as("man must have spouse")
                .isTrue();
        assertThat(woman.getSpouse().isPresent())
                .as("woman must have spouse")
                .isTrue();
        assertThat(man.getSpouse().get())
                .as("married man must have spouse")
                .isEqualTo(woman);
        assertThat(woman.getSpouse().get())
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

    @Test
    void checkExistenceOfPerson() {
        Person father = new Person("father_name", Gender.MALE);
        Person mother = new Person("mother_name", Gender.FEMALE);
        father.marry(mother);
        Person child = mother.addChild(new Person("child_name", Gender.FEMALE));

        boolean exists = father.hasChild(child);

        assertThat(exists)
                .isTrue();
    }

    @Test
    void removePerson() {
        Person father = new Person("father_name", Gender.MALE);
        Person mother = new Person("mother_name", Gender.FEMALE);
        father.marry(mother);
        Person child = mother.addChild(new Person("child_name", Gender.FEMALE));

        child.remove();

        assertThat(father.hasChild(child))
                .isFalse();
        assertThat(mother.hasChild(child))
                .isFalse();
    }

    @DisplayName("search child (son/daughter)")
    @ParameterizedTest
    @MethodSource("dataChildRelations")
    void findChildRelations(List<Person> children, Relation relation, List<Person> expectedChildren) {
        Person father = new Person("father_name", Gender.MALE);
        Person mother = new Person("mother_name", Gender.FEMALE);
        father.marry(mother);
        children.forEach(mother::addChild);

        Set<Person> childrenOfFather = father.search(relation);
        Set<Person> childrenOfMother = mother.search(relation);

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

        Set<Person> siblings = child.search(Relation.sibling());

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

    @DisplayName("search related people")
    @ParameterizedTest
    @MethodSource("dataRelatedPeople")
    void searchRelatedPeople(Person targetPerson, Relation relation, List<Person> expectedPersons) {
        Set<Person> result = targetPerson.search(relation);

        assertThat(result)
                .as("must contain all " + relation.name() + "(s)")
                .containsExactlyElementsOf(expectedPersons);
    }

    private static Stream<Arguments> dataRelatedPeople() {
        List<String> details = Arrays.asList(
                "m_p00", "f_p01", "m_p02", "f_p03", "m_p04", "f_p05", "m_p06", "f_p07", "f_p08", "f_p09",
                "m_p10", "m_p11", "m_p12", "f_p13", "m_p14", "m_p15", "f_p16", "m_p17", "f_p18", "f_p19",
                "f_p20", "f_p21", "f_p22", "m_p23", "f_p24", "f_p25", "m_p26", "m_p27", "m_p28", "f_p29");
        Person[] people = details.stream().map(PersonTest::parsePerson).toArray(Person[]::new);
        createGeneration(Arrays.asList(
                Arrays.asList(people[0], people[1], people[6], people[7], people[8], people[9], people[10]),
                Arrays.asList(people[2], people[3], people[11], people[12], people[13], people[14]),
                Arrays.asList(people[4], people[5], people[15], people[16])
        ));
        createGeneration(Arrays.asList(
                Arrays.asList(people[7], people[11], people[17], people[18], people[19], people[20]),
                Arrays.asList(people[8], people[12], people[21], people[22], people[23]),
                Arrays.asList(people[13], people[15], people[24], people[25], people[26], people[27], people[28]),
                Arrays.asList(people[10], people[16], people[29])
        ));
        return Stream.of(
                Arguments.of(people[0], Relation.son(), Arrays.asList(people[6], people[10])),
                Arguments.of(people[3], Relation.son(), Arrays.asList(people[11], people[12], people[14])),
                Arguments.of(people[5], Relation.daughter(), Arrays.asList(people[16])),
                Arguments.of(people[12], Relation.daughter(), Arrays.asList(people[21], people[22])),
                Arguments.of(people[25], Relation.brother(), Arrays.asList(people[26], people[27], people[28])),
                Arguments.of(people[28], Relation.brother(), Arrays.asList(people[26], people[27])),
                Arguments.of(people[23], Relation.sister(), Arrays.asList(people[21], people[22])),
                Arguments.of(people[19], Relation.sister(), Arrays.asList(people[18], people[20])),
                Arguments.of(people[14], Relation.sibling(), Arrays.asList(people[11], people[12], people[13])),
                Arguments.of(people[8], Relation.brotherInLaw(), Arrays.asList(people[11], people[14])),
                Arguments.of(people[8], Relation.sisterInLaw(), Arrays.asList(people[13], people[16])),
                Arguments.of(people[19], Relation.paternalUncle(), Arrays.asList(people[12], people[14])),
                Arguments.of(people[21], Relation.maternalUncle(), Arrays.asList(people[6], people[10])),
                Arguments.of(people[19], Relation.paternalAunt(), Arrays.asList(people[13])),
                Arguments.of(people[21], Relation.maternalAunt(), Arrays.asList(people[7], people[9]))
        );
    }

    private static void createGeneration(List<List<Person>> generationDetails) {
        generationDetails.forEach(it -> {
            it.get(0).marry(it.get(1));
            for (int i = 2; i < it.size(); ++i) {
                it.get(0).addChild(it.get(i));
            }
        });
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
