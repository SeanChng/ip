/**
 * Represents a generic task in the Bill application.
 * This is an abstract class that serves as a base for specific task types
 * like {@link ToDo}, {@link Deadline}, and {@link Event}.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Initializes a task with the given description.
     * By default, a new task is not completed.
     *
     * @param description The textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns a formatted string specifically designed for file storage.
     * Subclasses must implement this to include their specific data fields.
     *
     * @return A string representation of the task for saving to a text file.
     */
    public abstract String toSaveLine();

    /**
     * Returns a user-friendly string representation of the task,
     * including its status icon and description.
     *
     * @return A string formatted for CLI display.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.getDescription();
    }
}
