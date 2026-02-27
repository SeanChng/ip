import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Handles the loading and saving of task data to a local file.
 * This class serves as the persistence layer, converting between the
 * internal Task objects and their text-based representations on disk.
 */
public class Storage {
    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * Saves the current list of tasks to the storage file.
     * Automatically creates the necessary directories and file if they do not exist.
     *
     * @param tasks The list of {@link Task} objects to persist.
     * @throws TaskException If there is an error writing to the file or creating directories.
     */
    public void save(ArrayList<Task> tasks) throws TaskException {
        try {
            File file = new File(FILE_PATH);
            File dir = file.getParentFile();
            if (dir != null && !dir.exists() && !dir.mkdirs()) {
                throw new TaskException("Failed to create directory: " + dir.getPath());
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task t: tasks) {
                writer.write(taskToLine(t));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new TaskException("Failed to save tasks: " + e.getMessage());
        }
    }

    /**
     * Loads task data from the storage file and reconstructs the task list.
     * If the file does not exist, an empty list is returned.
     *
     * @return An {@link ArrayList} of reconstructed {@link Task} objects.
     * @throws TaskException If the file exists but cannot be read or contains invalid data.
     */
    public ArrayList<Task> load() throws TaskException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    tasks.add(lineToTask(line));
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new TaskException("Failed to load tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Converts a Task object into a format suitable for file storage.
     * Delegates the formatting logic to the specific Task subclass.
     *
     * @param t The task to convert.
     * @return A string representation of the task for saving.
     */
    private String taskToLine(Task t) {
        return t.toSaveLine();
    }

    /**
     * Parses a line from the storage file and reconstructs the appropriate Task object.
     * Handles specific parsing logic for Todos, Deadlines (ISO format), and Events (ISO format).
     *
     * @param line A single line from the save file.
     * @return The reconstructed {@link Task} object.
     * @throws TaskException If the line format is unrecognized or dates are malformed.
     */
    private Task lineToTask(String line) throws TaskException {
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new TaskException("Corrupted save file entry: " + line);
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task t;
        switch (type) {
        case "T":
            t = new ToDo(description);
            break;
        case "D":
            if (parts.length < 4) throw new TaskException("Corrupted Deadline");
            LocalDateTime by = LocalDateTime.parse(parts[3].trim());
            t = new Deadline(description, by);
            break;
        case "E":
            if (parts.length < 5) throw new TaskException("Corrupted Event");
            LocalDateTime start = LocalDateTime.parse(parts[3].trim());
            LocalDateTime end = LocalDateTime.parse(parts[4].trim());
            t = new Event(description, start, end);
            break;
        default:
            throw new TaskException("Unknown task type in save file: " + type);
        }

        if (isDone) t.markAsDone();
        return t;
    }
}
