import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected LocalDate by;

    /**
     * Creates a deadline task from a yyyy-MM-dd date string.
     *
     * @param description deadline description
     * @param by due date in yyyy-MM-dd format
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new ChotuException("Sir, please use date format yyyy-MM-dd for deadlines.");
        }
    }

    /**
     * Creates a deadline task with only a description.
     *
     * @param description deadline description
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Sets the due date.
     *
     * @param by due date
     */
    public void setBy(LocalDate by) {
        this.by = by;
    }

    /**
     * Returns the due date in storage format.
     *
     * @return due date in yyyy-MM-dd
     */
    public String getByForStorage() {
        return by.toString();
    }

    /**
     * Returns this deadline in display format.
     *
     * @return formatted deadline text
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DISPLAY_FORMAT) + ")";
    }
}
