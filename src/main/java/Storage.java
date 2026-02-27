import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "data/tasks.txt";

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

    private String taskToLine(Task t) {
        return t.toSaveLine();
    }

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
