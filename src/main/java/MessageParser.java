public class MessageParser {

    private final String taskType;
    private int markedIndex;
    private String todoDescription;
    private String[] deadlineDescription;
    private String[] eventDescription;

    public MessageParser(String userInput) {
        String[] components = userInput.split(" ", 2);
        taskType = components[0];
        switch (taskType) {
        case "mark":
        case "unmark":
            markedIndex = Integer.parseInt(components[1]);
            break;
        case "todo":
            todoDescription = components[1];
            break;
        case "deadline":
            deadlineDescription = components[1].split(" /by ", 2);
            break;
        case "event":
            eventDescription = components[1].split(" /from | /to ", 3);
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
