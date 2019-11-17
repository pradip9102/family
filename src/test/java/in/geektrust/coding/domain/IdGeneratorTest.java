package in.geektrust.coding.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IdGeneratorTest {

    @Test
    void returnsNewId() {
        assertThat(IdGenerator.newId()).isEqualTo(1L);
        assertThat(IdGenerator.newId()).isEqualTo(2L);
    }
}
