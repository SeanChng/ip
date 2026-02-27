/**
 * Acts as the main controller for the Bill Task Manager.
 * This class coordinates the interactions between the UI, Storage and Logic.
 */
public class Bill {

    /**
     * Starts the main execution loop of the application.
     * Initializes the UI and TaskManager, loads existing data,
     * and processes user commands until the exit command is received.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        final Ui ui = new Ui();
        final TaskManager manager = new TaskManager(ui);

        loadData(manager, ui);
        ui.showWelcome();

        runCommands(ui, manager);
    }

    /**
     * Acts as the wrapper method to start accepting commands,
     * ensures that the UI is not interrupted or prematurely closed.
     *
     * @param ui The Ui instance to display feedback to the user.
     * @param manager The TaskManager instance to perform operations on.
     */
    private static void runCommands(Ui ui, TaskManager manager) {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = handleCommand(ui, manager);
        }
    }

    /**
     * Processes a single user command by delegating to the MessageParser
     * and executing the corresponding logic in the TaskManager.
     *
     * @param ui The Ui instance to display feedback to the user.
     * @param manager The TaskManager instance to perform operations on.
     * @return true if the program should continue, false if it should exit.
     */
    private static boolean handleCommand(Ui ui, TaskManager manager) {
        try {
            MessageParser msg = new MessageParser(ui.readCommand());

            switch (msg.getTaskType()) {
            case "bye", "exit":
                closeApp(manager, ui);
                return false;

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
                ui.showMessage("The manual is currently unavailable, please try again later");
                break;

            case "delete":
                manager.deleteTask(msg.getMarkedIndex());
                break;

            case "find":
                manager.findTasks(msg.getFindKeyword());
                break;
            }
        } catch (InvalidCommandException | TaskException e) {
            ui.showWarning(e.getMessage());
        }
        return true;
    }

    /**
     * Performs cleanup and finalizes application state before exiting.
     * This includes saving the current task list to local storage and
     * closing any active UI resources.
     *
     * @param manager The TaskManager used to trigger the data save.
     * @param ui The Ui instance to be closed.
     * @throws TaskException If an error occurs during the file saving process.
     */
    private static void closeApp(TaskManager manager, Ui ui) throws TaskException {
        manager.saveData();
        ui.close();
        ui.showGoodbye();
    }

    /**
     * Attempts to load existing task data from the local storage file.
     * If a loading error occurs, a warning is displayed to the user via the UI,
     * but the application continues with an empty task list.
     *
     * @param manager The TaskManager where the loaded tasks will be stored.
     * @param ui The Ui instance used to display potential load warnings.
     */
    private static void loadData(TaskManager manager, Ui ui) {
        try {
            manager.loadData();
        } catch (TaskException e) {
            ui.showWarning(e.getMessage());
        }
    }
}
