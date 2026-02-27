import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter PRINT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    public Event(String description, String startTimeStr, String endTimeStr) {
        super(description);
        this.startTime = LocalDateTime.parse(startTimeStr.trim(), INPUT_FORMAT);
        this.endTime = LocalDateTime.parse(endTimeStr.trim(), INPUT_FORMAT);
    }

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toSaveLine() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime + " | " + endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + startTime.format(PRINT_FORMAT) +
                " to: " + endTime.format(PRINT_FORMAT) + ")";
    }
}
