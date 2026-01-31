import java.util.Scanner;

public class Bill {
    public static void main(String[] args) {
        String NAME = "Bill";
        int MARK_INDEX = 5;
        int UNMARK_INDEX = 7;
        int TODO_INDEX = 5;
        int DEADLINE_INDEX = 9;
        int EVENT_INDEX = 6;

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
            } else if (userInput.startsWith("todo")) {
                manager.addTask(new ToDo(userInput.substring(TODO_INDEX)));
            } else if (userInput.startsWith("deadline")) {
                int byIndex = userInput.indexOf("/by");
                manager.addTask(
                        new Deadline(userInput.substring(DEADLINE_INDEX, byIndex - 1),
                                     userInput.substring(byIndex + 4)));
            } else if (userInput.startsWith("event")) {
                int startIndex = userInput.indexOf("/from");
                int endIndex = userInput.indexOf("/to");
                manager.addTask(new Event(userInput.substring(EVENT_INDEX, startIndex - 1),
                                          userInput.substring(startIndex + 6, endIndex - 1),
                                          userInput.substring(endIndex + 4)));
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
