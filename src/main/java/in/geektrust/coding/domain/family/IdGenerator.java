package in.geektrust.coding.domain.family;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private static AtomicLong generator = new AtomicLong(0L);

    public static Long newId() {
        return generator.incrementAndGet();
    }
}
