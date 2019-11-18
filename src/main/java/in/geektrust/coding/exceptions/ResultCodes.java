package in.geektrust.coding.exceptions;

public final class ResultCodes {
    private ResultCodes() {
        throw new UnsupportedOperationException("No instances!");
    }

    /**
     * Input command is incorrect or not in expected format.
     */
    public static final String INVALID_COMMAND = "Invalid command: %s";

    /**
     * The person involved in command is not found.
     */
    public static final String PERSON_NOT_FOUND = "PERSON_NOT_FOUND";

    /**
     * Child addition failed.
     */
    public static final String CHILD_ADDITION_FAILED = "CHILD_ADDITION_FAILED";

    /**
     * Result contains no elements.
     */
    public static final String NONE = "NONE";

    /**
     * Planet created/recreated.
     */
    public static final String PLANET_CREATED = "PLANET_CREATED";

    /**
     * New child added successfully.
     */
    public static final String CHILD_ADDITION_SUCCEEDED = "CHILD_ADDITION_SUCCEEDED";

    /**
     * Marriage completed successfully.
     */
    public static final String MARRIED = "MARRIED";
}
