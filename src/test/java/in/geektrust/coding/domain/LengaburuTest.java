package in.geektrust.coding.domain;

import in.geektrust.coding.domain.family.Gender;
import in.geektrust.coding.domain.family.Person;
import in.geektrust.coding.exceptions.MotherNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LengaburuTest {

    @Test
    void shouldHaveProtagonists() {
        Person king = new Person("king_name", Gender.MALE);
        Person queen = new Person("queen_name", Gender.FEMALE);
        Lengaburu lengaburu = new Lengaburu(king, queen);

        assertThat(lengaburu)
                .as("new planet must not be null")
                .isNotNull();
        assertThat(lengaburu.getKing())
                .as("new planet must have a king")
                .isEqualTo(king);
        assertThat(lengaburu.getQueen())
                .as("new planet must have a queen")
                .isEqualTo(queen);
        assertThat(lengaburu.population())
                .as("new planet must have population of 2 (king & queen)")
                .isEqualTo(2);
    }

    @Test
    void findProtagonistsByName() {
        Person king = new Person("king_name", Gender.MALE);
        Person queen = new Person("queen_name", Gender.FEMALE);
        Lengaburu lengaburu = new Lengaburu(king, queen);

        Optional<Person> person1 = lengaburu.find("king_name");
        Optional<Person> person2 = lengaburu.find("queen_name");

        assertThat(person1.isPresent())
                .as("must find the person")
                .isTrue();
        assertThat(person1.get())
                .as("must find the same person")
                .isEqualTo(king);
        assertThat(person2.isPresent())
                .as("must find the person")
                .isTrue();
        assertThat(person2.get())
                .as("must find the same person")
                .isEqualTo(queen);
    }

    @Test
    void addNewPerson() {
        Person king = new Person("king_name", Gender.MALE);
        Person queen = new Person("queen_name", Gender.FEMALE);
        Lengaburu lengaburu = new Lengaburu(king, queen);

        Person prince = lengaburu.addPerson(queen.getName(), "prince_name", Gender.MALE);
        Optional<Person> result = lengaburu.find(prince.getName());

        assertThat(prince)
                .as("new person must be added successfully")
                .isNotNull();
        assertThat(result.isPresent())
                .as("new person must exist")
                .isTrue();
        assertThat(result.get())
                .as("new person must be the same one that was added")
                .isEqualTo(prince);
        assertThat(lengaburu.population())
                .as("adding new person must increase the population")
                .isEqualTo(3);
    }

    @Test
    void givenMotherDoesNotExist_whenAddPerson_thenThrowException() {
        Person king = new Person("king_name", Gender.MALE);
        Person queen = new Person("queen_name", Gender.FEMALE);
        Lengaburu lengaburu = new Lengaburu(king, queen);

        assertThatExceptionOfType(MotherNotFoundException.class)
                .isThrownBy(() -> lengaburu.addPerson("invalid_person_name", "child_name", Gender.MALE));
    }

    @Test
    void givenMotherDoesNotExist_whenAddPersonWithWildcardMother_thenDoNotThrowException() {
        Person king = new Person("king_name", Gender.MALE);
        Person queen = new Person("queen_name", Gender.FEMALE);
        Lengaburu lengaburu = new Lengaburu(king, queen);

        Person person = lengaburu.addPerson("?", "child_name", Gender.MALE);

        assertThat(person)
                .as("must allow adding new person with wildcard(?)")
                .isNotNull();
    }
}
