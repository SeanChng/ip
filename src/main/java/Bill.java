import java.util.Scanner;

public class Bill {
    public static void main(String[] args) {
        String NAME = "Bill";
        int MARK_INDEX = 5;
        int UNMARK_INDEX = 7;

        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");

        TaskManager manager = new TaskManager();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!(line.equals("bye"))) {
            line = line.trim();
            if (line.equals("list")) {
                manager.listTasks();
            } else if (line.startsWith("mark")) {
                String taskPart = line.substring(MARK_INDEX);
                int taskIndex = Integer.parseInt(taskPart);
                manager.markTask(taskIndex - 1, true);
            } else if (line.startsWith("unmark")) {
                String taskPart = line.substring(UNMARK_INDEX);
                int taskIndex = Integer.parseInt(taskPart);
                manager.markTask(taskIndex - 1, false);
            } else {
                manager.addTask(line);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
