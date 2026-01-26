import java.util.Scanner;

public class Bill {
    public static void main(String[] args) {
        String name = "Bill";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!(line.equals("bye"))) {
            System.out.println(line);
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
