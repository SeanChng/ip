/**
 * Represents a simple task without any specific date or time attached to it.
 * The <code>ToDo</code> class is the most basic form of a {@link Task}.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string formatted for saving to a text file.
     * The format used is "T | status | description".
     *
     * @return A pipe-separated string representing the ToDo task for storage.
     */
    @Override
    public String toSaveLine() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a user-friendly string representation of the ToDo task.
     * Prefixes the standard task string with a "[T]" identifier.
     *
     * @return A string formatted for CLI display including the type and status icon.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
