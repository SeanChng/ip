/**
 * Interprets user input strings and breaks them down into command types and arguments.
 * The parser validates the structure of the input and prepares the data for the TaskManager.
 */
public class MessageParser {

    private String taskType;
    private int markedIndex;
    private String todoDescription;
    private String[] deadlineDescription;
    private String[] eventDescription;

    private String findKeyword;

    /**
     * Constructs a MessageParser and begins the parsing process for the given input.
     *
     * @param userInput The raw line of text entered by the user.
     * @throws InvalidCommandException If the input is empty, null, or follows an invalid format.
     */
    public MessageParser(String userInput) throws InvalidCommandException {
        if (userInput == null || userInput.trim().isEmpty()) {
            throw new InvalidCommandException("null input");
        }

        String[] components = userInput.split(" ", 2);
        parseByTaskType(components);
    }

    /**
     * Categorizes the input into a specific command type and extracts relevant arguments.
     * Handles specific formatting for todos, deadlines, events, and index-based commands.
     *
     * @param components Array where index 0 is the command word and index 1 is the arguments string.
     * @throws InvalidCommandException If the arguments are missing or incorrectly formatted.
     */
    private void parseByTaskType(String[] components) throws InvalidCommandException {
        taskType = components[0].toLowerCase();
        switch (taskType) {
        case "list", "bye", "exit", "man", "help", "save":
            break;

        case "todo":
            if (components.length < 2 || components[1].trim().isEmpty()) {
                throw new InvalidCommandException("todo");
            }
            todoDescription = components[1].trim();
            break;

        case "deadline":
            if (components.length < 2 || components[1].trim().isEmpty()) {
                throw new InvalidCommandException("deadline");
            }
            deadlineDescription = components[1].split(" /by ", 2);
            if (deadlineDescription.length < 2 || deadlineDescription[1].trim().isEmpty()) {
                throw new InvalidCommandException("deadline");
            }
            break;

        case "event":
            if (components.length < 2 || components[1].trim().isEmpty()) {
                throw new InvalidCommandException("event");
            }
            eventDescription = components[1].split(" /from | /to ", 3);
            if (eventDescription.length < 3 ||
                    eventDescription[1].trim().isEmpty() ||
                    eventDescription[2].trim().isEmpty()) {
                throw new InvalidCommandException("event");
            }
            break;

        case "mark", "unmark", "delete":
            if (components.length < 2 || components[1].trim().isEmpty()) {
                throw new InvalidCommandException(taskType);
            }
            try {
                markedIndex = Integer.parseInt(components[1].trim());
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(taskType);
            }
            break;

        case "find":
            if (components.length < 2 || components[1].trim().isEmpty()) {
                throw new InvalidCommandException("find");
            }
            this.findKeyword = components[1].trim();
            break;

        default:
            throw new InvalidCommandException(taskType);
        }
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTodoDescription() {
        return todoDescription;
    }

    /** * @return A String array where index 0 is description and index 1 is the deadline date.
     */
    public String[] getDeadlineDescription() {
        return deadlineDescription;
    }

    /** * @return A String array where index 0 is description, 1 is start time, and 2 is end time.
     */
    public String[] getEventDescription() {
        return eventDescription;
    }

    public int getMarkedIndex() {
        return markedIndex;
    }

    public String getFindKeyword() {
        return findKeyword;
    }
}
