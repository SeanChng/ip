public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCnt = 0;

    public void addTask(String description) {
        tasks[tasksCnt] = new Task(description);
        tasksCnt++;
        System.out.println("added: " + description);
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksCnt; i++) {
            System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].description);
        }
    }

    public void markTask(int index, boolean isDone) {
        if (index >= 0 && index < tasksCnt) {
            if (isDone) {
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                tasks[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + tasks[index].getStatusIcon() + " " + tasks[index].description);
        }
    }
}
