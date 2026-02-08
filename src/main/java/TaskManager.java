public class TaskManager {

    private final Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(Task newTask) {
        // ensure there is space for new task
        if (tasksCount < tasks.length) {
            tasks[tasksCount] = newTask;
            tasksCount++;

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newTask.toString());
            System.out.println("Now you have " + tasksCount + " task(s) in the list.");
        } else {
            System.out.println("Error: Task list is full!");
        }
    }

    public void listTasks() {
        if (tasksCount == 0) {
            System.out.println("Your list is currently empty.");
            System.out.println("Start by adding some tasks!");
            System.out.println("usage: {todo|deadline|event} [arguments]");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1) + "." + tasks[i].toString());
        }
    }

    public void markTask(int userIndex, boolean isDone) {
        if (userIndex >= 0 && userIndex < tasksCount) {
            Task t = tasks[userIndex];
            if (isDone) {
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                t.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + t);
        } else {
            System.out.println("Error: Task index " + userIndex + " does not exist.");
        }
    }
}
