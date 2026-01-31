import java.util.Scanner;

public class Bill {
    public static void main(String[] args) {
        String NAME = "Bill";
        int MARK_INDEX = 5;
        int UNMARK_INDEX = 7;

        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");

        TaskManager manager = new TaskManager();

        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!(userInput.equals("bye"))) {
            userInput = userInput.trim();
            if (userInput.equals("list")) {
                manager.listTasks();
            } else if (userInput.startsWith("mark")) {
                String taskPart = userInput.substring(MARK_INDEX);
                int taskIndex = Integer.parseInt(taskPart);
                manager.markTask(taskIndex - 1, true);
            } else if (userInput.startsWith("unmark")) {
                String taskPart = userInput.substring(UNMARK_INDEX);
                int taskIndex = Integer.parseInt(taskPart);
                manager.markTask(taskIndex - 1, false);
            } else {
                manager.addTask(userInput);
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
