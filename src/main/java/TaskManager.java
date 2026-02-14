import java.util.ArrayList;

public class TaskManager {

    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your list is currently empty.");
            System.out.println("Start by adding some tasks!");
            System.out.println("usage: {todo|deadline|event} [arguments]");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
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
            System.out.println("Nice! I've marked this task as done:");
        } else {
            t.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  " + t);
    }

    public void deleteTask(int userIndex) throws TaskException {
        int listIndex = userIndex - 1;
        if (listIndex >= tasks.size() || listIndex < 0) {
            throw new TaskException("index is out of range");
        }
        Task removed = tasks.get(listIndex);

        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + removed);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }
}
