import java.util.Scanner;

public class Bill {
    public static void main(String[] args) {
        String name = "Bill";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        String[] tasks = new String[100];
        int tasksCnt = 0;

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!(line.equals("bye"))) {
            if (line.equals("list")) {
                for (int i = 0; i < tasksCnt; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
            } else {
                tasks[tasksCnt] = line;
                tasksCnt++;
                System.out.println("added: " + line);
            }

            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
