public class MessageParser {

    private final String taskType;
    private int markedIndex;
    private String todoDescription;
    private String[] deadlineDescription;
    private String[] eventDescription;

    public MessageParser(String userInput) throws InvalidCommandException {
        if (userInput == null || userInput.isEmpty()) {
            throw new InvalidCommandException("null input");
        }
        String[] components = userInput.split(" ", 2);
        taskType = components[0];
        switch (taskType) {
        case "list", "bye", "exit", "man", "help":
            break;
        case "mark", "unmark":
            if (components.length < 2 || components[1].trim().isEmpty()) {
                throw new InvalidCommandException(taskType);
            }
            try {
                markedIndex = Integer.parseInt(components[1].trim());
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(taskType);
            }
            break;

        case "todo":
            if (components.length < 2 || components[1].trim().isEmpty()) {
                throw new InvalidCommandException("todo");
            }
            todoDescription = components[1];
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

    public String[] getDeadlineDescription() {
        return deadlineDescription;
    }

    public String[] getEventDescription() {
        return eventDescription;
    }

    public int getMarkedIndex() {
        return markedIndex;
    }
}
