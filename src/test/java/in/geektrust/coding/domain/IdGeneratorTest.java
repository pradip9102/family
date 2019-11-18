package in.geektrust.coding.domain;

import in.geektrust.coding.domain.family.IdGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IdGeneratorTest {

    @Test
    void returnsNewId() {
        assertThat(IdGenerator.newId())
                .isNotEqualTo(IdGenerator.newId());
    }
}
