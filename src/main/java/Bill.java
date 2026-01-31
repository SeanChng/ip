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
            if (line.equals("list")) {
                manager.listTasks();
            } else if (line.matches("^mark\\s([1-9][0-9]?)")) {
                int idx = Integer.parseInt(line.substring(MARK_INDEX)) - 1;
                manager.markTask(idx, true);
            } else if (line.matches("^unmark\\s([1-9][0-9]?)")) {
                int idx = Integer.parseInt(line.substring(UNMARK_INDEX)) - 1;
                manager.markTask(idx, false);
            } else {
                manager.addTask(line);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }
}
