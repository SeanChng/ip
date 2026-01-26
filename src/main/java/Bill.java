import java.util.Scanner;

public class Bill {
    public static void main(String[] args) {
        String name = "Bill";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        Task[] tasks = new Task[100];
        int tasksCnt = 0;

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!(line.equals("bye"))) {
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasksCnt; i++) {
                    System.out.println(i + 1 + ". " +
                                               tasks[i].getStatusIcon() + " " +
                                               tasks[i].description);
                }
            } else if (line.matches("^mark\\s([1-9][0-9]?)")) {
                int idx = Integer.parseInt(line.substring(5)) - 1;
                tasks[idx].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[idx].getStatusIcon() + " " + tasks[idx].description);
            } else if (line.matches("^unmark\\s([1-9][0-9]?)")) {
                int idx = Integer.parseInt(line.substring(7)) - 1;
                tasks[idx].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[idx].getStatusIcon() + " " + tasks[idx].description);
            } else {
                Task newTask = new Task(line);
                tasks[tasksCnt] = newTask;
                tasksCnt++;
                System.out.println("added: " + line);
            }

            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
