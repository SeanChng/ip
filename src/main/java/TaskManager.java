import java.util.ArrayList;

/**
 * Manages the in-memory list of tasks.
 * This class handles operations such as adding, deleting, marking, and
 * searching for tasks, while coordinating with the Storage and Ui components.
 */
public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();
    private final Storage storage = new Storage();
    private final Ui ui;

    /**
     * Constructs a TaskManager with a reference to the application UI.
     *
     * @param ui The {@link Ui} instance used for displaying messages.
     */
    public TaskManager(Ui ui) {
        this.ui = ui;
    }

    /**
     * Persists the current task list to local storage.
     *
     * @throws TaskException If an error occurs during the saving process.
     */
    public void saveData() throws TaskException {
        storage.save(tasks);
        ui.showMessage("Tasks saved successfully.");
    }

    /**
     * Loads task data from storage into the internal list.
     *
     * @throws TaskException If the storage file is corrupted or unreadable.
     */
    public void loadData() throws TaskException {
        tasks = storage.load();
        if (!tasks.isEmpty()) {
            ui.showMessage("Loaded " + tasks.size() + " task(s) from save file.");
        }
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param newTask The task to be added.
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        ui.showMessage("Got it. I've added this task:\n" +
                               "  " + newTask.toString() + "\n" +
                               "Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Displays all current tasks in a numbered list.
     * If the list is empty, provides usage instructions to the user.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            ui.showMessage("Your list is currently empty.\n" +
                                   "Start by adding some tasks!\n" +
                                   "usage: {todo|deadline|event} [arguments]");
            return;
        }
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + "." + tasks.get(i).toString());
        }
    }

    /**
     * Updates the completion status of a specific task.
     *
     * @param userIndex The 1-based index of the task as seen by the user.
     * @param isDone True to mark as completed, false to mark as not done.
     * @throws TaskException If the provided index is invalid or out of bounds.
     */
    public void markTask(int userIndex, boolean isDone) throws TaskException {
        int listIndex = userIndex - 1;
        if (listIndex < 0 || listIndex >= tasks.size()) {
            throw new TaskException("Task index " + userIndex + " is out of range. Use \"list\" to see valid indices.");
        }
        Task t = tasks.get(listIndex);
        if (isDone) {
            t.markAsDone();
            ui.showMessage("Nice! I've marked this task as done:");
        } else {
            t.markAsNotDone();
            ui.showMessage("OK, I've marked this task as not done yet:");
        }
        ui.showMessage("  " + t);
    }

    /**
     * Removes a task from the list based on its position.
     *
     * @param userIndex The 1-based index of the task to be deleted.
     * @throws TaskException If the provided index is invalid or out of bounds.
     */
    public void deleteTask(int userIndex) throws TaskException {
        int listIndex = userIndex - 1;
        if (listIndex >= tasks.size() || listIndex < 0) {
            throw new TaskException("index is out of range");
        }
        Task removed = tasks.remove(listIndex);

        ui.showMessage("Noted. I've removed this task:\n" +
                               " " + removed + "\n" +
                               "Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Searches for and displays tasks whose descriptions contain the specified keyword.
     * The search is case-insensitive.
     *
     * @param keyword The string to search for within task descriptions.
     */
    public void findTasks(String keyword) {
        String searchLower = keyword.toLowerCase();
        String output = "Here are the matching tasks in your list:";
        int matchCount = 0;

        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(searchLower)) {
                matchCount++;
                output += "\n" + matchCount + "." + t;
            }
        }

        if (matchCount == 0) {
            ui.showMessage("There are no tasks matching: " + keyword);
        } else {
            ui.showMessage(output);
        }
    }
}
