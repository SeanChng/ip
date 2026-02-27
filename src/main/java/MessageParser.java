public class MessageParser {

    private String taskType;
    private int markedIndex;
    private String todoDescription;
    private String[] deadlineDescription;
    private String[] eventDescription;



    private String findKeyword;

    public MessageParser(String userInput) throws InvalidCommandException {
        if (userInput == null || userInput.trim().isEmpty()) {
            throw new InvalidCommandException("null input");
        }

        String[] components = userInput.split(" ", 2);
        parseByTaskType(components);
    }

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

    public String[] getDeadlineDescription() {
        return deadlineDescription;
    }

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
