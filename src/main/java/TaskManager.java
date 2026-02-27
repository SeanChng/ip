import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks = new ArrayList<>();
    private final Storage storage = new Storage();
    private final Ui ui;

    public TaskManager(Ui ui) {
        this.ui = ui;
    }

    public void saveData() throws TaskException {
        storage.save(tasks);
        ui.showMessage("Tasks saved successfully.");
    }

    public void loadData() throws TaskException {
        tasks = storage.load();
        if (!tasks.isEmpty()) {
            ui.showMessage("Loaded " + tasks.size() + " task(s) from save file.");
        }
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        ui.showMessage("Got it. I've added this task:\n" +
                               "  " + newTask.toString() + "\n" +
                               "Now you have " + tasks.size() + " task(s) in the list.");
    }

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
