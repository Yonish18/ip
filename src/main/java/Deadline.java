import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new ChotuException("Sir, please use date format yyyy-MM-dd for deadlines.");
        }
    }

    public Deadline(String description) {
        super(description);
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    public String getByForStorage() {
        return by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DISPLAY_FORMAT) + ")";
    }
}
