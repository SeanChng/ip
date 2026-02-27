import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * A Deadline object contains a description and a date/time
 * by which the task must be completed.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /** The format used to parse date/time strings from user input. */
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /** The format used to display date/time to the user. */
    private static final DateTimeFormatter PRINT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs a Deadline task using a raw date string.
     * Used primarily when processing new user input.
     *
     * @param description The description of the task.
     * @param by The deadline date string in the format "d/M/yyyy HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by.trim(), INPUT_FORMAT);
    }

    /**
     * Constructs a Deadline task using an existing LocalDateTime object.
     * Used primarily when loading tasks from storage.
     *
     * @param description The description of the task.
     * @param by The LocalDateTime representing the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string formatted for saving the task to a file.
     * * @return A pipe-separated string containing task type, status, description, and deadline.
     */
    @Override
    public String toSaveLine() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns a user-friendly string representation of the deadline task.
     * The date is formatted as "MMM dd yyyy" (e.g., Dec 02 2019).
     *
     * @return A string indicating task type, status, description, and formatted deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(PRINT_FORMAT) + ")";
    }
}