import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a specific time range.
 * An Event object includes a description, a start time, and an end time.
 */
public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /** The format used to parse date/time strings from user input. */
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /** The format used to display date/time to the user. */
    private static final DateTimeFormatter PRINT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Constructs an Event task using raw date/time strings for start and end.
     * Used primarily for processing new user commands.
     *
     * @param description The description of the event.
     * @param startTimeStr The start date/time in "d/M/yyyy HHmm" format.
     * @param endTimeStr The end date/time in "d/M/yyyy HHmm" format.
     */
    public Event(String description, String startTimeStr, String endTimeStr) {
        super(description);
        this.startTime = LocalDateTime.parse(startTimeStr.trim(), INPUT_FORMAT);
        this.endTime = LocalDateTime.parse(endTimeStr.trim(), INPUT_FORMAT);
    }

    /**
     * Constructs an Event task using existing LocalDateTime objects.
     * Used primarily for loading tasks from storage.
     *
     * @param description The description of the event.
     * @param startTime The LocalDateTime object representing the start.
     * @param endTime The LocalDateTime object representing the end.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string formatted for saving to a text file.
     *
     * @return A pipe-separated string including task type, status, description,
     * and ISO-formatted start and end times.
     */
    @Override
    public String toSaveLine() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime + " | " + endTime;
    }

    /**
     * Returns a user-friendly string representation of the event.
     * Formats the start and end times into a readable "MMM dd yyyy, hh:mm a" format.
     *
     * @return A string indicating task type, status, description, and time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + startTime.format(PRINT_FORMAT) +
                " to: " + endTime.format(PRINT_FORMAT) + ")";
    }
}
