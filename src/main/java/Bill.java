import java.util.Scanner;

public class Bill {
    public static void main(String[] args) {
        final String NAME = "Bill";

        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");

        TaskManager manager = new TaskManager();

        try {
            manager.loadData();
        } catch (TaskException e) {
            System.out.println("Warning: " + e.getMessage());
        }

        String userInput;
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine().trim();

            try {
                MessageParser msg = new MessageParser(userInput);

                switch (msg.getTaskType()) {
                case "bye", "exit":
                    in.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    return;

                case "save":
                    manager.saveData();
                    break;

                case "list":
                    manager.listTasks();
                    break;

                case "mark":
                    manager.markTask(msg.getMarkedIndex(), true);
                    break;

                case "unmark":
                    manager.markTask(msg.getMarkedIndex(), false);
                    break;

                case "todo":
                    manager.addTask(new ToDo(msg.getTodoDescription()));
                    break;

                case "deadline":
                    manager.addTask(new Deadline(msg.getDeadlineDescription()[0],
                                                 msg.getDeadlineDescription()[1]));
                    break;

                case "event":
                    manager.addTask(new Event(msg.getEventDescription()[0],
                                              msg.getEventDescription()[1],
                                              msg.getEventDescription()[2]));
                    break;

                case "man", "help":
                    System.out.println("The manual is currently unavailable, please try again later");
                    break;
                case "delete":
                    manager.deleteTask(msg.getMarkedIndex());
                }
            } catch (InvalidCommandException | TaskException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
