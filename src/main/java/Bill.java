public class Bill {
    public static void main(String[] args) {
        final Ui ui = new Ui();
        final TaskManager manager = new TaskManager(ui);

        ui.showWelcome();
        loadData(manager, ui);
        runCommands(ui, manager);
    }

    private static void runCommands(Ui ui, TaskManager manager) {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = handleCommand(ui, manager);
        }
    }

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
            }
        } catch (InvalidCommandException | TaskException e) {
            ui.showWarning(e.getMessage());
        }
        return true;
    }

    private static void closeApp(TaskManager manager, Ui ui) throws TaskException {
        manager.saveData();
        ui.close();
        ui.showGoodbye();
    }

    private static void loadData(TaskManager manager, Ui ui) {
        try {
            manager.loadData();
        } catch (TaskException e) {
            ui.showWarning(e.getMessage());
        }
    }
}
