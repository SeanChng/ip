/**
 * Signals that a user-entered command is either unrecognized or improperly formatted.
 * This exception encapsulates the logic for generating context-specific error messages
 * based on the command type that triggered the failure.
 */
public class InvalidCommandException extends Exception {

    public InvalidCommandException(String taskType) {
        super(getMessageForTaskType(taskType));
    }

    /**
     * Maps a command keyword or error trigger to a helpful usage instruction.
     * Provides the user with the correct syntax for the failed command.
     *
     * @param taskType The type of command that failed.
     * @return A formatted string containing a description of the error and the correct usage.
     */
    private static String getMessageForTaskType(String taskType) {
        if (taskType == null || taskType.isEmpty()) {
            return "No command entered. Use \"man\" to see available commands.";
        }

        switch (taskType) {
        case "null input":
            return "Please type in a command or use \"man\" to see the list of commands.";
        case "todo":
            return "Invalid command for todo task. Use format: todo <taskDescription>";
        case "deadline":
            return "Invalid command for deadline task. Use format: deadline <taskDescription> /by <deadline>";
        case "event":
            return "Invalid command for event task. Use format: event <taskDescription> /from <start> /to <end>";
        case "mark":
            return "Invalid command for mark task. Use format: mark <taskIndex>";
        case "unmark":
            return "Invalid command for unmark task. Use format: unmark <taskIndex>";
        case "delete":
            return "Invalid command for deleting task. Use format: delete <taskIndex>";
        default:
            return "Unknown command: " + taskType + "\nUse \"man\" to see the list of all commands.";
        }
    }
}
