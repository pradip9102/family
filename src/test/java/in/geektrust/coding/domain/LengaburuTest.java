package in.geektrust.coding.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

        Person person1 = lengaburu.find("king_name");
        Person person2 = lengaburu.find("queen_name");

        assertThat(person1)
                .as("must find the person by name")
                .isEqualTo(king);
        assertThat(person2)
                .as("must find the person by name")
                .isEqualTo(queen);
    }

    @Test
    void addNewPerson() {
        Person king = new Person("king_name", Gender.MALE);
        Person queen = new Person("queen_name", Gender.FEMALE);
        Lengaburu lengaburu = new Lengaburu(king, queen);

        Person prince = lengaburu.addPerson(queen.getName(), "prince_name", Gender.MALE);

        assertThat(prince)
                .isNotNull();
        assertThat(lengaburu.find(prince.getName()))
                .isEqualTo(prince);
        assertThat(lengaburu.population())
                .isEqualTo(3);
    }
}
