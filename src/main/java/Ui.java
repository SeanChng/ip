import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * The <code>Ui</code> class is responsible for reading user input and
 * displaying messages, warnings, and formatting the output for the CLI.
 */
public class Ui {
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Bill");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        String line = in.nextLine();
        while (line.trim().isEmpty()) {
            line = in.nextLine();
        }
        return line.trim();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showWarning(String message) {
        System.out.println("Warning: " + message);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Closes the underlying Scanner resource.
     * Should be called when the application is shutting down.
     */
    public void close() {
        in.close();
    }
}